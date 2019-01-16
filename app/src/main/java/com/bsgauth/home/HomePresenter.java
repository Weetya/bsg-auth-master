package com.bsgauth.home;

import android.content.Context;

import com.bsgauth.model.Account;
import com.bsgauth.navigation.HomeNavigationPresenter;
import com.bsgauth.repositories.AccountsRepository;
import com.bsgauth.repositories.CurrentUserRepository;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.fragments.homeScreen.AccountDeletable;
import com.bsgauth.ui.fragments.homeScreen.AccountUpdatable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomePresenter {
    private AccountsRepository repository;
    private AccountDeletable accountDeletable;
    private CurrentUserRepository currentUserRepository;
    private HomeNavigationPresenter navigation;
    private AccountUpdatable accountUpdatable;

    public HomePresenter(AccountsRepository repository, AccountDeletable accountDeletable, AccountUpdatable accountUpdatable,
                         CurrentUserRepository currentUserRepository, HomeNavigationPresenter navigation) {
        this.repository = repository;
        this.currentUserRepository = currentUserRepository;
        this.navigation = navigation;
        this.accountDeletable = accountDeletable;
        this.accountUpdatable = accountUpdatable;
    }


    public void deleteAccount(Account account) {
        repository.delete(account);
        accountDeletable.deleteAccount(account);
    }

    public void logOut(Context context) {
        currentUserRepository.logOut(context);
        navigation.backtoFirstFragment((MoveToFragment) context);
    }

    public List<Account> getAccounts () throws IOException {
        new GetAccountsTask(this, repository).execute();
        return new ArrayList<>();
    }

    public void updateAccounts (List<Account> accounts) {
        accountUpdatable.updateAccounts(accounts);
    }
}

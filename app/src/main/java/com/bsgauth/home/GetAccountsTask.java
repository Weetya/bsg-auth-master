package com.bsgauth.home;

import android.os.AsyncTask;

import com.bsgauth.model.Account;
import com.bsgauth.repositories.AccountsRepository;

import java.io.IOException;
import java.util.List;

public class GetAccountsTask extends AsyncTask<Void, Void, List<Account>> {
    private HomePresenter presenter;
    private AccountsRepository repository;

    public GetAccountsTask(HomePresenter presenter, AccountsRepository repository) {
        this.presenter = presenter;
        this.repository = repository;
    }


    @Override
    protected void onPostExecute(List<Account> accounts) {
        presenter.updateAccounts(accounts);
    }

    @Override
    protected List<Account> doInBackground(Void... voids) {
        try {
            return repository.getAll().execute().body().get("accounts");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

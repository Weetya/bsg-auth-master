package com.bsgauth.ui.fragments.homeScreen;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsgauth.R;
import com.bsgauth.home.HomePresenter;
import com.bsgauth.model.Account;
import com.bsgauth.model.Device;
import com.bsgauth.model.DeviceType;
import com.bsgauth.navigation.HomeNavigationPresenter;
import com.bsgauth.repositories.AccountsRepository;
import com.bsgauth.repositories.CurrentUserRepository;
import com.bsgauth.ui.activities.MoveToFragment;

import java.io.IOException;
import java.util.List;

/***
 *  @author Daria Kostenko
 */
public class HomescreenFragment extends Fragment implements AccountDeletable, AccountUpdatable {

    private RecyclerView recyclerView;
    private AccountsRvAdapter rvAdapter;

    private FloatingActionButton addAccount;

    private View view;

    private List<Account> accounts;

    private HomeNavigationPresenter navigation = new HomeNavigationPresenter();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_homescreen, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        HomePresenter presenter =  new HomePresenter(new AccountsRepository(), this, this,
                new CurrentUserRepository(), navigation);

        try {
            accounts = presenter.getAccounts();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Initialize RecyclerView
        recyclerView = view.findViewById(R.id.rv_accounts);
        recyclerView.setSaveEnabled(true);
        rvAdapter = new AccountsRvAdapter(accounts, getActivity(), presenter);

        rvAdapter = new AccountsRvAdapter(accounts, getActivity(), presenter);
        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //FAB's onScroll show/hide effect
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && addAccount.getVisibility() == View.VISIBLE) {
                    addAccount.hide();
                } else if (dy < 0 && addAccount.getVisibility() != View.VISIBLE) {
                    addAccount.show();
                }
            }
        });

        //Initialize and set onClick for the addAccount button
        addAccount = view.findViewById(R.id.btn_add_account);

        //TODO: add transition to CreateNewAccount fragment
        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getContext(), "Add transition to fragment", Toast.LENGTH_SHORT).show();
                navigation.moveToAddNewAccount((MoveToFragment) getActivity());
            }
        });


    }

    @Override
    public void deleteAccount(Account account) {
        accounts.remove(account);
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateAccounts(List<Account> accounts) {
        this.accounts.clear();
        this.accounts.addAll(accounts);
        rvAdapter.notifyDataSetChanged();
    }
}
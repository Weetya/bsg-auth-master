package com.bsgauth.addNewAccount.Presenter;

import android.content.Context;

import com.bsgauth.repositories.CurrentUserRepository;

public class PhoneNumberPresenter {

    private Context context;
    private CurrentUserRepository repository;

    public PhoneNumberPresenter (Context context, CurrentUserRepository repository){
        this.context = context;
        this.repository = repository;
    }

    public void savePhoneNumber(String number){
        repository.savePhoneNumber(context, number);
    }
}

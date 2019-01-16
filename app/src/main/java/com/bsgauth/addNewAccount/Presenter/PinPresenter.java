package com.bsgauth.addNewAccount.Presenter;

import android.content.Context;

import com.bsgauth.navigation.SwitchToConf;
import com.bsgauth.repositories.CurrentUserRepository;
import com.bsgauth.repositories.SMSRepository;
import com.bsgauth.ui.activities.MoveToFragment;


public class PinPresenter {
    private SwitchToConf navigation;
    private SMSRepository repository;
    private CurrentUserRepository currentUserRepository;

    public PinPresenter(SwitchToConf navigation, SMSRepository repository, CurrentUserRepository currentUserRepository) {
        this.navigation = navigation;
        this.repository = repository;
        this.currentUserRepository = currentUserRepository;
    }


    public boolean checkPin(int pin) {
        return repository.getPinCode() == pin;
    }

    public boolean authenticate(Context context, int pin, MoveToFragment moveTo)
            throws IllegalArgumentException {
        if (checkPin(pin)) {
            currentUserRepository.signUp(context);
            navigation.switchToHome(moveTo);
            return true;
        }
        else
            return false;
    }
}

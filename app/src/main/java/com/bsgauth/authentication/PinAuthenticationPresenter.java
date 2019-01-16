package com.bsgauth.authentication;

import com.bsgauth.navigation.AuthenticationNavigationPresenter;
import com.bsgauth.repositories.PinCodeRepository;
import com.bsgauth.ui.activities.MoveToFragment;

public class PinAuthenticationPresenter {
    private AuthenticationNavigationPresenter navigation;
    private PinCodeRepository repository;

    public PinAuthenticationPresenter(AuthenticationNavigationPresenter navigation, PinCodeRepository repository) {
        this.navigation = navigation;
        this.repository = repository;
    }


    public boolean checkPin(int pin) {
        return repository.getPinCode() == pin;
    }

    public boolean authenticate(int pin, MoveToFragment moveTo) throws IllegalArgumentException {
        if (checkPin(pin)) {
            navigation.authenticate(moveTo);
            return true;
        }
        else
            return false;
    }
}
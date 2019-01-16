package com.bsgauth.addNewAccount;

public class EnterCodeManuallyPresenter {
    public boolean isTokenValid(String token){
        String regex = "^[0-9]{6}";
        return token.length() != 0 && token.matches(regex);
    }

}

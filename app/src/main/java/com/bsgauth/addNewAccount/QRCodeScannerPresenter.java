package com.bsgauth.addNewAccount;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;

import com.bsgauth.model.Account;
import com.bsgauth.permissions.PermissionHandler;
import com.bsgauth.repositories.AccountsRepository;
import com.google.zxing.Result;


public class QRCodeScannerPresenter {
    private String code = null;
    private final static int REQUEST_CAMERA_CODE = 100;
    private AccountsRepository accountsRepository;

    private void saveTokenAndAddAccount(String code){
        accountsRepository = new AccountsRepository();
        int codeInt = Integer.parseInt(code);
        // TODO: replace nulls when backend is connected
        accountsRepository.add(new Account(codeInt, null, null));
    }

    // get result which is scanned with
    public boolean isResultTokenSavedSuccessfully(Result rawResult){
        code = rawResult.getText();
        if(this.isTokenValid(code)){
            saveTokenAndAddAccount(code);
            return true;
        } else {
            return false;
        }
    }

    public void requestCameraPermission(Fragment fragment){
        PermissionHandler permissionHandler = new PermissionHandler();
        // check if user denied for camera
        if( !(permissionHandler.hasPermission
                ((AppCompatActivity)fragment.getActivity(), Manifest.permission.CAMERA)) ){
            // if true, request permission again
            permissionHandler.requestPermission(fragment, new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_CODE);

        }
    }

    public boolean isCameraPermissionGranted(int requestCode, @NonNull String[] permissions,
                                             @NonNull int[] grantResults){
        if(requestCode == REQUEST_CAMERA_CODE ){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                return true;
            }
        }
        return false;
    }

    private boolean isTokenValid(String token){
        String regex = "^[0-9]{6}";
        return token.length() != 0 && token.matches(regex);
    }
}

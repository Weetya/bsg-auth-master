package com.bsgauth.ui.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bsgauth.R;
import com.bsgauth.navigation.QRCodeScannerNavigation;
import com.bsgauth.addNewAccount.QRCodeScannerPresenter;
import com.bsgauth.ui.activities.BackToFragment;
import com.bsgauth.ui.activities.MoveToFragment;
import com.bsgauth.ui.activities.ShowPopupListener;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerFragment extends Fragment implements ZXingScannerView.ResultHandler, DialogInterface.OnDismissListener {

    private ZXingScannerView scannerView;
    private QRCodeScannerPresenter presenter;
    private ShowPopupListener listener;
    private QRCodeScannerNavigation navigation;

    public void setShowPopupListener(ShowPopupListener listener){
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        // TODO: set Toolbar
        // TODO: ask permissions
        presenter = new QRCodeScannerPresenter();
        navigation = new QRCodeScannerNavigation();
        checkCameraPermission();
        // make a layout
        View view = inflater.inflate(R.layout.fragment_qr_code_scanner, container, false);
        // make a scanner
        scannerView = new ZXingScannerView(getActivity());
        // set this scanner in content_frame
        ViewGroup containerFrame = view.findViewById(R.id.content_frame);
        containerFrame.addView(scannerView);

        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        scannerView.startCamera();
        scannerView.setResultHandler(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        scannerView.stopCamera();
        Log.i("TAG", "onPause");
    }

    // handle result of scanning
    @Override
    public void handleResult(Result rawResult) {
        if(presenter.isResultTokenSavedSuccessfully(rawResult)){
            navigation.switchToAddNewAccountFragment((MoveToFragment) getActivity());
        } else {
            this.showFailurePopup();
        }
    }

    // ask permissions
    private void checkCameraPermission(){
        presenter.requestCameraPermission(this);
    }

    // callback, handles result of asking
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(presenter.isCameraPermissionGranted(requestCode, permissions, grantResults)){
            return; // if permission is granted
        } else {
            this.navigation.switchToAddNewFragmentIfNoPermission((BackToFragment) this.getActivity());
        }
    }

    public void showFailurePopup(){
        listener.showPopup(R.string.token_is_not_valid_text, R.drawable.failure_icon, this);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        scannerView.resumeCameraPreview(this);
    }
}



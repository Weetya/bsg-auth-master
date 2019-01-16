package com.bsgauth.ui.activities;

import android.content.DialogInterface;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;

import com.bsgauth.R;
import com.bsgauth.navigation.StartFragmentResolver;
import com.bsgauth.ui.fragments.DialogWithImage;
import com.bsgauth.ui.fragments.FingerprintAuthenticationFragment;
import com.bsgauth.ui.fragments.homeScreen.HomescreenFragment;

public class MainActivity extends AppCompatActivity implements MoveToFragment, BackToFragment, ShowPopupListener, ClearBackStack {
    // global variables
    FragmentManager fm;
    Fragment fragment; // it's fragment just for test. Should be replaces with needed fragment

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_ACTION_MODE_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fragment = new StartFragmentResolver().getStartFragment(this);
        if (savedInstanceState == null) {
            // set start fragment
            this.moveTo(fragment);
        }
    }

    @Override
    public void moveTo(Fragment newFragment) {
        if (fm.findFragmentById(R.id.frame_container_main) != null) {
        fm.beginTransaction()
                .replace(R.id.frame_container_main, newFragment)
                .addToBackStack(null)
                .commit();
        } else {
            fm.beginTransaction()
                    .add(R.id.frame_container_main, newFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

    @Override
    public void backTo() {
        if (fm.findFragmentById(R.id.frame_container_main) != null) {
            fm.popBackStack();
        }
    }

    @Override
    public void onBackPressed() {
        if (fm.getBackStackEntryCount() == 1) {
            this.finish();
        } else if(fm.getBackStackEntryCount() > 1) {
            this.backTo();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void clearBackStack() {
        Log.i("TAG", "clear back stack");
        fm.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    //========================
    // Show Popup Listener realization

    @Override
    public void showPopup(int messageId, int iconId) {
        DialogWithImage dialog = new DialogWithImage(this, messageId, iconId);
        dialog.show();
    }

    // if fragment needs ondismiss
    @Override
    public void showPopup(int messageId, int iconId, DialogInterface.OnDismissListener listener) {
        DialogWithImage dialog = new DialogWithImage(this, messageId, iconId);
        dialog.setOnDismissListener(listener);
        dialog.show();
    }
}

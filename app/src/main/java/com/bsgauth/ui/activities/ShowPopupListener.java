package com.bsgauth.ui.activities;

import android.content.DialogInterface;

public interface ShowPopupListener {
    void showPopup(int messageId, int iconId);
    void showPopup(int messageId, int iconId, DialogInterface.OnDismissListener listener);
}

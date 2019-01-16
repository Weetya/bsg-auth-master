package com.bsgauth.ui.fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bsgauth.R;

public class DialogWithImage extends Dialog {

    private int messageId;
    private int iconId;

    public DialogWithImage(@NonNull Context context, int messageId, int iconId) {
        super(context);
        this.messageId = messageId;
        this.iconId = iconId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_popup_successful);
        ImageView icon = findViewById(R.id.icon_status);
        TextView textMsg = findViewById(R.id.text_message);
        Button btn = findViewById(R.id.btn_ok);

        icon.setImageResource(iconId);
        textMsg.setText(messageId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        if(getWindow() != null){
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

    }
}

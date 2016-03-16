package com.ske.snakebaddesign.models;

import android.content.DialogInterface;

/**
 * Created by พศิน on 16/3/2559.
 */
public class DataDialog {
    private String title;
    private String message;
    private DialogInterface.OnClickListener listener;

    public DataDialog(String title, String message, DialogInterface.OnClickListener listener) {
        this.title = title;
        this.message = message;
        this.listener = listener;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public DialogInterface.OnClickListener getListener() {
        return listener;
    }
}

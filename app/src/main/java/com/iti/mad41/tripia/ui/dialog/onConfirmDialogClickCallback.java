package com.iti.mad41.tripia.ui.dialog;

import android.content.DialogInterface;

public interface onConfirmDialogClickCallback {
    void onPositiveButtonClick(DialogInterface dialog, int id);
    void onNegativeButtonClick(DialogInterface dialog, int id);
}

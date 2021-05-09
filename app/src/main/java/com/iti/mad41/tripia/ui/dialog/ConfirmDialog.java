package com.iti.mad41.tripia.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.iti.mad41.tripia.adapters.onPreviousTripsClickCallback;

public class ConfirmDialog extends DialogFragment {
    private String message;
    private String positiveBtnTitle;
    private String negativeBtnTitle;
    private onConfirmDialogClickCallback onConfirmDialogClickCallback;

    public ConfirmDialog(String message, String positiveBtnTitle, String negativeBtnTitle){
        this.message = message;
        this.positiveBtnTitle = positiveBtnTitle;
        this.negativeBtnTitle = negativeBtnTitle;
    }

    public void setConfirmDialogClickCallback(onConfirmDialogClickCallback onConfirmDialogClickCallback){
        this.onConfirmDialogClickCallback = onConfirmDialogClickCallback;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(message)
                .setPositiveButton(positiveBtnTitle, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        onConfirmDialogClickCallback.onPositiveButtonClick(dialog, id);
                    }
                })
                .setNegativeButton(negativeBtnTitle, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        onConfirmDialogClickCallback.onNegativeButtonClick(dialog, id);
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

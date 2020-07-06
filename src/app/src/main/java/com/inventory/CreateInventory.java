package com.inventory;



import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.lang.reflect.Field;
import java.util.Objects;

import javax.xml.namespace.QName;

public class CreateInventory extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.activity_create_inventory, null))
                // Add action buttons
                .setPositiveButton("CREAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        EditText inventory_name = (EditText) getDialog().findViewById(R.id.inventory_name);
                        if (inventory_name != null) {
                            inventoryManagement im;
                            im = inventoryManagement.getInstance();
                            im.addInventory(inventory_name.getText().toString());
                            startActivity(new Intent(getActivity(), inventoryView.class));
                        }
                    }
                })
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //LoginDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
package com.inventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class user_details extends AppCompatActivity {
    long adm_id, user_id;
    user_management um;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Intent i = getIntent();
        adm_id = i.getLongExtra("admin_id", 0);
        user_id = i.getLongExtra("user_id", 0);

        um = user_management.getInstance().findHashMap(new User(adm_id, "", "", true)).user_m;
        user = um.findAVL(new User(user_id, "", "", false));

        displayUserCharacteristics();
    }

    public void modifyUser(View v) {
        TextView success = findViewById(R.id.success_ud);
        TextView error = findViewById(R.id.error_ud);
        success.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);

        TextView edit = (TextView) findViewById(R.id.edit_ud);
        edit.setVisibility(View.INVISIBLE);
        edit.setClickable(false);
        TextView discard = (TextView) findViewById(R.id.discard_ud);
        discard.setVisibility(View.VISIBLE);
        discard.setClickable(true);
        TextView confirm = (TextView) findViewById(R.id.confirm_ud);
        confirm.setVisibility(View.VISIBLE);
        confirm.setClickable(true);

        EditText name_edit = (EditText) findViewById(R.id.username_edtx);
        name_edit.setEnabled(true);
        EditText pass_edit = (EditText) findViewById(R.id.pass_edtx);
        pass_edit.setEnabled(true);
    }

    public void saveUserChanges(View v) {
        EditText name_edit = (EditText) findViewById(R.id.username_edtx);
        EditText pass_edit = (EditText) findViewById(R.id.pass_edtx);

        String newName = name_edit.getText().toString();
        String newPass = pass_edit.getText().toString();

        TextView success = findViewById(R.id.success_ud);
        TextView error = findViewById(R.id.error_ud);

        if(!newName.matches("")&&!newPass.matches("")) {
            if(isAlph(newName)) {

                user.name = newName;
                user.pass = newPass;

                error.setVisibility(View.INVISIBLE);
                success.setVisibility(View.VISIBLE);
            }
            else {
                success.setVisibility(View.INVISIBLE);
                error.setText("El nombre sólo puede contener caracteres alfabéticos y espacios");
                error.setVisibility(View.VISIBLE);
            }

        }
        else {
            success.setVisibility(View.INVISIBLE);
            error.setText("Rellene todos los campos");
            error.setVisibility(View.VISIBLE);
        }

        name_edit.setEnabled(false);
        pass_edit.setEnabled(false);

        TextView edit = (TextView) findViewById(R.id.edit_ud);
        edit.setVisibility(View.VISIBLE);
        edit.setClickable(true);
        TextView discard = (TextView) findViewById(R.id.discard_ud);
        discard.setVisibility(View.INVISIBLE);
        discard.setClickable(false);
        TextView confirm = (TextView) findViewById(R.id.confirm_ud);
        confirm.setVisibility(View.INVISIBLE);
        confirm.setClickable(false);

        displayUserCharacteristics();

    }

    public void discardUserChanges(View v) {
        TextView edit = (TextView) findViewById(R.id.edit_ud);
        edit.setVisibility(View.VISIBLE);
        edit.setClickable(true);
        TextView discard = (TextView) findViewById(R.id.discard_ud);
        discard.setVisibility(View.INVISIBLE);
        discard.setClickable(false);
        TextView confirm = (TextView) findViewById(R.id.confirm_ud);
        confirm.setVisibility(View.INVISIBLE);
        confirm.setClickable(false);

        EditText name_edit = (EditText) findViewById(R.id.username_edtx);
        name_edit.setEnabled(false);
        EditText pass_edit = (EditText) findViewById(R.id.pass_edtx);
        pass_edit.setEnabled(false);


        displayUserCharacteristics();
    }

    public void displayUserCharacteristics() {
        EditText id = (EditText) findViewById(R.id.user_id);
        EditText name_edit = (EditText) findViewById(R.id.username_edtx);
        EditText password_edit = (EditText) findViewById(R.id.pass_edtx);

        id.setText(String.valueOf(user.id));
        name_edit.setText(user.name);
        password_edit.setText(user.pass);
    }

    public void backToUser(View v) {
        Intent i = new Intent(this, UserView.class);
        i.putExtra("admin_id", adm_id);
        startActivity(i);
    }

    public void deleteUser(View v) {

        final Intent i = new Intent(this, UserView.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("ALERTA");
        builder.setMessage("¿Seguro deseas eliminar el usuario?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO: handle the OK
                        um.deleteAVL(user);
                        i.putExtra("admin_id", adm_id);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public boolean isAlph(String s) {
        char[] chars = s.toCharArray();
        for(char c:chars) {
            if(!Character.isAlphabetic(c)&&c!=' ') {
                return false;
            }
        }
        return true;
    }
}
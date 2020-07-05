package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class createUser extends AppCompatActivity {
    Button create_button;
    EditText username_et, password_et,id_et;
    boolean perms;
    long adm_id;
    String user_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        Intent i = getIntent();
        perms = i.getBooleanExtra("has_perms", false);
        adm_id = i.getLongExtra("admin_id", 0);
        user_n = i.getStringExtra("user_name");
    }

    public void addUser_da(View v){
        create_button = (Button)findViewById(R.id.create_user_button_da);
        id_et = (EditText)findViewById(R.id.create_id);
        username_et = (EditText)findViewById(R.id.create_user);
        password_et = (EditText)findViewById(R.id.create_password);

        long newId = Long.parseLong(id_et.getText().toString());
        String newUserN = username_et.getText().toString();
        String newPass = password_et.getText().toString();
        User newUser = new User(newId,newUserN,newPass, perms);


        user_management user_m = user_management.getInstance();
        TextView error = findViewById(R.id.create_error);
        TextView success = findViewById(R.id.create_success);

        //no hay alguien con ese id o está vacía la lista
        if(user_m.DAUsers == 0 || user_m.user_list.get(user_m.user_list.getIndexOf(newUser) ) == null || !(user_m.user_list.get(user_m.user_list.getIndexOf(newUser)).id == newUser.id)){
            user_m.addUser(newUser);
            success.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            //si ya hay alguien con ese id
            error.setVisibility(View.VISIBLE);
        }

    }

    public void addUser_avl(View v){
        create_button = (Button)findViewById(R.id.create_user_button_avl);
        id_et = (EditText)findViewById(R.id.create_id);
        username_et = (EditText)findViewById(R.id.create_user);
        password_et = (EditText)findViewById(R.id.create_password);

        long newId = Long.parseLong(id_et.getText().toString());
        String newUserN = username_et.getText().toString();
        String newPass = password_et.getText().toString();
        User newUser = new User(newId, newUserN, newPass, perms);
        user_management user_m = user_management.getInstance();
        TextView error = findViewById(R.id.create_error);
        TextView success = findViewById(R.id.create_success);
        User userFound = user_m.findAVL(newUser);
        if(userFound==null||userFound.id!=newId) {
            user_m.addUserAVL(newUser);
            error.setVisibility(View.INVISIBLE);
            success.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            success.setVisibility(View.INVISIBLE);
            error.setVisibility(View.VISIBLE);
        }
    }

    public void addUser_hashmap(View v){
        id_et = (EditText)findViewById(R.id.create_id);
        username_et = (EditText)findViewById(R.id.create_user);
        password_et = (EditText)findViewById(R.id.create_password);

        if(checkFields()) {
            long newId = Long.parseLong(id_et.getText().toString());
            String newName = username_et.getText().toString();
            String newPass = password_et.getText().toString();
            User newUser = new User(newId, newName, newPass, perms);
            user_management user_m = user_management.getInstance();
            TextView error = findViewById(R.id.create_error);
            TextView success = findViewById(R.id.create_success);

            if(perms) {
                User userFound = user_m.findHashMap(newUser);
                if(userFound==null||userFound.id!=newId) {
                    user_m.addUserHashMap(newUser);
                    error.setVisibility(View.INVISIBLE);
                    success.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    success.setVisibility(View.INVISIBLE);
                    error.setText("ID de usuario ya registrada");
                    error.setVisibility(View.VISIBLE);
                }
            }
            else {
                User admin = user_m.findHashMap(new User(adm_id, "", "", true));
                User userFound = admin.user_m.findAVL(newUser);
                if(userFound==null||userFound.id!=newId) {
                    admin.user_m.addUserAVL(newUser);
                    error.setVisibility(View.INVISIBLE);
                    success.setVisibility(View.VISIBLE);
                    Intent intent = new Intent(this, UserView.class);
                    intent.putExtra("admin_id", admin.id);
                    intent.putExtra("user_name", user_n);
                    startActivity(intent);
                }
                else {
                    success.setVisibility(View.INVISIBLE);
                    error.setText("ID de usuario ya registrada");
                    error.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public boolean checkFields() {
        String user_id = id_et.getText().toString();
        String user_n = username_et.getText().toString();
        String password = password_et.getText().toString();
        TextView error = findViewById(R.id.create_error);
        if(!user_n.matches("")&&!password.matches("")&&!user_id.matches("")) {
            return true;
        }
        else {
            error.setText("Rellene todos los campos");
            error.setVisibility(View.VISIBLE);
            return false;
        }
    }

}
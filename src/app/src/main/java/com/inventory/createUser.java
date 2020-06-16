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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
    }

    public void addUser_da(View v){
        create_button = (Button)findViewById(R.id.create_user_button_da);
        id_et = (EditText)findViewById(R.id.create_id);
        username_et = (EditText)findViewById(R.id.create_user);
        password_et = (EditText)findViewById(R.id.create_password);

        long newId = Long.parseLong(id_et.getText().toString());
        String newUserN = username_et.getText().toString();
        String newPass = password_et.getText().toString();
        User newUser = new User(newId,newUserN,newPass);


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
        User newUser = new User(newId, newUserN, newPass);
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

        long newId = Long.parseLong(id_et.getText().toString());
        String newName = username_et.getText().toString();
        String newPass = password_et.getText().toString();
        User newUser = new User(newId, newName, newPass);
        user_management user_m = user_management.getInstance();
        TextView error = findViewById(R.id.create_error);
        TextView success = findViewById(R.id.create_success);
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
            error.setVisibility(View.VISIBLE);
        }
    }

}
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

        int newId = Integer.parseInt(id_et.getText().toString());
        String newUser = username_et.getText().toString();
        String newPass = password_et.getText().toString();

        //userList_da.add(new User(id,name,pass));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
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
        TextView error = findViewById(R.id.creation_error);
        TextView success = findViewById(R.id.creation_success);
        User userFound = user_m.findAVL(newUser);
        if(userFound==null||userFound.id!=newId) {
            user_m.addUserAVL(newUser);
            success.setVisibility(View.VISIBLE);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            error.setVisibility(View.VISIBLE);
        }
    }

    public void addUser_map(View v){

    }


}
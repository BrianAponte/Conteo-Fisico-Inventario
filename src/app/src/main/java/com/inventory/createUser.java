package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

        user_management um = new user_management();
        um.addUser(newId,newUser,newPass);

        //userList_da.add(new User(id,name,pass));
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void addUser_avl(View v){

    }

    public void addUser_map(View v){

    }


}
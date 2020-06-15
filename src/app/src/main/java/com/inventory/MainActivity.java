package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button login_button;
    EditText username_et, password_et;
    TextView create_user_tv;
    D_ArrayImp<User> userList_da;
    String username,password;

    public static final String EXTRA_MESSAGE = "com.inventory.MESSAGE";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = (Button)findViewById(R.id.Login_button_da);
        username_et = (EditText)findViewById(R.id.UserId);
        password_et = (EditText)findViewById(R.id.Password);


        userList_da = new D_ArrayImp<>();

    }

    public void addUser(View v){
        Intent intent = new Intent(this, createUser.class);
        startActivity(intent);
    }


    public void login_da(View v) {

        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = username_et.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void login_avl(View v){

    }

    public void login_map(View v){

    }
}

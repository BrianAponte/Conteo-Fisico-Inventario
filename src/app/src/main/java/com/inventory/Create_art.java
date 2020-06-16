package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Create_art extends AppCompatActivity {
    String user_n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_art);
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");
    }

    public void addProd(View v) {}

    public void go_back(View v){
        Intent i = new Intent(this, DisplayMessageActivity.class);
        i.putExtra("user_name", user_n);
        startActivity(i);
    }
}

package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class View_art extends AppCompatActivity {
    String user_n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_art);
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");
    }

    public void filter(View v){}

    public void back_view(View v) {
        Intent i = new Intent(this, DisplayMessageActivity.class);
        i.putExtra("user_name", user_n);
        startActivity(i);
    }
}

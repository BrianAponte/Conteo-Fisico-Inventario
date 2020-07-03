package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inventoryDetails extends AppCompatActivity {
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        Intent i = getIntent();
        id = i.getIntExtra("inventory_id", 0);
    }

    public void createArt(View v) {
        Intent intent = new Intent(this, Create_art.class);
        //intent.putExtra("user_name", user_n);
        intent.putExtra("inventory_id", id);
        startActivity(intent);
    }

    public void showArt(View v) {
        Intent intent = new Intent(this, View_art.class);
        //intent.putExtra("user_name", user_n);
        intent.putExtra("inventory_id", id);
        startActivity(intent);
    }
}
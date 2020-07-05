package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inventoryDetails extends AppCompatActivity {
    boolean perms;
    int id;
    String user_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_details);
        Intent i = getIntent();
        id = i.getIntExtra("inventory_id", 0);
        perms = i.getBooleanExtra("has_perms", true);
        user_n = i.getStringExtra("user_name");
    }

    public void createArt(View v) {
        Intent intent = new Intent(this, Create_art.class);
        //intent.putExtra("user_name", user_n);
        intent.putExtra("inventory_id", id);
        intent.putExtra("has_perms", perms);
        intent.putExtra("user_name", user_n);
        startActivity(intent);
    }

    public void showArt(View v) {
        Intent intent = new Intent(this, View_art.class);
        //intent.putExtra("user_name", user_n);
        intent.putExtra("inventory_id", id);
        intent.putExtra("has_perms", perms);
        intent.putExtra("user_name", user_n);
        startActivity(intent);
    }

    public void backToInv(View v) {
        Intent intent = new Intent(this, inventoryView.class);
        intent.putExtra("has_perms", perms);
        intent.putExtra("user_name", user_n);
        startActivity(intent);
    }
}
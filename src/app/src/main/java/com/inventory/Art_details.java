package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Art_details extends AppCompatActivity {
    String prod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_details);

        Intent i = getIntent();
        prod = i.getStringExtra("prod_name");

        TextView tv = (TextView) findViewById(R.id.art_name);
        tv.setText(prod);
    }
}

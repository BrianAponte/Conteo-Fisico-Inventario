package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        user_management user_m = user_management.getInstance();
        String avl = "Actualmente hay "+user_m.AVLUsers+" usuarios AVL";
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.greeting);
        TextView avl_users = findViewById(R.id.avl_users);
        textView.setText(message);
        avl_users.setText(avl);
    }

}

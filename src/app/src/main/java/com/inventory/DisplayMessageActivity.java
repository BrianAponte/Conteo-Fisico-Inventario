package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    String user_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");
        String message = "¡Bienvenido "+user_n+"!";
        user_management user_m = user_management.getInstance();
        String avl = "Actualmente hay "+user_m.AVLUsers+" usuarios AVL";
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.greeting);
        TextView avl_users = findViewById(R.id.avl_users);
        textView.setText(message);
        avl_users.setText(avl);
    }

    public void createArt(View v) {
        Intent intent = new Intent(this, add_art.class);
        //intent.putExtra("user_name", this.user_n);
        startActivity(intent);
    }

    public void showArt(View v) {
        Intent intent = new Intent(this, Vista_Articulos.class);
        startActivity(intent);
    }

}

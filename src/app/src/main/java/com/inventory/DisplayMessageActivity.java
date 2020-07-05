package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    String user_n;
    long adm_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        //Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");
        adm_id = intent.getLongExtra("admin_id", 0);
        String message = "¡Bienvenido "+user_n+"!";
        user_management user_m = user_management.getInstance();
        String avl = "Actualmente hay "+user_m.HashMapUsers+" usuarios MAP";
        //Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.greeting);
        TextView avl_users = findViewById(R.id.avl_users);
        textView.setText(message);
        avl_users.setText(avl);
    }

    public void goToInventory(View v){
        Intent intent = new Intent(this, inventoryView.class);
        startActivity(intent);
    }

    public void goToUserM(View v) {
        Intent intent = new Intent(this, UserView.class);
        intent.putExtra("admin_id", adm_id);
        startActivity(intent);
    }

    public void logout(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}

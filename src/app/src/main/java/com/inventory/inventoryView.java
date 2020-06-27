package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class inventoryView extends AppCompatActivity {
    String userN;
    inventoryManagement im;
    private static inventoryManagement myInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_view);

        Intent intent = getIntent();

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout);
        im = inventoryManagement.getInstance();

        D_ArrayImp<Inventory> inventories = im.inventarios;

        for (int i = 0 ; i<inventories.getLen();i++){
            int id = inventories.get(i).id;
            TextView tv = new TextView(this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tv.setText(String.valueOf(id));
            tv.setTextSize(20);
            tv.setClickable(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(inventoryView.this, View_art.class);
                    startActivity(intent);
                }
            });
            myLayout.addView(tv);
        }
    }


    public void addInventory(View v) throws Throwable {
        inventoryManagement im = inventoryManagement.getInstance();
        im.addInventory();
        startActivity(getIntent());
        //this.onCreate();
    }
}


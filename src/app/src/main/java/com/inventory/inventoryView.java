package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
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
        displayInv();


        /*for (int i = 0 ; i<inventories.getLen();i++){
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
        }*/
    }


    public void addInventory(View v) throws Throwable {
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.inv_layout);
        myLayout.removeAllViews();
        im.addInventory();
        displayInv();
    }

    public void displayInv(){
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.inv_layout);
        D_ArrayImp<Inventory> inv = inventoryManagement.getInstance().inventarios;

        for(int i=0;i<inv.getLen();i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(View.TEXT_ALIGNMENT_CENTER);
            final int id = inv.get(i).id;
            final String inv_m = "Inventario "+id;
            TextView tv = new TextView(this);
            ImageButton im = new ImageButton(this);
            LinearLayout.LayoutParams lay_params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            tv.setLayoutParams(lay_params);
            tv.setText(inv_m);
            tv.setTextSize(24);
            tv.setWidth(650);
            tv.setHeight(110);
            tv.setGravity(View.TEXT_ALIGNMENT_CENTER);

            im.setImageResource(R.drawable.ic_add);
            im.setBackgroundColor(0);
            lay_params.height = 110;
            im.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(inventoryView.this, inventoryDetails.class);
                    intent.putExtra("inventory_id", id);
                    startActivity(intent);
                }
            });
            row.addView(tv);
            lay_params.setMargins(20, 0, 0, 0);
            row.addView(im, lay_params);
            myLayout.addView(row);
        }

    }
}


package com.inventory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class inventoryView extends AppCompatActivity {
    inventoryManagement im;
    boolean perms;
    private static inventoryManagement myInstance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_view);
        Intent intent = getIntent();
        perms = intent.getBooleanExtra("has_perms", true);
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout);
        im = inventoryManagement.getInstance();
        displayInv();

        ImageView im = (ImageView) findViewById(R.id.add);
        TextView tv = (TextView) findViewById(R.id.back_display_message);
        if(!perms) {
            im.setVisibility(View.INVISIBLE);
            im.setClickable(false);
            tv.setBackgroundResource(R.drawable.ic_cerrar_sesion);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(inventoryView.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    public void addInventory(String name) {
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.inv_layout);
        myLayout.removeAllViews();
        im.addInventory(name);
        displayInv();
    }

    public void createInventory(View v) throws Throwable {
        DialogFragment nn = new CreateInventory();
        nn.show(getSupportFragmentManager(), "Crear Inventario");
    }

    public void displayInv(){
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.inv_layout);
        D_ArrayImp<Inventory> inv = inventoryManagement.getInstance().inventarios;

        for(int i=0;i<inv.getLen();i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(View.TEXT_ALIGNMENT_CENTER);
            final int id = inv.get(i).id;
            final String inv_m = inv.get(i).id + " " + inv.get(i).name;
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
                    if(perms) {
                        Intent intent = new Intent(inventoryView.this, inventoryDetails.class);
                        intent.putExtra("inventory_id", id);
                        intent.putExtra("has_perms", perms);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(inventoryView.this, View_art.class);
                        intent.putExtra("inventory_id", id);
                        intent.putExtra("has_perms", perms);
                        startActivity(intent);
                    }
                }
            });
            row.addView(tv);
            lay_params.setMargins(20, 0, 0, 0);
            row.addView(im, lay_params);
            myLayout.addView(row);
        }
    }

    public void backToDisplayMessage(View v) {
        Intent i = new Intent(this, DisplayMessageActivity.class);
        startActivity(i);
    }

}


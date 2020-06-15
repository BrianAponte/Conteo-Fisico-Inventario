package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Vista_Articulos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista__articulos);

        LinearLayout mLayout=(LinearLayout)findViewById(R.id.art_layout);
        art_management a_m = art_management.getInstance();
        D_ArrayImp<Product> products = a_m.getArt();
        for(int i=0; i<products.getLen();i++) {
            TextView currentP = new TextView(Vista_Articulos.this);
            currentP.setText(products.get(i).name);
            mLayout.addView(currentP);
        }
    }
}

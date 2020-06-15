package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class add_art extends AppCompatActivity {
    Button create_prod, back_userM;
    EditText create_sku, create_artN,create_category, create_stock, create_count;
    String user_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_art);
        Intent i = getIntent();
        user_n = i.getStringExtra("user_name");
    }

    public void addArt(View v) {
        create_prod = (Button) findViewById(R.id.create_prod);
        back_userM = (Button) findViewById(R.id.back_userM);
        create_sku = (EditText) findViewById(R.id.create_sku);
        create_artN = (EditText) findViewById(R.id.create_artN);
        create_category = (EditText) findViewById(R.id.create_category);
        create_stock = (EditText) findViewById(R.id.create_stock);
        create_count = (EditText) findViewById(R.id.create_count);
        TextView error = findViewById(R.id.prod_error);
        TextView success = findViewById(R.id.prod_success);

        String sku = create_sku.getText().toString();
        String name = create_artN.getText().toString();
        String category= create_category.getText().toString();
        String stock = create_stock.getText().toString();
        String count = create_count.getText().toString();
        if(!sku.matches("")&&!name.matches("")&&!category.matches("")&&!stock.matches("")&&!count.matches("")) {
            art_management a_m = art_management.getInstance();
            Product newProd = new Product(sku, name, category, Integer.parseInt(stock), Integer.parseInt(count));
            if(a_m.amount_of_art==0) {
                a_m.add_art(newProd);
                error.setVisibility(View.INVISIBLE);
                success.setVisibility(View.VISIBLE);
            }
            else {
                Product product_found = a_m.findProd(newProd);
                if(product_found==null||!product_found.name.matches(name)) {
                    a_m.add_art(newProd);
                    error.setVisibility(View.INVISIBLE);
                    success.setVisibility(View.VISIBLE);
                }
                else {
                    error.setText("Producto ya registrado");
                    success.setVisibility(View.INVISIBLE);
                    error.setVisibility(View.VISIBLE);
                }
            }
        }
        else {
            error.setText("Rellene todos los campos");
            success.setVisibility(View.INVISIBLE);
            error.setVisibility(View.VISIBLE);
        }
    }

    public void back(View v) {
        Intent i = getIntent();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        intent.putExtra("user_name", user_n);
        startActivity(intent);
    }
}

package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Create_prod extends AppCompatActivity {
    String user_n;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_prod);
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");
    }

    public void addProd(View v) {
        art_management a_m = art_management.getInstance();
        TextView success = findViewById(R.id.success_t);
        TextView error = findViewById(R.id.error_t);
        String sku = ((EditText) findViewById(R.id.new_sku)).getText().toString();
        String name = ((EditText) findViewById(R.id.new_prod)).getText().toString();
        String category = ((EditText) findViewById(R.id.new_category)).getText().toString();
        String stock = ((EditText) findViewById(R.id.new_stock)).getText().toString();
        String count = ((EditText) findViewById(R.id.new_count)).getText().toString();
        if(!sku.matches("")&&!name.matches("")&&!category.matches("")&&!stock.matches("")&&!count.matches("")) {
            Product newProd = new Product(sku, name, category, Integer.parseInt(stock), Integer.parseInt(count));
            if(a_m.amount_of_art==0) {
                a_m.add_art(newProd);
                error.setVisibility(View.INVISIBLE);
                success.setText("Producto añadido, hay "+a_m.amount_of_art +" productos");
                success.setVisibility(View.VISIBLE);
            }
            else {
                Product productFound = a_m.findProd(newProd);
                if(productFound==null||!productFound.sku.matches(sku)) {
                    a_m.add_art(newProd);
                    error.setVisibility(View.INVISIBLE);
                    success.setText("Producto añadido, hay "+a_m.amount_of_art +" productos");
                    success.setVisibility(View.VISIBLE);
                }
                else {
                    success.setVisibility(View.INVISIBLE);
                    error.setText("Rellene todos los campos");
                    error.setVisibility(View.VISIBLE);
                }
            }
        }
        else {
            success.setVisibility(View.INVISIBLE);
            error.setText("Rellene todos los campos");
            error.setVisibility(View.VISIBLE);
        }
    }

    public void go_back(View v) {
        Intent i = new Intent(this, DisplayMessageActivity.class);
        i.putExtra("user_name", user_n);
        startActivity(i);
    }
}

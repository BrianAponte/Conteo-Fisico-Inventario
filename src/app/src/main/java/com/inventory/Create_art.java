package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Create_art extends AppCompatActivity {

    String user_n;
    boolean perms;
    int inventoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_art);
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");
        inventoryId = intent.getIntExtra("inventory_id", 1);
        perms = intent.getBooleanExtra("has_perms", true);
    }

    public void addProd(View v) {

        inventoryManagement i_m = inventoryManagement.getInstance();
        TextView success = findViewById(R.id.success_art);
        TextView error = findViewById(R.id.error_art);
        String sku = ((EditText) findViewById(R.id.new_sku)).getText().toString();
        String name = ((EditText) findViewById(R.id.new_prod)).getText().toString();
        String category = ((EditText) findViewById(R.id.new_category)).getText().toString();
        String stock = ((EditText) findViewById(R.id.new_stock)).getText().toString();
        String count = ((EditText) findViewById(R.id.new_count)).getText().toString();

        ((EditText) findViewById(R.id.new_sku)).setText("");
        ((EditText) findViewById(R.id.new_prod)).setText("");
        ((EditText) findViewById(R.id.new_category)).setText("");
        ((EditText) findViewById(R.id.new_stock)).setText("");
        ((EditText) findViewById(R.id.new_count)).setText("");

        if(!sku.matches("")&&!name.matches("")&&!category.matches("")
                &&!stock.matches("")&&!count.matches("")) {
            if(isAlph(name)) {
                Product newProd = new Product(sku, name, category, Integer.parseInt(stock), Integer.parseInt(count));
                art_management a_m = i_m.inventarios.get(inventoryId-1).products;
                if(a_m.amount_of_art==0) {
                    a_m.add_art(newProd);
                    error.setVisibility(View.INVISIBLE);
                    success.setText("Producto añadido, actualmente hay "+a_m.amount_of_art +" productos");
                    success.setVisibility(View.VISIBLE);
                }
                else {
                    Product productFound = a_m.findProd(newProd);
                    if(productFound==null||!productFound.name.matches(name)) {
                        a_m.add_art(newProd);
                        error.setVisibility(View.INVISIBLE);
                        success.setText("Producto añadido, actualmente hay "+a_m.amount_of_art +" productos");
                        success.setVisibility(View.VISIBLE);
                    }
                    else {
                        success.setVisibility(View.INVISIBLE);
                        error.setText("Nombre de artículo ya registrado");
                        error.setVisibility(View.VISIBLE);
                    }
                }
            }
            else {
                success.setVisibility(View.INVISIBLE);
                error.setText("El nombre sólo puede contener caracteres alfabéticos y espacios");
                error.setVisibility(View.VISIBLE);
            }

        }
        else {
            success.setVisibility(View.INVISIBLE);
            error.setText("Rellene todos los campos");
            error.setVisibility(View.VISIBLE);
        }
    }

    public void go_back(View v){
        Intent i = new Intent(this, inventoryDetails.class);
        i.putExtra("user_name", user_n);
        i.putExtra("inventory_id", inventoryId);
        i.putExtra("has_perms", perms);
        startActivity(i);
    }

    public boolean isAlph(String s) {
        char[] chars = s.toCharArray();
        for(char c:chars) {
            if(!Character.isAlphabetic(c)&&c!=' ') {
                return false;
            }
        }
        return true;
    }
}

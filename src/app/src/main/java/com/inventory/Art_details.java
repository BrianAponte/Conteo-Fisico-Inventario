package com.inventory;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Art_details extends AppCompatActivity {
    String prod_name;
    Product prod;
    art_management am;
    int inventoryId;
    boolean perms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_art_details);

        Intent i = getIntent();
        prod_name = i.getStringExtra("prod_name");
        inventoryId = i.getIntExtra("inventory_id", 1);
        perms = i.getBooleanExtra("has_perms", true);

        am = inventoryManagement.getInstance().inventarios.get(inventoryId-1).products;
        prod = am.findProd(new Product("", prod_name, "", 0, 0));

        if(!perms) {
            TextView delete = (TextView) findViewById(R.id.delete_ar);
            delete.setClickable(false);
            delete.setVisibility(View.INVISIBLE);
        }

        displayProdCharacteristics();
    }

    public void modifyArt(View v) {
        TextView success = findViewById(R.id.success_am);
        TextView error = findViewById(R.id.error_am);
        success.setVisibility(View.INVISIBLE);
        error.setVisibility(View.INVISIBLE);

        TextView edit = (TextView) findViewById(R.id.edit);
        edit.setVisibility(View.INVISIBLE);
        edit.setClickable(false);
        TextView discard = (TextView) findViewById(R.id.discard);
        discard.setVisibility(View.VISIBLE);
        discard.setClickable(true);
        TextView confirm = (TextView) findViewById(R.id.confirm);
        confirm.setVisibility(View.VISIBLE);
        confirm.setClickable(true);

        if(perms) {
            EditText sku_edit = (EditText) findViewById(R.id.sku_edtx);
            sku_edit.setEnabled(true);
            EditText name_edit = (EditText) findViewById(R.id.name_edtx);
            name_edit.setEnabled(true);
            EditText category_edit = (EditText) findViewById(R.id.category_edtx);
            category_edit.setEnabled(true);
        }

        EditText stock_edit = (EditText) findViewById(R.id.stock_edtx);
        stock_edit.setEnabled(true);
        EditText count_edit = (EditText) findViewById(R.id.count_edtx);
        count_edit.setEnabled(true);
    }

    public void saveArtChanges(View v) {
        EditText sku_edit = (EditText) findViewById(R.id.sku_edtx);
        EditText name_edit = (EditText) findViewById(R.id.name_edtx);
        EditText category_edit = (EditText) findViewById(R.id.category_edtx);
        EditText stock_edit = (EditText) findViewById(R.id.stock_edtx);
        EditText count_edit = (EditText) findViewById(R.id.count_edtx);

        String sku = sku_edit.getText().toString();
        String name = name_edit.getText().toString();
        String category = category_edit.getText().toString();
        String stock = stock_edit.getText().toString();
        String count = count_edit.getText().toString();
        TextView success = findViewById(R.id.success_am);
        TextView error = findViewById(R.id.error_am);

        if(!sku.matches("")&&!name.matches("")&&!category.matches("")
                &&!stock.matches("")&&!count.matches("")) {
            if(isAlph(name)) {

                if(name.matches(prod_name)) {
                    prod.sku = sku;
                    prod.category = category;
                    prod.stock = Integer.parseInt(stock);
                    prod.counted = Integer.parseInt(count);
                }
                else {
                    Product newProd = new Product(sku, name, category, Integer.parseInt(stock), Integer.parseInt(count));
                    am.updateArt(prod, newProd);
                    prod = newProd;
                    prod_name = name;
                }

                error.setVisibility(View.INVISIBLE);
                success.setVisibility(View.VISIBLE);
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

        sku_edit.setEnabled(false);
        name_edit.setEnabled(false);
        category_edit.setEnabled(false);
        stock_edit.setEnabled(false);
        count_edit.setEnabled(false);

        TextView edit = (TextView) findViewById(R.id.edit);
        edit.setVisibility(View.VISIBLE);
        edit.setClickable(true);
        TextView discard = (TextView) findViewById(R.id.discard);
        discard.setVisibility(View.INVISIBLE);
        discard.setClickable(false);
        TextView confirm = (TextView) findViewById(R.id.confirm);
        confirm.setVisibility(View.INVISIBLE);
        confirm.setClickable(false);

        displayProdCharacteristics();

    }

    public void discardArtChanges(View v) {
        TextView edit = (TextView) findViewById(R.id.edit);
        edit.setVisibility(View.VISIBLE);
        edit.setClickable(true);
        TextView discard = (TextView) findViewById(R.id.discard);
        discard.setVisibility(View.INVISIBLE);
        discard.setClickable(false);
        TextView confirm = (TextView) findViewById(R.id.confirm);
        confirm.setVisibility(View.INVISIBLE);
        confirm.setClickable(false);

        EditText sku_edit = (EditText) findViewById(R.id.sku_edtx);
        sku_edit.setEnabled(false);
        EditText name_edit = (EditText) findViewById(R.id.name_edtx);
        name_edit.setEnabled(false);
        EditText category_edit = (EditText) findViewById(R.id.category_edtx);
        category_edit.setEnabled(false);
        EditText stock_edit = (EditText) findViewById(R.id.stock_edtx);
        stock_edit.setEnabled(false);
        EditText count_edit = (EditText) findViewById(R.id.count_edtx);
        count_edit.setEnabled(false);

        displayProdCharacteristics();
    }

    public void displayProdCharacteristics() {
        EditText sku_edit = (EditText) findViewById(R.id.sku_edtx);
        EditText name_edit = (EditText) findViewById(R.id.name_edtx);
        EditText category_edit = (EditText) findViewById(R.id.category_edtx);
        EditText stock_edit = (EditText) findViewById(R.id.stock_edtx);
        EditText count_edit = (EditText) findViewById(R.id.count_edtx);
        sku_edit.setText(prod.sku);
        name_edit.setText(prod.name);
        category_edit.setText(prod.category);
        stock_edit.setText(Integer.toString(prod.stock));
        count_edit.setText(Integer.toString(prod.counted));
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

    public void backToArt(View v) {
        Intent i = new Intent(this, View_art.class);
        i.putExtra("inventory_id", inventoryId);
        i.putExtra("has_perms", perms);
        startActivity(i);
    }

    public void deleteArt(View v) {
        final Intent i = new Intent(this, View_art.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("ALERTA");
        builder.setMessage("¿Seguro deseas eliminar el artículo?")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // TODO: handle the OK
                        am.deleteArt(prod);
                        i.putExtra("inventory_id", inventoryId);
                        i.putExtra("has_perms", perms);
                        startActivity(i);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

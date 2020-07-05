package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class View_art extends AppCompatActivity {
    String user_n;
    art_management am;
    int inventoryId;
    boolean perms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_art);

        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");
        inventoryId = intent.getIntExtra("inventory_id", 1);
        perms = intent.getBooleanExtra("has_perms", true);

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout);
        am = inventoryManagement.getInstance().inventarios.get(inventoryId-1).products;
        D_ArrayImp<Product> prods = am.getArt();

        displayProds(prods);
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

    public void filter(View v){
        EditText filterBy = (EditText) findViewById(R.id.filter);
        String filter = filterBy.getText().toString();
        D_ArrayImp<Product> filtered = new D_ArrayImp<>();
        TextView error = (TextView) findViewById(R.id.filter_error);
        boolean doFilter;

        if(filter.matches("")) {
            error.setVisibility(View.INVISIBLE);
            filtered = am.getArt();
            doFilter = true;

        }
        else if (!isAlph(filter)){
            doFilter = false;
            error.setVisibility(View.VISIBLE);
        }
        else {
            error.setVisibility(View.INVISIBLE);
            filtered = am.Filter(filter);
            doFilter = true;
        }

        if(doFilter) {
            LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout);
            myLayout.removeAllViews();
            displayProds(filtered);
        }

    }

    public void displayProds(D_ArrayImp<Product> prods) {
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout);

        for(int i=0;i<prods.getLen();i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(View.TEXT_ALIGNMENT_CENTER);

            final String product = prods.get(i).name;
            TextView tv = new TextView(this);
            ImageButton im = new ImageButton(this);
            LinearLayout.LayoutParams lay_params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            tv.setLayoutParams(lay_params);
            tv.setText(product);
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
                    Intent intent = new Intent(View_art.this, Art_details.class);
                    intent.putExtra("prod_name", product);
                    intent.putExtra("inventory_id", inventoryId);
                    intent.putExtra("has_perms", perms);
                    startActivity(intent);
                }
            });
            row.addView(tv);
            lay_params.setMargins(20, 0, 0, 0);
            row.addView(im, lay_params);
            myLayout.addView(row);
        }
    }

    public void back_view(View v) {
        Intent i = new Intent(this, inventoryDetails.class);
        i.putExtra("user_name", user_n);
        i.putExtra("inventory_id", inventoryId);
        i.putExtra("has_perms", perms);
        startActivity(i);
    }
}

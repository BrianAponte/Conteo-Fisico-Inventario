package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class View_art extends AppCompatActivity {
    String user_n;
    art_management am;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_art);
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout);
        am = art_management.getInstance();
        D_ArrayImp<Product> prods = am.getArt();
        for(int i=0;i<prods.getLen();i++) {
            final String product = prods.get(i).name;
            TextView tv = new TextView(this);
            tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            tv.setText(product);
            tv.setTextSize(20);
            tv.setClickable(true);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(View_art.this, Art_details.class);
                    intent.putExtra("prod_name", product);
                    startActivity(intent);
                }
            });
            myLayout.addView(tv);
        }
    }

    public boolean isAlph(String s) {
        char[] chars = s.toCharArray();
        for(char c:chars) {
            if(!Character.isAlphabetic(c)) {
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
            System.out.print("dunno bro");
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
            for(int i=0;i<filtered.getLen();i++) {
                final String product = filtered.get(i).name;
                TextView tv = new TextView(this);
                tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                tv.setText(product);
                tv.setTextSize(20);
                tv.setClickable(true);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(View_art.this, Art_details.class);
                        intent.putExtra("prod_name", product);
                        startActivity(intent);
                    }
                });
                myLayout.addView(tv);
            }
        }

    }

    public void back_view(View v) {
        Intent i = new Intent(this, DisplayMessageActivity.class);
        i.putExtra("user_name", user_n);
        startActivity(i);
    }
}

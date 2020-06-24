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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_art);
        Intent intent = getIntent();
        user_n = intent.getStringExtra("user_name");

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout);
        art_management am = art_management.getInstance();
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

    public void filter(View v){
        EditText filterBy = (EditText) findViewById(R.id.filter);

        //Hacer una pequeña función que retorne la siguiente cadena lexicograficamente mayor
        //que no esté incluida en el filtro, ej: filtro="a", siguiente="b". Con esto, llamar
        //la función Filter() de art_management con ambas cadenas como parámetros y con la lista
        //que retorna hacer el mismo proceso que al iniciar la vista
    }

    public void back_view(View v) {
        Intent i = new Intent(this, DisplayMessageActivity.class);
        i.putExtra("user_name", user_n);
        startActivity(i);
    }
}

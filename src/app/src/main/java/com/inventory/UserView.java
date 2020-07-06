package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UserView extends AppCompatActivity {
    long adm_id;
    user_management um;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        Intent intent = getIntent();
        user_management u_m = user_management.getInstance();
        adm_id = u_m.adminId;
        User admin = user_management.getInstance().findHashMap(new User(adm_id, "", "", true));
        um = admin.user_m;
        D_ArrayImp<User> users = um.getInOrderAVL();
        displayUsers(users);
    }

    public void filter(View v){
        EditText filterBy = (EditText) findViewById(R.id.filter_u);
        String filter = filterBy.getText().toString();
        D_ArrayImp<User> filtered = new D_ArrayImp<>();

        if(filter.matches("")) {
            filtered = um.getInOrderAVL();

        }
        else {
            filtered = um.filterAVL(Integer.parseInt(filter));
        }

        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout_u);
        myLayout.removeAllViews();
        displayUsers(filtered);

    }

    public void displayUsers(D_ArrayImp<User> users) {
        LinearLayout myLayout = (LinearLayout) findViewById(R.id.layout_u);

        for(int i=0;i<users.getLen();i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(View.TEXT_ALIGNMENT_CENTER);

            final long id = users.get(i).id;
            TextView tv = new TextView(this);
            ImageButton im = new ImageButton(this);
            LinearLayout.LayoutParams lay_params =new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            tv.setLayoutParams(lay_params);
            tv.setText(String.valueOf(id));
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
                    Intent intent = new Intent(UserView.this, user_details.class);
                    intent.putExtra("user_id", id);
                    intent.putExtra("admin_id", adm_id);
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
        Intent i = new Intent(this, DisplayMessageActivity.class);
        startActivity(i);
    }

    public void createU(View v) {
        Intent i = new Intent(this, createUser.class);
        i.putExtra("has_perms", false);
        startActivity(i);
    }
}
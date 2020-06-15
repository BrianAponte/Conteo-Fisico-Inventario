package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button login_button;
    EditText username_et, password_et;
    TextView create_user_tv;
    D_ArrayImp<User> userList_da;
    String username,password;

    public static final String EXTRA_MESSAGE = "com.inventory.MESSAGE";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = (Button)findViewById(R.id.Login_button_da);
        username_et = (EditText)findViewById(R.id.UserId);
        password_et = (EditText)findViewById(R.id.Password);
        userList_da = new D_ArrayImp<>();
    }

    public void addUser(View v){
        Intent intent = new Intent(this, createUser.class);
        startActivity(intent);
    }


    public void login_da(View v) {
        String user_n = username_et.getText().toString();
        String password = password_et.getText().toString();
        TextView error = findViewById(R.id.error_text);
        if(!user_n.matches("")&&!password.matches("")) {
            long user =  Long.parseLong(username_et.getText().toString());
            user_management user_m = user_management.getInstance();
            if(!(user_m.DAUsers == 0)){

                for(int i = 0; i<user_m.DAUsers;i++){
                    User u = user_m.user_list.get(i);
                    if(u.id == user){
                        if(u.pass == password){
                            //user exists and uN and Pass are ok
                            Intent intent = new Intent(this, DisplayMessageActivity.class);
                            String message = "Bienvenido " + u.name + "!";
                            intent.putExtra(EXTRA_MESSAGE, message);
                            startActivity(intent);
                        } else {
                            //contraseña incorrecta
                            error.setText("Contraseña incorrecta");
                            error.setVisibility(View.VISIBLE);
                        }

                    } else {
                        //id no registrado
                        error.setText("ID de usuario no está registrado");
                        error.setVisibility(View.VISIBLE);
                    }
                }

            } else {
                error.setText("No existen usuarios DA");
                error.setVisibility(View.VISIBLE);
            }

        } else {
            error.setText("Rellene todos los campos");
            error.setVisibility(View.VISIBLE);
        }
    }

    public void login_avl(View v){
        String user_n = username_et.getText().toString();
        String password = password_et.getText().toString();
        TextView error = findViewById(R.id.error_text);
        if(!user_n.matches("")&&!password.matches("")) {
            long user =  Long.parseLong(username_et.getText().toString());
            user_management user_m = user_management.getInstance();

            if(user_m.AVLUsers!=0){
                User userFound = user_m.findAVL(new User(user, "", password));
                if(user==userFound.id) {
                    if(password.matches(userFound.pass)) {
                        Intent intent = new Intent(this, DisplayMessageActivity.class);
                        String message = user_m.greetAVLUser(userFound);
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                    else {
                        error.setText("Contraseña incorrecta");
                        error.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    error.setText("ID de usuario no está registrado");
                    error.setVisibility(View.VISIBLE);
                }
            }
            else {
                error.setText("No existen usuarios AVL");
                error.setVisibility(View.VISIBLE);
            }
        }
        else {
            error.setText("Rellene todos los campos");
            error.setVisibility(View.VISIBLE);
        }
    }

    public void login_map(View v){

    }
}

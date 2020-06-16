package com.inventory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.*;

import java.util.Random;

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
        //userList_da = new D_ArrayImp<>();

        user_management um = user_management.getInstance();
        //lena el da
        for(long i = 0;i<999;i++){
            um.addUser(new User(i, "da" , "da"));
        }
        um.addUser(new User(1005105349, "Carlos Jimenez", "clave"));

        for(long i = 0;i<1000;i++){
            um.addUserAVL(new User(i, "avl", "avl"));
        }
        um.addUserAVL(new User(1005105349 , "Carlos Jimenez" , "clave"));
        /*for(long i = 0;i<1000;i++){
            um.addUserHashMap(new User(i,"hm","hm"));
        }
        um.addUserHashMap(new User(1005105349 , "Carlos Jimenez" ,"clave"));*/
    }

    public void addUser(View v){
        Intent intent = new Intent(this, createUser.class);
        startActivity(intent);
    }


    public void login_da(View v) {
        long time_start, time_end;
        time_start = System.nanoTime();


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
                        if(u.pass.matches(password)){
                            //user exists and uN and Pass are ok
                            Intent intent = new Intent(this, DisplayMessageActivity.class);
                            
                            intent.putExtra("user_name", u.name);
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
        time_end = System.nanoTime();
        System.out.println("time to login with d-a: " + (time_end-time_start));
    }

    public void login_avl(View v){

        long time_start, time_end;
        time_start = System.nanoTime();

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
                        intent.putExtra("user_name", userFound.name);
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
        time_end = System.nanoTime();
        System.out.println("time to login with avl: " + (time_end-time_start));
    }

    public void login_map(View v){
        long time_start, time_end, startTime;
        time_start = System.nanoTime();
        startTime = time_start;

        String user_n = username_et.getText().toString();
        String password = password_et.getText().toString();
        TextView error = findViewById(R.id.error_text);


        if(!user_n.matches("")&&!password.matches("")) {
            long user =  Long.parseLong(username_et.getText().toString());
            user_management user_m = user_management.getInstance();
            System.out.println(user_m.HashMapUsers);
            if(user_m.HashMapUsers != 0){
                User userFound = user_m.findHashMap(new User(user, "", password));
                if(userFound != null) {
                    if(password.matches(userFound.pass)) {
                        Intent intent = new Intent(this, DisplayMessageActivity.class);
                        String message = "Bienvenido? nop " + userFound.id;
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                    else {
                        error.setText("Contraseña incorrecta" + "  Time: " + (System.nanoTime() - startTime));
                        error.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    error.setText("ID de usuario no está registrado" + "  Time: " + (System.nanoTime() - startTime));
                    error.setVisibility(View.VISIBLE);
                }
            }
            else {
                error.setText("No existen usuarios HashMap" + "  Time: " + (System.nanoTime() - startTime));
                error.setVisibility(View.VISIBLE);
            }
        }
        else {
            error.setText("Rellene todos los campos" + "  Time: " + (System.nanoTime() - startTime));
            error.setVisibility(View.VISIBLE);
        }
        time_end = System.nanoTime();
        System.out.println("time to login with avl: " + (time_end-time_start));
    }
}

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
    boolean perms;

    public static final String EXTRA_MESSAGE = "com.inventory.MESSAGE";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_button = (Button)findViewById(R.id.Login_button_da);
        username_et = (EditText)findViewById(R.id.UserId);
        password_et = (EditText)findViewById(R.id.Password);
        //userList_da = new D_ArrayImp<>();
        perms = true;
        user_management um = user_management.getInstance();
        //lena el da
        /*for(long i = 1;i<=100000000;i++){
            um.addUser(new User(i, "da" , "da"));
        }
        um.addUser(new User(1005105349, "Carlos Jimenez", "clave"));*/

        /*for(long i = 1;i<=100;i++){
            um.addUserAVL(new User(i, "avl", "avl"));
        }
        um.addUserAVL(new User(1005105349 , "Carlos Jimenez" , "clave")); */
        /*
        for(long i = 0;i<1000;i++){
            um.addUserHashMap(new User(i,"hm","hm", true));
        }

         */
        //um.addUserHashMap(new User(1005105349 , "Carlos Jimenez" ,"clave"));
    }

    public void addUser(View v){
        Intent intent = new Intent(this, createUser.class);
        intent.putExtra("has_perms", true);
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
                            if(u.hasPerms) {
                                Intent intent = new Intent(this, DisplayMessageActivity.class);

                                intent.putExtra("user_name", u.name);
                                startActivity(intent);
                            }
                            else {
                                Intent intent = new Intent(this, inventoryView.class);
                                startActivity(intent);
                            }

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
                User userFound = user_m.findAVL(new User(user, "", password, false));
                if(user==userFound.id) {
                    if(password.matches(userFound.pass)) {
                        if(userFound.hasPerms) {
                            Intent intent = new Intent(this, DisplayMessageActivity.class);

                            intent.putExtra("user_name", userFound.name);
                            startActivity(intent);
                        }
                        else {
                            Intent intent = new Intent(this, inventoryView.class);
                            startActivity(intent);
                        }
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

        if(checkFields()) {
            long user =  Long.parseLong(username_et.getText().toString());
            user_management user_m = user_management.getInstance();
            User userFound;
            if(perms) {
                userFound = user_m.findHashMap(new User(user, "", "", true));
                if(userFound!=null) {
                    if(userFound.pass.matches(password)) {
                        user_m.currentUser = userFound;
                        user_m.adminId = userFound.id;
                        Intent intent = new Intent(this, DisplayMessageActivity.class);
                        intent.putExtra("admin_id", user);
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
                String admin_id = ((EditText)findViewById(R.id.admin_id)).getText().toString();
                User adminFound = user_m.findHashMap(new User(Long.parseLong(admin_id), "", "", false));
                if(adminFound!=null) {
                    userFound = adminFound.user_m.findAVL(new User(user, "", "", false));
                    if(userFound!=null&&userFound.id==user) {
                        if(userFound.pass.matches(password)) {
                            user_m.currentUser = userFound;
                            user_m.adminId = adminFound.id;
                            Intent intent = new Intent(this, inventoryView.class);
                            intent.putExtra("has_perms", false);
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
                    error.setText("ID de administrador no está registrada");
                    error.setVisibility(View.VISIBLE);
                }
            }
        }

        time_end = System.nanoTime();
        System.out.println("time to login with avl: " + (time_end-time_start));


    }

    public boolean checkFields() {
        String user_n = username_et.getText().toString();
        String password = password_et.getText().toString();
        EditText adm_field = (EditText) findViewById(R.id.admin_id);
        String adm_id = adm_field.getText().toString();
        TextView error = findViewById(R.id.error_text);
        if(!user_n.matches("")&&!password.matches("")) {
            if(!perms) {
                if(!adm_id.matches("")) {
                    return true;
                }
                error.setText("Rellene todos los campos");
                error.setVisibility(View.VISIBLE);
                return false;
            }
            return true;
        }
        else {
            error.setText("Rellene todos los campos");
            error.setVisibility(View.VISIBLE);
            return false;
        }
    }

    public void enableHiddenField(View v) {
        EditText adminId = (EditText) findViewById(R.id.admin_id);
        adminId.setEnabled(true);
        adminId.setVisibility(View.VISIBLE);
        perms = false;
    }
}

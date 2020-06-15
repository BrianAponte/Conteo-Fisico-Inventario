package com.inventory;

import android.content.res.TypedArray;

public class user_management {
    private static user_management myInstance = null;

    D_ArrayImp<User> user_list;
    AVLTreeImp<User> user_tree;
    int DAUsers, AVLUsers;

    public user_management(){
        user_list = new D_ArrayImp<>();
        user_tree = new AVLTreeImp<>();
        AVLUsers = 0;
    }

    public void addUser(long id, String name, String pass) {
        user_list.add(new User(id, name, pass));
        DAUsers++;
    }

    public void addUserAVL(User user) {
        user_tree.insert(user);
        AVLUsers++;
    }

    public int amountOfAVLUsers(){
        return AVLUsers;
    }

    public static synchronized user_management getInstance() {
        if(myInstance==null) {
            myInstance = new user_management();
        }
        return myInstance;
    }

    public User findAVL(User user) {
        return user_tree.findData(user);
    }

    public User findDA(User user){
        return user_list;
    }


    public String greetAVLUser(User user) {
        return"¡Bienvenido "+findAVL(user).name+"!";
    }
}

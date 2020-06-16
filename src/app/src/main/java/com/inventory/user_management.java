package com.inventory;

import android.content.res.TypedArray;

public class user_management {
    private static user_management myInstance = null;

    D_ArrayImp<User> user_list;
    AVLTreeImp<User> user_tree;
    HashMap<Long ,User> user_hashmap;
    int DAUsers, AVLUsers, HashMapUsers;

    public user_management(){
        user_list = new D_ArrayImp<>();
        user_tree = new AVLTreeImp<>();
        user_hashmap = new HashMap<>(10);
        AVLUsers = 0;
    }

    public void addUser(User user) {
        user_list.add(user);
        DAUsers++;
    }

    public void addUserAVL(User user) {
        user_tree.insert(user);
        AVLUsers++;
    }

    public void addUserHashMap(User user) {
        user_hashmap.add(user.id, user);
        HashMapUsers++;
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

    public User findHashMap(User user) {
        return user_hashmap.get(user.id);
    }


    public String greetAVLUser(User user) {
        return"¡Bienvenido "+findAVL(user).name+"!";
    }
}

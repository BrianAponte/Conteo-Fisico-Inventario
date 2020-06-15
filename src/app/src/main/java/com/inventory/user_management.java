package com.inventory;

import android.content.res.TypedArray;

public class user_management {
    D_ArrayImp<User> user_list = new D_ArrayImp<>();
    public void addUser(long id, String name, String pass) {
        user_list.add(new User(id, name, pass));
    }
}

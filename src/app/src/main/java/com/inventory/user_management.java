package com.inventory;

import android.content.res.TypedArray;

public class user_management {
    dArrayImp<User> user_list = new dArrayImp<>();
    public void addUser(long id, String name, String pass) {
        user_list.add(new User(id, name, pass));
    }
}

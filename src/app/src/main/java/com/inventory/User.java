package com.inventory;

public class User implements Comparable<User>{
    long id;
    String name;
    String pass;
    boolean hasPerms;
    user_management user_m;

    public User(long id, String name, String pass, boolean hasPerms) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.hasPerms = hasPerms;
        if(hasPerms) {
            user_m = new user_management();
        }
        else {
            user_m = null;
        }
    }

    public int compareTo(User other){
        return this.id>other.id?1:this.id==other.id?0:-1;
    }

}

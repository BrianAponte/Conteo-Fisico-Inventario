package com.inventory;

public class User implements Comparable<User>{
    long id;
    String name;
    String pass;

    public User(long id, String name, String pass) {
        this.id = id;
        this.name = name;
        this.pass = pass;
    }

    public int compareTo(User other){
        return this.id>other.id?1:this.id==other.id?0:-1;
    }

}

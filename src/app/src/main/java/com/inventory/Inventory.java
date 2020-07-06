package com.inventory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Inventory implements Comparable<Inventory> {

    int id;
    String name;
    Date date;
    art_management products;
    //ArrayList<Product> products;

    public Inventory(int id, String name) {
        this.id = id;
        this.name = name;
        this.date = Calendar.getInstance().getTime();
        this.products = new art_management();
    }

    public void addProduct(Product newProduct){
        this.products.add_art(newProduct);
    }

    @Override
    public int compareTo(Inventory o) {
        return 0;
    }
}

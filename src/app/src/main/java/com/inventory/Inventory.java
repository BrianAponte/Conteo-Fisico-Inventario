package com.inventory;

import java.util.ArrayList;
import java.util.Date;

public class Inventory {

    int id;
    Date date;
    AVLTreeImp<Product> products;
    //ArrayList<Product> products;

    public Inventory(int id, Date date, AVLTreeImp<Product> products) {
        this.id = id;
        this.date = date;
        this.products = products;
    }

    public void addProduct(Product newProduct){
        this.products.insert(newProduct);
    }
}

package com.inventory;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class inventoryManagement extends AppCompatActivity {

    private static inventoryManagement myInstance = null;
    D_ArrayImp<Inventory> inventarios;
    int amount_of_inventories;

    public inventoryManagement(){
        inventarios = new D_ArrayImp<>();
        amount_of_inventories = 0;
    }

    public static synchronized inventoryManagement getInstance() {
        if(myInstance==null) {
            myInstance = new inventoryManagement();
        }
        return myInstance;
    }

    public void addInventory(String name) {
        amount_of_inventories++;
        inventarios.add(new Inventory(amount_of_inventories, name));
    }
}

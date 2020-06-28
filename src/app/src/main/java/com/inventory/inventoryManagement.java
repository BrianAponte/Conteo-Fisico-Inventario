package com.inventory;

import android.content.Intent;

public class inventoryManagement {
    int inventoryNumber;
    private static inventoryManagement myInstance = null;
    D_ArrayImp<Inventory> inventarios;
    int amount_of_inventories;

    public inventoryManagement(){
        inventoryNumber = 1;
        inventarios = new D_ArrayImp<>();
        amount_of_inventories = 0;
    }

    public static synchronized inventoryManagement getInstance() {
        if(myInstance==null) {
            myInstance = new inventoryManagement();
        }
        return myInstance;
    }

    public void addInventory() {
        inventarios.add(new Inventory(inventoryNumber, new AVLTreeImp<Product>()));
        inventoryNumber++;
        amount_of_inventories++;
    }
}

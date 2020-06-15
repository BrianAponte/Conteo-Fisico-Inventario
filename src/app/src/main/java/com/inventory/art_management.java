package com.inventory;

public class art_management {
    private static art_management myInstance = null;

    AVLTreeImp<Product> articulos;
    int amount_of_art;

    public art_management(){
        articulos = new AVLTreeImp<>();
        amount_of_art = 0;
    }

    public static synchronized art_management getInstance() {
        if(myInstance==null) {
            myInstance = new art_management();
        }
        return myInstance;
    }

    public void add_art(Product product) {
        articulos.insert(product);
        amount_of_art++;
    }

    public D_ArrayImp<Product> getArt(){
        return articulos.getInOrder();
    }

    public Product findProd(Product prod) {
        return articulos.findData(prod);
    }
}

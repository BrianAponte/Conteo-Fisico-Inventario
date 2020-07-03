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

    public Product findProd(Product prod) {return articulos.findData(prod); }

    public D_ArrayImp<Product> Filter(String filter) {
        String end = getNext(filter);

        if(!end.equals("")) {
            return articulos.rangeSearchN(new Product("", filter, "", 0, 0),
                    new Product("", end, "", 0, 0));
        }
        else {
            return articulos.rangeSearchB(new Product("", filter, "", 1, 1));
        }
    }

    /**
     * Returns the next lexicographicly greater string that wouldn't fit
     * into a given filter string, it returns an empty string if the filter
     * is any string composed only by one or more "z"
     * @param s
     * @return
     */
    public static String getNext(String s) {
        s = s.toLowerCase();
        int currInd = s.length()-1;
        String alph = "abcdefghijklmnñopqrstuvwxyz";
        int alphIndexOfCurr = alph.indexOf(s.substring(currInd, currInd+1));

        if(alphIndexOfCurr!=25) {
            return s.substring(0, currInd)+alph.charAt(alphIndexOfCurr+1);
        }
        else {
            if(currInd==0) {
                return "";
            }
            else {
                return getNext(s.substring(0, currInd));
            }
        }
    }

    public void updateArt(Product oldArt, Product newArt) {
        articulos.update(oldArt, newArt);
    }

    public void deleteArt(Product prodToDelete) {
        articulos.delete(articulos.find(prodToDelete));
    }
}

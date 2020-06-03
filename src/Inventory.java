import java.util.ArrayList;
import java.util.Date;

public class Inventory {

    int id;
    Date date;
    ArrayList<Product> products;

    public Inventory(int id, Date date, ArrayList<Product> products) {
        this.id = id;
        this.date = date;
        this.products = products;
    }

}

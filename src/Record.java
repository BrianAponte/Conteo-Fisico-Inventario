import java.util.Date;

public class Record {

    int id;
    Date date;
    User user;
    Inventory inventory;
    Product product;
    int counted;

    public Record(int id, Date date, User user, Inventory inventory, Product product, int counted) {
        this.id = id;
        this.date = date;
        this.user = user;
        this.inventory = inventory;
        this.product = product;
        this.counted = counted;
    }

}

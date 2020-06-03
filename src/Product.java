public class Product {

    String sku;
    String name;
    String category;
    int stock;
    int counted;

    public Product(String sku, String name, String category, int stock, int counted) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.counted = counted;
    }

    public int compareTo(Product otherProduct){
        return this.name.compareToIgnoreCase(otherProduct.name);
    }

}

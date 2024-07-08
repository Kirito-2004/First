public class Product {
    private int idProd;
    private String name;
    private double price;

    Product() {
    }

    public Product(int idProd, String name, double price) {
        this.idProd = idProd;
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price) {
        this.idProd = Server.getLastProd();
        Server.setLastProd(Server.getLastProd() + 1);
        this.name = name;
        this.price = price;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getIdProd() {
        return idProd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return idProd + "," + name + "," + price;
    }

    public static Product fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid account data: " + str);
        }
        int idProd = Integer.parseInt(parts[0]);
        String name = parts[1];
        double price = Double.parseDouble(parts[2]);
        return new Product(idProd, name, price);
    }
}

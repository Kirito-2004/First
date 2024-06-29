public class Product {
    private int idProd;
    private String name;
    private double price;

    public Product(int idProd, String name, double price){
        this.idProd = idProd;
        this.name = name;
        this.price = price;
    }

    public void setIdProd(int idProd){
        this.idProd = idProd;
    }
    public int getIdProd(){
        return idProd;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
}

abstract class Customer {
    private String name;
    private double total;
    abstract String info();

    public Customer(String name, double total) {
        this.name = name;
        this.total = total;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getTotal(){
        return total;
    }
    public void setTotal(double total){
        this.total = total;
    }

}

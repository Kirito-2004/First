abstract class Customer {
    private String name;
    private double total;
    abstract String info();

    public Customer(String name, double total) {
        this.name = name;
        this.total = total;
    }

    public String setName(){
        return name;
    }
    public void getName(String name){
        this.name = name;
    }

    public double setTotal(){
        return total;
    }
    public void getTotal(double total){
        this.total = total;
    }

}

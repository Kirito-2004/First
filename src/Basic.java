public class Basic extends Customer {
    public Basic(String name, double total){
        super(name, total);
    };
    @Override
    public String info(){
        return "Name: " + getName() + " Total: " + getTotal();
    }

}

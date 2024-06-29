public class VIP extends Customer{
    private double rate;
    private int level;
    public VIP(String name, double total, double rate, int level){
        super(name, total);
        this.rate = rate;
        this.level = level;
    }

    public double getRate(){
        return rate;
    }
    public void setRate(double rate){
        this.rate = rate;
    }

    public int getLevel(){
        return level;
    }
    public void setLevel(int level){
        this.level = level;
    }

    @Override
    public String info(){
        return "Name: " + getName() + " Total: " + getTotal() + " Rate: " + getRate() + " Level: " + getLevel();
    }
}

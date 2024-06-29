abstract class VIP extends Customer{
    private double rate;
    private int level;
    public VIP(String name, double total, double rate, int level){
        super(name, total);
        this.rate = rate;
        this.level = level;
    }

    public double setRate(){
        return rate;
    }
    public void getRate(double rate){
        this.rate = rate;
    }

    public int setLevel(){
        return level;
    }
    public void getLevel(int level){
        this.level = level;
    }
}

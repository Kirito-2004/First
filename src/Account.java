public class Account {
    private int idAcc;
    private int tel;
    private String password;
    private Customer customer;
    public Account(){}
    public Account(String name, int tel, String password){
        Customer c = new Basic(name,0);
        this.idAcc=Sever.getLastAcc();
        Sever.setLastAcc(Sever.getLastAcc()+1);
        this.tel=tel;
        this.password=password;
        this.customer=c;
    }

    public String getPassword() {
        return password;
    }
}

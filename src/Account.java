public class Account {
    private int idAcc;
    private int tel;
    private String password;
    private Customer customer;

    public Account() {
    }

    public Account(String name, int tel, String password) {
        Customer c = new Basic(name, 0);
        this.idAcc = Server.getLastAcc();
        Server.setLastAcc(Server.getLastAcc() + 1);
        this.tel = tel;
        this.password = password;
        this.customer = c;
    }

    public Account(int idAcc, String name, int tel, String password) {
        Customer c = new Basic(name, 0);
        this.idAcc = idAcc;
        this.tel = tel;
        this.password = password;
        this.customer = c;
    }

    public int getIdAcc() {
        return idAcc;
    }

    public String getPassword() {
        return password;
    }

    public int getTel() {
        return tel;
    }

    public String getName() {
        return customer.getName();
    }

    @Override
    public String toString() {
        return idAcc + "," + customer.getName() + "," + tel + "," + password;
    }

    public static Account fromString(String str) {
        String[] parts = str.split(",");
        if (parts.length < 4) {
            throw new IllegalArgumentException("Invalid account data: " + str);
        }
        int idAcc = Integer.parseInt(parts[0]);
        String name = parts[1];
        int tel = Integer.parseInt(parts[2]);
        String password = parts[3];
        return new Account(idAcc, name, tel, password);
    }
}

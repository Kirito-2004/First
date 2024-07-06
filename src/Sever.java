import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sever {
    Scanner sc = new Scanner(System.in);
    Account account = new Account();
    Product product = new Product();
    private static int lastAcc;
    private static int lastProd;
    private HashMap<Integer, Account> hashAcc;
    private HashMap<Integer, Product> hashProd;

    public Sever() {
        this.hashAcc = new HashMap<>();
        this.hashProd = new HashMap<>();
        Sever.lastAcc = 0;
        Sever.lastProd = 0;
        loadAccountsFromFile();
        loadProductsFromFile();
    }

    public void createAccount() {
        boolean flag;
        System.out.println("\u001B[36m" + "#Create Account:" + "\u001B[0m");
        System.out.print("Name: ");
        String name = this.nextLine();
        int tel = 0;
        do {
            flag = true;
            // Kiem tra so dien thoai co phai la so khong
            try {
                System.out.print("Tel: ");
                tel = this.nextInt();
            } catch (Exception ex) {
                flag = false;
                System.out.println("\u001B[31m" + "<Invalid phone number, please try again!>" + "\u001B[0m");
            }

            // Kiem tra so dien thoai co ton tai that khong
            ListPerson lp = new ListPerson();
            lp.readPerson("../data/person.txt");
            if ((!lp.isExist(Integer.toString(tel))) && (flag == true)) {
                flag = false;
                System.out.println("\u001B[31m" + "<This phone number is not exist, please try again!>" + "\u001B[0m");
            }

            // Kiem tra so dien thoai da duoc dang ky chua
            if ((this.hashAcc.get(tel) != null) && (flag == true)) {
                flag = false;
                System.out.println(
                        "\u001B[31m" + "<This phone number has been registered, please try again!>" + "\u001B[0m");
            }
        } while (!flag);
        sc.nextLine();
        System.out.print("Password: ");
        String password = this.nextLine();
        Account a = new Account(name, tel, password);
        hashAcc.put(tel, a);
        saveAccountToFile(a);
        System.out.println("\u001B[32m" + "<Create account successfully" + "\u001B[0m");
        // this.pressEnter();
    }

    public boolean login(int tel, String password) {
        if (this.hashAcc.get(tel) != null) {
            if (this.hashAcc.get(tel).getPassword().equals(password))
                return true;
        }
        return false;
    }

    private void saveAccountToFile(Account account) {
        try (FileWriter writer = new FileWriter("data/Account.txt", true)) {
            writer.write(account.toString() + "\n");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error writing to file>" + "\u001B[0m");
        }
    }

    private void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/Account.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Account account = Account.fromString(line);
                    hashAcc.put(account.getTel(), account);
                    if (account.getIdAcc() > Sever.getLastAcc()) {
                        Sever.setLastAcc(account.getIdAcc() + 1);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid account data: " + line + ">" + "\u001B[0m");
                }
            }
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error reading from file>" + "\u001B[0m");
        }
    }

    private void loadProductsFromFile() {
        System.out.println("\u001B[36m" + "Load Product........" + "\u001B[0m");
        try (BufferedReader reader = new BufferedReader(new FileReader("data/Product.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Product product = Product.fromString(line);
                    hashProd.put(product.getIdProd(), product);
                    if (product.getIdProd() > Sever.getLastProd()) {
                        Sever.lastProd = product.getIdProd() + 1;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid product data: " + line + ">" + "\u001B[0m");
                }
            }
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error reading from file>" + "\u001B[0m");
        }
    }

    public void printAllProducts() {
        if (hashProd.isEmpty()) {
            System.out.println();
            System.out.println("\u001B[31m" + "<< No products loaded >>" + "\u001B[0m");
        } else {
            System.out.println();
            System.out.println("\u001B[36m" + "List Product:" + "\u001B[0m");
            for (Product product : hashProd.values()) {
                System.out.println(product);
            }
        }
        System.out.println();
        System.out.println("\u001B[32m" + "<< Load product successfuly >>" + "\u001B[0m");
    }

    public String nextLine() {
        return sc.nextLine();
    }

    public int nextInt() {
        return sc.nextInt();
    }

    public void pressEnter() {
        System.out.println("\u001B[33m" + "Press Enter key to continue..." + "\u001B[0m");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    public void menuA() {
        System.out.println("1.Create account");
        System.out.println("2.Login account");
        System.out.println("3.Exit");
    }

    public void menuB() {
        System.out.println("1.List product");
        System.out.println("2.My cart");
        System.out.println("3.Convenient card");
        System.out.println("4.History transaction");
        System.out.println("5.Logout account");
    }

    public void menuB1() {
        System.out.println("1.Choose product");
        System.out.println("2.Exit");
    }

    public void menuB2() {
        System.out.println("1.Change quantify");
        System.out.println("2.Order");
        System.out.println("3.Clear cart");
        System.out.println("4.Exit");
    }

    public void menuB3A() {
        System.out.println("1.Create convenient card");
        System.out.println("2.Exit");
    }

    public void menuB3B() {
        System.out.println("1.Check balance");
        System.out.println("2.");
    }

    public static void setLastAcc(int n) {
        Sever.lastAcc = n;
    }

    public static int getLastAcc() {
        return Sever.lastAcc;
    }

    public static void setLastProd(int n) {
        Sever.lastProd = n;
    }

    public static int getLastProd() {
        return Sever.lastProd;
    }
}

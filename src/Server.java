import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    Scanner sc = new Scanner(System.in);
    private static int lastAcc;
    private static int lastProd;
    private HashMap<Integer, Account> hashAcc;
    private HashMap<Integer, Product> hashProd;
    private ListPerson listPerson;

    public Server(int port)
    {
        this.hashAcc = new HashMap<>();
        this.hashProd = new HashMap<>();
        this.listPerson = new ListPerson();
        this.listPerson.readPerson("data/person.txt");
        Server.lastAcc = 0;
        Server.lastProd = 0;

        loadAccountsFromFile();
        loadProductsFromFile();

        try{
            server = new ServerSocket(port);
            System.out.println("\u001B[36m"+"#Server started"+"\u001B[0m");
            socket = server.accept();
            while(true){
                System.out.println("\u001B[32m"+"<Client connected>"+"\u001B[0m");
                input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                output = new DataOutputStream(socket.getOutputStream());
                int menu = input.readInt();
                if(menu==1){
                    int choice = 0;
                    while (choice==0) {
                        choice = input.readInt();
                        switch (choice) {
                            case 1:
                                boolean flag;
                                String name = input.readUTF();
                                int tel=0;
                                do{
                                    flag = true;
                                    tel = input.readInt();
                                    if(!this.listPerson.isExist(Integer.toString(tel))){
                                        flag = false;
                                        output.writeBoolean(false);
                                    }
                                    else output.writeBoolean(true);

                                    if(flag){
                                        if(this.hashAcc.get(tel) != null){
                                            flag = false;
                                            output.writeBoolean(false);
                                        }
                                        else output.writeBoolean(true);
                                    }
                                }while(!flag);
                                String password = input.readUTF();
                                Account a = new Account(name, tel, password);
                                hashAcc.put(tel, a);
                                saveAccountToFile(a);
                                break;
                            case 2:
                                break;
                            case 3:
                                printAllProducts();
                                break;
                            case 4:
                                System.exit(0);
                                break;
                        }
//                    try {
//                        choice = input.readInt();
//                        System.out.println(line);
//                    } catch (IOException i) {
//                        System.out.println("\u001B[31m"+"<Client disconnected>"+"\u001B[0m");
//                        socket.close();
//                        in.close();
//                        break;
//                    }
                    }
                }

                socket = server.accept();
            }
        }
        catch(IOException i)
        {
            System.out.println("\u001B[31m"+"<Cannot connect to server>"+"\u001B[0m");
            System.exit(1);
        }
    }

    public boolean login(int tel, String password) {
        if (this.hashAcc.get(tel) != null) {
            if(this.hashAcc.get(tel).getPassword().equals(password))
                return true;
        }
        return false;
    }

    private void saveAccountToFile(Account account) {
        try (FileWriter writer = new FileWriter("data/account.txt", true)) {
            writer.write(account.toString() + "\n");
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error writing to file>" + "\u001B[0m");
        }
    }

    private void loadAccountsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/account.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Account account = Account.fromString(line);
                    hashAcc.put(account.getTel(), account);
                    if (account.getIdAcc() > Server.getLastAcc()) {
                        Server.setLastAcc(account.getIdAcc() + 1);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid account data: " + line + ">" + "\u001B[0m");
                }
                System.out.println("\u001B[32m" + "<Load account successfully>" + "\u001B[0m");
            }
        } catch (IOException e) {
            System.out.println("\u001B[31m" + "<Error reading from file>" + "\u001B[0m");
        }
    }

    private void loadProductsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("data/product.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Product product = Product.fromString(line);
                    hashProd.put(product.getIdProd(), product);
                    if (product.getIdProd() > Server.getLastProd()) {
                        Server.lastProd = product.getIdProd() + 1;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("\u001B[31m" + "<Skipping invalid product data: " + line + ">" + "\u001B[0m");
                }
            }
            System.out.println("\u001B[32m" + "<Load product successfully>" + "\u001B[0m");
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

    public static void setLastAcc(int n) {
        Server.lastAcc = n;
    }

    public static int getLastAcc() {
        return Server.lastAcc;
    }

    public static void setLastProd(int n) {
        Server.lastProd = n;
    }

    public static int getLastProd() {
        return Server.lastProd;
    }

    public static void main(String[] args) {
        Server s = new Server(5000);
    }
}

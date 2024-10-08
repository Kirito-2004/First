import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private Socket socket = null;
    private DataInputStream input = null;
    private DataOutputStream output = null;

    Scanner sc = new Scanner(System.in);
    private Customer customer;
    // constructor to put ip address and port
    public Client(String address, int port)
    {
        // establish a connection
        try {
            socket = new Socket(address, port);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            int choice = 0;
            if(this.customer == null){
                output.writeInt(1); //Request menu A to server
            }
            else {
                output.writeInt(2); //Request menu B to server
            }
            do{
                if(this.customer == null){
                    menuA();
                    System.out.print("\u001B[36m"+"#Select: "+"\u001B[0m");
                    choice = getChoice();
                    output.writeInt(choice); //Send choice to server
                    switch(choice){
                        case 1:
                            createAccount();
                            break;
                        case 2:

                            break;
                        case 3:
                            System.exit(0);
                            break;
                    }
                }
                else{
                    menuB();
                    choice = getChoice();
                    output.writeInt(choice);
                }

            } while(choice==0);




        }
        catch (IOException i) {
            System.out.println(i);
            return;
        }

//        try {
//            input.close();
//            out.close();
//            socket.close();
//        }
//        catch (IOException i) {
//            System.out.println(i);
//        }
    }

    public int getChoice() {
        int choice = 0;
        try {
            sc = new Scanner(System.in);
            choice = sc.nextInt();
        } catch (Exception e){}
        return choice;
    }

    public void menuA() {
        System.out.println("\u001B[36m"+"#Menu A:"+"\u001B[0m");
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




    public void createAccount() {
        try {
            boolean flag;
            System.out.println("\u001B[36m" + "#Create Account:" + "\u001B[0m");
            System.out.print("Name: ");
            String name = this.nextLine();
            output.writeUTF(name); //Send name to server
            int tel = 0;
            do {
                flag = true;
                try {
                    System.out.print("Tel: ");
                    tel = this.nextInt();
                } catch (Exception ex) {
                    flag = false;
                    System.out.println("\u001B[31m" + "<Invalid phone number, please try again!>" + "\u001B[0m");
                }
                if(flag) {
                    output.writeInt(tel); //Send tel to server
                    flag = input.readBoolean(); //Receive flag from server 1

                    if (flag) {
                        flag = input.readBoolean(); //Receive flag from server 2
                        if (!flag) {
                            System.out.println("\u001B[31m" + "<This phone number is registed, please try again!>" + "\u001B[0m");
                        }
                    } else {
                        System.out.println("\u001B[31m" + "<This phone number is not exist, please try again!>" + "\u001B[0m");
                    }
                }
            } while (!flag);
            sc.nextLine();
            System.out.print("Password: ");
            String password = this.nextLine();
            output.writeUTF(password);
            System.out.println("\u001B[32m" + "<Create account successfully" + "\u001B[0m");
        } catch (IOException e) {}
    }

    public String nextLine() {
        sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public int nextInt() {
        sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 5000);
    }
}

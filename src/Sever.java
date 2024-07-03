import java.util.HashMap;
import java.util.Scanner;
public class Sever {
    private static int lastAcc;
    private HashMap<Integer,Account> hashAcc;
    public Sever(){
        this.hashAcc = new HashMap<>();
        Sever.lastAcc=0;
    }
    public void createAccount(){
        boolean flag;
        System.out.println("\u001B[36m"+"#Create Account:"+"\u001B[0m");
        System.out.print("Name: ");
        String name = this.nextLine();
        int tel=0;
        do {
            flag = true;
            //Kiem tra so dien thoai co phai la so khong
            try {
                System.out.print("Tel: ");
                tel = this.nextInt();
            } catch (Exception ex) {
                flag = false;
                System.out.println("\u001B[31m" + "<Invalid phone number, please try again!>" + "\u001B[0m");
            }

            //Kiem tra so dien thoai co ton tai that khong
            ListPerson lp = new ListPerson();
            lp.readPerson("data/person.txt");
            if ((!lp.isExist(Integer.toString(tel)))&&(flag==true)){
                flag=false;
                System.out.println("\u001B[31m" + "<This phone number is not exist, please try again!>" + "\u001B[0m");
            }

            //Kiem tra so dien thoai da duoc dang ky chua
            if((this.hashAcc.get(tel)!=null)&&(flag==true)){
                flag=false;
                System.out.println("\u001B[31m" + "<This phone number has been registered, please try again!>"+"\u001B[0m");
            }
        }while(!flag);

        System.out.print("Password: ");
        String password = this.nextLine();
        Account a = new Account(name,tel,password);
        hashAcc.put(tel,a);
        System.out.println("\u001B[32m" + "<Create account successfully"+"\u001B[0m");
        this.pressEnter();
    }

    public boolean login(int tel,String password){
        if(this.hashAcc.get(tel)!=null){
            if(this.hashAcc.get(tel).getPassword().equals(password)) return true;
        }
        return false;
    }

    public String nextLine(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public int nextInt(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    public void pressEnter(){
        System.out.println("\u001B[33m"+"Press Enter key to continue..."+"\u001B[0m");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }
    public void menuA(){
        System.out.println("1.Create account");
        System.out.println("2.Login account");
        System.out.println("3.Exit");
    }
    public void menuB(){
        System.out.println("1.List product");
        System.out.println("2.My cart");
        System.out.println("3.Convenient card");
        System.out.println("4.History transaction");
        System.out.println("5.Logout account");
    }
    public void menuB1(){
        System.out.println("1.Choose product");
        System.out.println("2.Exit");
    }
    public void menuB2(){
        System.out.println("1.Change quantify");
        System.out.println("2.Order");
        System.out.println("3.Clear cart");
        System.out.println("4.Exit");
    }
    public void menuB3A(){
        System.out.println("1.Create convenient card");
        System.out.println("2.Exit");
    }
    public void menuB3B(){
        System.out.println("1.Check balance");
        System.out.println("2.");
    }

    public static void setLastAcc(int n){
        Sever.lastAcc=n;
    }
    public static int getLastAcc() {
        return Sever.lastAcc;
    }
}

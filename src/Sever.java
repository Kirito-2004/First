import java.util.HashMap;
import java.util.Scanner;
public class Sever {
    private static int lastAcc;
    private HashMap<Integer,Account> hashAcc;

    public void createAccount(){
        boolean flag = false;
        System.out.println("------------------------------");
        System.out.println("#Create Account:");
        System.out.print("Name: ");
        String name = this.nextLine();
        while (!flag){
            try {
                System.out.print("Tel: ");
                int tel = this.nextInt();
                flag=true;
            }
            catch (Exception ex){
                System.out.println("\u001B[31m" + "<Tel invalid, please try again!>"+"\u001B[0m");
            }
        }
        System.out.print("Password: ");
        String password = this.nextLine();
        System.out.println("\u001B[32m" + "<Create account successful>"+"\u001B[0m");
        System.out.println("------------------------------");
    }

    public String nextLine(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public int nextInt(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    public static void setLastAcc(int n){
        Sever.lastAcc=n;
    }
    public static int getLastAcc() {
        return Sever.lastAcc;
    }
}

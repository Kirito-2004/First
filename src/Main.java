import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Sever s = new Sever();
        //Tao acc 1
        while(true) {
            s.createAccount();
        }
        //Tao acc 2
//        s.createAccount();
//        Scanner sc = new Scanner(System.in);
//        System.out.print("Tel: ");
//        int tel=sc.nextInt();
//        sc=new Scanner(System.in);
//        System.out.print("Password: ");
//        String password = sc.nextLine();
//        if(s.login(tel,password)) System.out.println("Login successfully");
//        else System.out.println("Login failure");
    }
}

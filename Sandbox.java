import java.util.Scanner;

public class Sandbox {
    public static void main(String[] args){
        String n;
        String p;
        User me = new User();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter user name: ");
        n = input.nextLine();
        System.out.println("Enter password: ");
        p = input.nextLine();
        me.set_name(n);
        me.set_pass(p);

        Budget myBudget = new Budget();
        for(int i = 10; i >= 0; i--){
            myBudget.options();
        }

        System.out.println("You are logging out. Goodbye!");
        //it's a lie I haven't figured out login/logout for now. let's get everything else working first.

    }

}

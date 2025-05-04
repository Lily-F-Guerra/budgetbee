import Items.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Budget {
    private ArrayList<Item> items = new ArrayList<>();
    private Scanner input = new Scanner(System.in); 

    public void createItem(){
        System.out.println("Select item type:"); /*placeholder for a more complex type selection*/
        String type = input.nextLine();
        String name;
        float val;
        int freq;

        if (type.equals("One Time Expense")){ //this is an example of polymorphism
            System.out.print("Enter value and item name: ");
            val = input.nextFloat();
            name = input.nextLine();
            Item n = new OneTimeExpense(name, val);
            items.add(n);
        }
        else if (type.equals("Recurring Expense")){
            System.out.print("Enter value, frequency (in days), and item name: ");
            val = input.nextFloat();
            freq = input.nextInt();
            name = input.nextLine();
            Item n = new RecurringExpense(name, val, freq);
            items.add(n);
        }
        else if (type.equals("One Time Income")){
            System.out.print("Enter value and item name: ");
            val = input.nextFloat();
            name = input.nextLine();
            Item n = new OneTimeIncome(name, val);
            items.add(n);
        }
        else if (type.equals("Recurring Income")){
            System.out.print("Enter value, frequency (in days), and item name: ");
            val = input.nextFloat();
            freq = input.nextInt();
            name = input.nextLine();
            Item n = new RecurringIncome(name, val, freq);
            items.add(n);
        }

    }

    public double total() {
        double total = 0.0;
        for (Item item : items) {
            if (item instanceof OneTimeIncome || item instanceof RecurringIncome) {
                total += item.getValue();
            } else {
                total -= item.getValue(); // treat expenses as subtraction
            }
        }
        return total;
    }

    public void display(){
        //we may need to change this once Charlie gets the frontend working
        for (Item item : items) {
            if (item instanceof OneTimeIncome) {
                System.out.println("Income: " + item.name + " | " + item.value + " |       |");
            } else if (item instanceof OneTimeExpense) {
                    System.out.println("Expense: " + item.name + " | " + item.value + " |       |");
            } else if (item instanceof RecurringIncome){
                System.out.println("Income: " + item.name + " | " + item.value + " | " + item.freq + " |");
            } else {
                System.out.println("Expense: " + item.name + " | " + item.value + " | " + item.freq + " |");
            }
        }

        System.out.println("Total funds: " + total());
        
    }

    public void options(){
        System.out.println("Create New Item | Display Budget | Log Out ");
        String choice = input.nextLine();
        if (choice.equals("Create New Item")){
            createItem();
        }
        else if(choice.equals("Display Budget")){
            display();
        }
        else { return;}

    }
    
}

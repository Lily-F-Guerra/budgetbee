import Items.*;
import java.util.ArrayList;

public class Budget {
    private ArrayList<Item> items = new ArrayList<>();

    public void createItem(){
        //get name, value, freq, and type
        //use polymorphism
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
        
    }
    
}

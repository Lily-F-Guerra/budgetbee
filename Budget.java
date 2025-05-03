import Items.*;
import java.util.ArrayList;

public class Budget {
    private ArrayList<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
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
}

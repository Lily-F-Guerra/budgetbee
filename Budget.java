abstract class Item{
    int value;
    String name;

    //add getter and setter methods

}

class OneTimeExpense extends Item{

}

class RecurringExpense extends Item{

}

class OneTimeIncome extends Item{

}

class RecurringIncome extends Item{
    private int freq;

    public void set_freq(/*how do we store the frequency? days? weeks? a string?*/ int f){
        if (f > 0){
            freq = f;
        }
    }

}


public class Budget{
  
    //how do we store items in the Budget? Array? Idk how to do this in Java.

    public double Total(){
        //loop through each item in the Budget
        //if it is an expense, subtract
        //if it is an income, add
        //number may be positive or negative
        return 0.0;
    }

    
}

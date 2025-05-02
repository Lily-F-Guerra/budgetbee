
class RecurringIncome extends Item{
    private int freq;

    public void set_freq(int f /*how do we store the frequency? days? weeks? a string?*/){
        if (f > 0){
            freq = f;
        }
    }

}
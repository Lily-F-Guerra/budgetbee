abstract class Item{
    int value;
    String name;
    int freq;

    //constructor
    //use polymorphism
    public Item(String n, int v, int f){
        name = n;
        value = v;
        freq = f;
    }

    public Item(String n, int v){
        name = n;
        value = v;
    }

}
abstract class Item{ //abstraction: other classes inherit this class and its methods
    float value;
    String name;
    int freq;

    //constructor
    //use polymorphism
    public Item(String n, float v, int f){
        name = n;
        value = v;
        freq = f;
    }

    public Item(String n, float v){
        name = n;
        value = v;
    }

    public float getValue(){
        return value;
    }

}
public class User{
    private String name;
    private String password;
    private bool login = true;

    public void set_name(String n){
        if (login == true && n != ""){
            name = n;
        }
    }

    public String get_name(){
        return name;
    }

    public void set_pass(String pass){
        if (login == true && pass != ""){
            password = pass;
        }
    }
    //No getter method for password. It needs to be a secret!

    public void login(String n, String pass){
        if (n == name && pass == password){
            login = true;
        }
        else {login = false;}
    }


}
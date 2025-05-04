public class User {
    private String name;
    private String password;
    private boolean login = true;

    public void set_name(String n){
        if (login == true && !n.isEmpty()){
            name = n;
        }
    }

    public String get_name(){
        return name;
    }

    public void set_pass(String pass){
        if (login == true && !pass.isEmpty()){
            password = pass;
        }
    }

    public void login(String n, String pass){
        if (n.equals(name) && pass.equals(password)){
            login = true;
        } else {
            login = false;
        }
    }
}

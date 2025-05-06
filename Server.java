import static spark.Spark.*;
import java.nio.file.Paths;

public class Server {
    private final UserDatabase userDB;
    private static final String PUBLIC_DIR = "public";
    
    public Server() {
        this.userDB = new UserDatabase();
        initializeRoutes();
    }
    
    private void initializeRoutes() {
        configureStaticFiles();
        setupHomeRoute();
        setupLoginRoute();
        setupRegisterRoute();
    }
    
    private void configureStaticFiles() {
        staticFiles.externalLocation(Paths.get(PUBLIC_DIR).toAbsolutePath().toString());
    }
    
    private void setupHomeRoute() {
        get("/", (req, res) -> {
            res.redirect("/login.html");
            return null;
        });
    }
    
    private void setupLoginRoute() {
        post("/login", (req, res) -> {
            LoginRequest loginRequest = new LoginRequest(
                req.queryParams("email"),
                req.queryParams("password")
            );
            
            return handleLogin(loginRequest, req, res);
        });
    }
    
    private String handleLogin(LoginRequest loginRequest, spark.Request req, spark.Response res) {
        Integer userId = userDB.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
        
        if (userId != null) {
            req.session(true).attribute("userId", userId);
            res.redirect("/BudgetTracker.html");
        } else {
            res.redirect("/login.html?error=true");
        }
        return null;
    }
    
    private void setupRegisterRoute() {
        post("/register", (req, res) -> {
            RegistrationRequest registerRequest = new RegistrationRequest(
                req.queryParams("name"),
                req.queryParams("email"),
                req.queryParams("password")
            );
            
            return handleRegistration(registerRequest, res);
        });
    }
    
    private String handleRegistration(RegistrationRequest registerRequest, spark.Response res) {
        if (userDB.registerUser(
            registerRequest.getName(),
            registerRequest.getEmail(),
            registerRequest.getPassword()
        )) {
            res.redirect("/login.html");
        } else {
            res.redirect("/register.html?error=true");
        }
        return null;
    }
    
    public static void main(String[] args) {
        new Server(); // Initialize the server
    }
    
    // Encapsulated request objects
    private static class LoginRequest {
        private final String email;
        private final String password;
        
        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }
        
        public String getEmail() { 
            return email; 
        }
        public String getPassword() { 
            return password; 
        }
    }
    
    // Encapsulation
    private static class RegistrationRequest {
        private final String name;
        private final String email;
        private final String password;
        
        public RegistrationRequest(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
        
        public String getName() { 
            return name; 
        }
        public String getEmail() { 
            return email; 
        }
        public String getPassword() {
             return password; 
        }
    }
}

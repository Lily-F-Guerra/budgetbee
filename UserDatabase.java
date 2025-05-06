import java.sql.*;

// Abstraction - DatabaseManager is an abstract class with common database operations
// you can't build an actual "DatabaseManager" library, but you can use it as a base for specific libraries
// Parent class
abstract class DatabaseManager {
    // Library address (DB_URL) - where database is located
    // Protected - I can access from the same directory. If I want to access from another
    // directory then I have to use Inheritance.
    protected static final String DB_URL = "jdbc:sqlite:database.db";
    
    // A way to get inside the library (getConnection() - like having library keys)
    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}

// Inheritance - UserDatabase extends DatabaseManager
// Parent class - DatabaseManager: A basic blueprint for any library 
// Child class (UserDatabase) - Like a special user section
// It gets all the basic library stuff from DatabaseManager (like getConnection() and createStatement())
public class UserDatabase extends DatabaseManager {
    // Static - Already present for DB_URL, adding a static method
    private static int userCount = 0;
    
    //It automatically calls createUsersTable() when a new UserDatabase is created
    public UserDatabase() {
        // This is like preparing a special notebook to track users:
        createUsersTable();
    }

    private void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "name TEXT NOT NULL," +
                     "email TEXT UNIQUE NOT NULL," +
                     "password TEXT NOT NULL)";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
        }
    }

    // Static method to get user count
    public static int getUserCount() {
        return userCount;
    }

    public boolean registerUser(String name, String email, String password) {
        String sql = "INSERT INTO users(name, email, password) VALUES(?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();
            userCount++; // Incrementing static counter
            return true;
        } catch (SQLException e) {
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    public Integer loginUser(String email, String password) {
        String sql = "SELECT id FROM users WHERE email = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Login error: " + e.getMessage());
            return null;
        }
    }
}

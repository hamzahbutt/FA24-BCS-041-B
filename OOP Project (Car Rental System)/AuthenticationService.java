import java.util.ArrayList;
import java.util.List;

public class AuthenticationService {
    private List<User> users;
    private User currentUser;

    public AuthenticationService() {
        users = new ArrayList<>();
    }

    public boolean signUp(String username, String password, String email) {
        // Check if username already exists
        if (findUserByUsername(username) != null) {
            return false;
        }

        String userId = generateUserId();
        User newUser = new Customer(userId, username, password, email);
        users.add(newUser);
        return true;
    }

    public boolean login(String username, String password) {
        User user = findUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    private User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    private String generateUserId() {
        return "CUST" + String.format("%03d", users.size() + 1);
    }
}
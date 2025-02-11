import java.util.HashMap;
import java.util.Map;

class RegistrationService {
    private Map<String, String> userDatabase = new HashMap<>();
    private Map<String, String> emailDatabase = new HashMap<>();
    private Map<String, String> phoneDatabase = new HashMap<>();
    private Map<String, Integer> tokenDatabase = new HashMap<>();
    private Map<String, String> roleDatabase = new HashMap<>();

    public boolean register(String username, String password, String email, String phone, String role) {
        if (!userDatabase.containsKey(username)) {
            userDatabase.put(username, password);
            emailDatabase.put(username, email);
            phoneDatabase.put(username, phone);
            roleDatabase.put(username, role);
            generateToken(username);
            System.out.println("Użytkownik zarejestrowany pomyślnie jako " + role);
            return true;
        }
        System.out.println("Nazwa użytkownika już istnieje");
        return false;
    }

    public boolean validateUser(String username, String password) {
        return userDatabase.containsKey(username) && userDatabase.get(username).equals(password);
    }

    public boolean validateUserByEmail(String email, String password) {
        return emailDatabase.containsValue(email) && userDatabase.containsKey(getUsernameByEmail(email)) && userDatabase.get(getUsernameByEmail(email)).equals(password);
    }

    public boolean validateUserByPhone(String phone, String password) {
        return phoneDatabase.containsValue(phone) && userDatabase.containsKey(getUsernameByPhone(phone)) && userDatabase.get(getUsernameByPhone(phone)).equals(password);
    }

    public void generateToken(String username) {
        int token = (int) (Math.random() * 10000);
        tokenDatabase.put(username, token);
        System.out.println("Wygenerowano token dla " + username + ": " + token);
    }

    public boolean validateToken(String username, int token) {
        return tokenDatabase.containsKey(username) && tokenDatabase.get(username) == token;
    }

    public boolean validateTokenByPhone(String phone, int token) {
        String username = getUsernameByPhone(phone);
        return username != null && tokenDatabase.containsKey(username) && tokenDatabase.get(username) == token;
    }

    public String getUserRole(String username) {
        return roleDatabase.getOrDefault(username, "Unknown");
    }

    public String getUsernameByPhone(String phone) {
        for (Map.Entry<String, String> entry : phoneDatabase.entrySet()) {
            if (entry.getValue().equals(phone)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public String getUsernameByEmail(String email) {
        for (Map.Entry<String, String> entry : emailDatabase.entrySet()) {
            if (entry.getValue().equals(email)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
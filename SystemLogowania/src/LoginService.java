class LoginService {
    private RegistrationService registrationService;

    public LoginService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    public boolean login(String username, String password) {
        if (registrationService.validateUser(username, password)) {
            System.out.println("Logowanie za pomocą nazwy użytkownika i hasła ✅");
            System.out.println("Witaj, " + username + "!");
            displayAccessLevel(username);
            return true;
        }
        System.out.println("Odmowa dostępu ❌");
        return false;
    }

    public boolean login(String username, String email, String password) {
        if (registrationService.validateUserByEmail(email, password)) {
            System.out.println("Logowanie za pomocą loginu, e-maila i hasła ✅");
            displayAccessLevel(username);
            return true;
        }
        System.out.println("Odmowa dostępu ❌");
        return false;
    }

    public boolean login(int token, String username, String email) {
        if (registrationService.validateToken(username, token) && registrationService.getUsernameByEmail(email).equals(username)) {
            System.out.println("Logowanie za pomocą tokenu, loginu i e-maila ✅");
            displayAccessLevel(username);
            return true;
        }
        System.out.println("Nieprawidłowy token, login lub e-mail ❌");
        return false;
    }

    public boolean login(String phone, int token) {
        if (registrationService.validateTokenByPhone(phone, token)) {
            System.out.println("Logowanie za pomocą numeru telefonu i tokenu ✅");
            String username = registrationService.getUsernameByPhone(phone);
            displayAccessLevel(username);
            return true;
        }
        System.out.println("Nieprawidłowy token lub numer telefonu ❌");
        return false;
    }

    private void displayAccessLevel(String username) {
        String role = registrationService.getUserRole(username);
        User user;
        switch (role) {
            case "Admin":
                user = new Admin();
                break;
            case "Programmer":
                user = new Programmer();
                break;
            case "Tester":
                user = new Tester();
                break;
            case "Manager":
                user = new Manager();
                break;
            default:
                System.out.println("Nieznana rola użytkownika");
                return;
        }
        System.out.println("Twoje uprawnienia: " + user.getAccessLevel());
    }
}
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RegistrationService registrationService = new RegistrationService();
        LoginService loginService = new LoginService(registrationService);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Wybierz opcję:");
            System.out.println("1. Rejestracja");
            System.out.println("2. Logowanie");
            System.out.println("3. Wyjście");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Podaj nazwę użytkownika: ");
                    String username = scanner.nextLine();
                    System.out.print("Podaj hasło: ");
                    String password = scanner.nextLine();
                    System.out.print("Podaj e-mail: ");
                    String email = scanner.nextLine();
                    System.out.print("Podaj numer telefonu: ");
                    String phone = scanner.nextLine();
                    System.out.println("Wybierz rolę:");
                    System.out.println("1. Admin");
                    System.out.println("2. Programmer");
                    System.out.println("3. Tester");
                    System.out.println("4. Manager");
                    int roleChoice = scanner.nextInt();
                    scanner.nextLine();
                    String role = "";
                    switch (roleChoice) {
                        case 1:
                            role = "Admin";
                            break;
                        case 2:
                            role = "Programmer";
                            break;
                        case 3:
                            role = "Tester";
                            break;
                        case 4:
                            role = "Manager";
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór roli. Ustawiono domyślną rolę: Tester");
                            role = "Tester";
                    }
                    registrationService.register(username, password, email, phone, role);
                    break;
                case 2:
                    System.out.println("1. Logowanie za pomocą loginu i hasła");
                    System.out.println("2. Logowanie za pomocą loginu, e-maila i hasła");
                    System.out.println("3. Logowanie za pomocą loginu, tokenu i e-maila");
                    System.out.println("4. Logowanie za pomocą numeru telefonu i tokenu");
                    int loginChoice = scanner.nextInt();
                    scanner.nextLine();
                    String loginUser;
                    switch (loginChoice) {
                        case 1:
                            System.out.print("Podaj nazwę użytkownika: ");
                            loginUser = scanner.nextLine();
                            System.out.print("Podaj hasło: ");
                            String loginPassword = scanner.nextLine();
                            loginService.login(loginUser, loginPassword);
                            break;
                        case 2:
                            System.out.print("Podaj nazwę użytkownika: ");
                            loginUser = scanner.nextLine();
                            System.out.print("Podaj e-mail: ");
                            String loginEmail = scanner.nextLine();
                            System.out.print("Podaj hasło: ");
                            String emailPassword = scanner.nextLine();
                            loginService.login(loginUser, loginEmail, emailPassword);
                            break;
                        case 3:
                            System.out.print("Podaj nazwę użytkownika: ");
                            String tokenUser = scanner.nextLine();
                            System.out.print("Podaj token: ");
                            int token = scanner.nextInt();
                            scanner.nextLine();
                            System.out.print("Podaj e-mail: ");
                            String tokenEmail = scanner.nextLine();
                            loginService.login(token, tokenUser, tokenEmail);
                            break;

                        case 4:
                            System.out.print("Podaj numer telefonu: ");
                            String phoneNumber = scanner.nextLine();
                            System.out.print("Podaj token: ");
                            int phoneToken = scanner.nextInt();
                            loginService.login(phoneNumber, phoneToken);
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór logowania.");
                    }
                    break;
                case 3:
                    System.out.println("Zamykanie programu...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
            }
        }
    }
}

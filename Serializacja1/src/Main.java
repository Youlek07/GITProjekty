import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        LibraryManager manager = new LibraryManager();
        Scanner scanner = new Scanner(System.in);

        manager.addBook(new Book("Pan Tadeusz", "Adam Mickiewicz", 1834));
        manager.addBook(new Book("Lalka", "Bolesław Prus", 1890));

        boolean running = true;
        while (running){
            System.out.println("\n MENU:");
            System.out.println("1. Dodaj książkę");
            System.out.println("2. Wyświetl książki");
            System.out.println("3. Zapisz do pliku (binary)");
            System.out.println("4. Wczytaj z pliku (binary)");
            System.out.println("5. Zapisz do JSON");
            System.out.println("6. Wczytaj z JSON");
            System.out.println("7. Wyjście");
            System.out.print(" Wybierz opcję: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option){
                case 1 -> manager.addBookFromUserInput();
                case 2 -> manager.displayBooks();
                case 3 -> manager.saveToFile("books.dat");
                case 4 -> manager.loadFromFile("books.dat");
                case 5 -> manager.saveToJson("books.json");
                case 6 -> manager.loadFromJson("books.json");
                case 7 ->{
                    running = false;
                    System.out.println("Dziękujemy za skorzystanie z programu!");
                }
                default -> System.out.println("Nieprawidłowa opcja.");
            }
        }
        scanner.close();
    }
}

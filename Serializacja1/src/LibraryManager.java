import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import java.util.Scanner;

public class LibraryManager{
    private List<Book> books = new ArrayList<>();

    public void addBookFromUserInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj tytuł książki: ");
        String title = scanner.nextLine();
        System.out.print("Podaj autora książki: ");
        String author = scanner.nextLine();
        System.out.print("Podaj rok wydania: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        Book book = new Book(title, author, year);
        addBook(book);

        System.out.println("Książka dodana pomyślnie!");
    }

    public void addBook(Book book){
        books.add(book);
    }

    public void saveToFile(String filename){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(books);
            System.out.println("Lista książek została zapisana do pliku.");
        } catch (IOException e){
            System.err.println("Błąd podczas zapisywania pliku: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename){
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            books = (List<Book>) in.readObject();
            System.out.println("Lista książek wczytana z pliku binarnego:");
            if (books.isEmpty()) {
                System.out.println("Brak książek w pliku.");
            } else {
                for (Book book : books){
                    System.out.println(book);
                }
            }
        } catch (IOException | ClassNotFoundException e){
            System.err.println("Błąd podczas wczytywania pliku: " + e.getMessage());
        }
    }


    public void displayBooks(){
        if (books.isEmpty()){
            System.out.println("Brak książek w bibliotece.");
        } else{
            System.out.println("Lista książek:");
            for(Book book : books){
                System.out.println(book.toString());
            }
        }
    }

    public void saveToJson(String filename){
        try (Writer writer = new FileWriter(filename)){
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(books, writer);
            System.out.println("Zapisano książki do formatu JSON.");
        } catch (IOException e){
            System.err.println("Błąd zapisu JSON: " + e.getMessage());
        }
    }

    public void loadFromJson(String filename){
        try (Reader reader = new FileReader(filename)){
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Book>>(){}.getType();
            books = gson.fromJson(reader, listType);
            System.out.println("Wczytano książki z pliku JSON:");
            if (books == null || books.isEmpty()){
                System.out.println("Brak książek w pliku.");
            } else{
                for (Book book : books){
                    System.out.println(book);
                }
            }
        } catch (IOException e){
            System.err.println("Błąd odczytu z formatu JSON: " + e.getMessage());
        }
    }

}

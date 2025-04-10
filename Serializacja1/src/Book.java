import java.io.Serializable;
import java.util.UUID;

public class Book implements Serializable{
    private static final long serialVersionUID = 1L;

    private String title;
    private String author;
    private int year;
    private String id;

    public Book(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", id='" + id + '\'' +
                '}';
    }
}

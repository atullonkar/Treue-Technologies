package ad.demo.onlinebookstore.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
@Getter
@Setter
public class Book {

    @Id
    private String bookId;
    private String bookName;
    private String author;
    private String genre;
    private int volume;
    private String bookType;
    private String series;
    private int pages;
    private boolean inStock;
    private double price;

    public Book() {}

    public Book(String bookId, String bookName, String author, String genre, int volume, String bookType, String series, int pages, boolean inStock, double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.volume = volume;
        this.bookType = bookType;
        this.series = series;
        this.pages = pages;
        this.inStock = inStock;
        this.price = price;
    }

    public Book(String bookName, String author, String genre, int volume, String bookType, String series, int pages, boolean inStock, double price) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
        this.volume = volume;
        this.bookType = bookType;
        this.series = series;
        this.pages = pages;
        this.inStock = inStock;
        this.price = price;
    }
}

package ad.demo.onlinebookstore.repository;

import ad.demo.onlinebookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public interface BookRepository {

    Book addBook(Book book);

    void deleteBook(String bookId) throws Exception;

    Book updateBookField(String bookId, String fieldName, String newValue);

    Book updateBook(Book book);

    Page<Book> findAllBook(String bookId, String bookName, String author, String genre, Integer lessThanVolume,
                           Integer moreThanVolume, String bookType, String series, Integer moreThanPages,
                           Integer lessThanPages, Boolean inStock, Double lessThanPrice, Double moreThanPrice,
                           PageRequest pageable);
}

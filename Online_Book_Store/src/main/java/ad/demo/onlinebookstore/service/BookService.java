package ad.demo.onlinebookstore.service;

import ad.demo.onlinebookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;

public interface BookService {

    Book addBook(Book book);

    void deleteBook(String bookId) throws Exception;

    Book updateBookField(String bookId, String fieldName, String newValue);

    Book updateBook(Book book);

    Page<Book> findAllBook(String bookId, String bookName, String author, String genre, Integer lessThanVolume,
                           Integer moreThanVolume, String bookType, String series, Integer moreThanPages,
                           Integer lessThanPages, Boolean inStock, Double lessThanPrice, Double moreThanPrice,
                           int page, int size, String sortBy, Sort.Direction directionString);
}

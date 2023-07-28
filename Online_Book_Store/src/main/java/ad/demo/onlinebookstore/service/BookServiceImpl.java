package ad.demo.onlinebookstore.service;

import ad.demo.onlinebookstore.model.Book;
import ad.demo.onlinebookstore.repository.BookRepository;
import ad.demo.onlinebookstore.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(Book book) {
        return bookRepository.addBook(book);
    }

    @Override
    public void deleteBook(String bookId) throws Exception {
        bookRepository.deleteBook(bookId);
    }

    @Override
    public Book updateBookField(String bookId, String fieldName, String newValue) {
        if (fieldName.equalsIgnoreCase(Constants.BOOKID))
            throw new IllegalArgumentException("This field can't be modified!");
        else
            return bookRepository.updateBookField(bookId,fieldName,newValue);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.updateBook(book);
    }

    @Override
    public Page<Book> findAllBook(String bookId, String bookName, String author, String genre, Integer lessThanVolume,
                                  Integer moreThanVolume, String bookType, String series, Integer moreThanPages,
                                  Integer lessThanPages, Boolean inStock, Double lessThanPrice, Double moreThanPrice,
                                  int page, int size, String sortBy, Sort.Direction direction) {
        return bookRepository.findAllBook(bookId,bookName,author,genre,lessThanVolume,moreThanVolume,bookType,
                series,moreThanPages,lessThanPages,inStock,lessThanPrice,moreThanPrice, PageRequest.of(page,size,
                        Sort.by(direction,sortBy)));
    }
}

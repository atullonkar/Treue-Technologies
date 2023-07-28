package ad.demo.onlinebookstore.service;

import ad.demo.onlinebookstore.model.Book;
import ad.demo.onlinebookstore.repository.BookRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepositoryImpl bookRepository;

    private Book book;

    @BeforeEach
    void setup() {
        book = new Book("64bc159605856a0c8f3404fa","Harry Potter and the Order of the Phoenix","J. K. Rowling",
                "Fantasy",7,"Paperback","Harry Potter",816,true,8.99);
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookRepository).deleteBook(anyString());

        bookService.deleteBook(book.getBookId());

        verify(bookRepository).deleteBook(anyString());
    }

    @Test
    public void testDeleteBookException() throws Exception {
        doThrow(new Exception("Book with book id: " + book.getBookId() + " does not exist!")).when(bookRepository)
                .deleteBook(anyString());

        Assertions.assertThrows(Exception.class, () -> bookService.deleteBook(book.getBookId()));
    }
}

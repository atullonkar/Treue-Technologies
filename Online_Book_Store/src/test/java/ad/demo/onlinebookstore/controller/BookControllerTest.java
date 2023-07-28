package ad.demo.onlinebookstore.controller;

import ad.demo.onlinebookstore.model.Book;
import ad.demo.onlinebookstore.service.BookService;
import ad.demo.onlinebookstore.utils.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private Book book;
    private List<Book> bookList = new ArrayList<>();

    @BeforeEach
    void setup() {
        book = new Book("64bc159605856a0c8f3404fa","Harry Potter and the Order of the Phoenix","J. K. Rowling",
                "Fantasy",7,"Paperback","Harry Potter",816,true,8.99);
    }

    @Test
    public void testAddBook() throws Exception {
        Book bookRequest = new Book("Harry Potter and the Order of the Phoenix","J. K. Rowling",
                "Fantasy",7,"Paperback","Harry Potter",816,true,8.99
        );

        given(bookService.addBook(any(Book.class))).willReturn(book);

        mockMvc.perform(post("/api/books/addBook").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName").value(book.getBookName()))
                .andDo(print());
    }

    @Test
    public void testUpdateBookField() throws Exception {
        String newValue = "Akhil";
        book.setAuthor(newValue);
        given(bookService.updateBookField(any(),any(),any())).willReturn(book);

        mockMvc.perform(get("/api/books/updateBookField")
                        .param(Constants.BOOKID, book.getBookId())
                        .param("fieldName",Constants.AUTHOR)
                        .param("newValue",newValue))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateBookFieldForIntField() throws Exception {
        String newValue = "250";
        book.setPages(Integer.parseInt(newValue));
        given(bookService.updateBookField(any(),any(),any())).willReturn(book);

        mockMvc.perform(get("/api/books/updateBookField")
                        .param(Constants.BOOKID, book.getBookId())
                        .param("fieldName",Constants.PAGES)
                        .param("newValue",newValue))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateBookFieldForBooleanField() throws Exception {
        String newValue = "true";
        book.setInStock(Boolean.parseBoolean(newValue));
        given(bookService.updateBookField(any(),any(),any())).willReturn(book);

        mockMvc.perform(get("/api/books/updateBookField")
                        .param(Constants.BOOKID, book.getBookId())
                        .param("fieldName",Constants.INSTOCK)
                        .param("newValue",newValue))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateBookFieldForDoubleField() throws Exception {
        String newValue = "5.99";
        book.setPrice(Double.parseDouble(newValue));
        given(bookService.updateBookField(any(),any(),any())).willReturn(book);

        mockMvc.perform(get("/api/books/updateBookField")
                        .param(Constants.BOOKID, book.getBookId())
                        .param("fieldName",Constants.PRICE)
                        .param("newValue",newValue))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book updateBook = new Book("64bc159605856a0c8f3404fa","Harry Potter and the Order of the Phoenix","J. K. Rowling",
                "Fantasy",7,"Paperback","Harry Potter",816,true,8.99);
        given(bookService.updateBook(any(Book.class))).willReturn(book);

        mockMvc.perform(put("/api/books/updateBook")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(updateBook)))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void testFindAllBook() throws Exception {
        bookList.add(book);
        Page<Book> bookPagedMockResponse = new PageImpl<>(bookList);

        given(bookService.findAllBook(any(),any(),any(),any(),anyInt(),anyInt(),any(),any(),anyInt(),anyInt(),
                anyBoolean(),anyDouble(),anyDouble(),anyInt(),anyInt(),any(),any()))
                .willReturn(bookPagedMockResponse);


        mockMvc.perform(get("/api/books")).andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testFindAllBookWithParam() throws Exception {
        bookList.add(book);
        Page<Book> bookPagedMockResponse = new PageImpl<>(bookList);

        given(bookService.findAllBook(any(),any(),any(),any(),anyInt(),anyInt(),any(),any(),anyInt(),anyInt(),
                anyBoolean(),anyDouble(),anyDouble(),anyInt(),anyInt(),any(),any()))
                .willReturn(bookPagedMockResponse);


        mockMvc.perform(get("/api/books")
                        .param("bookId", book.getBookId())
                        .param("bookName", book.getBookName())
                        .param("author", book.getAuthor())
                        .param("genre", book.getGenre())
                        .param("lessThanVolume", String.valueOf(5))
                        .param("bookType", book.getBookType())
                        .param("series", book.getSeries())
                        .param("moreThanPages", String.valueOf(book.getPages()))
                        .param("inStock", String.valueOf(book.isInStock()))
                        .param("moreThanPrice", String.valueOf(book.getPrice()))
                ).andExpect(status().isOk())
                .andReturn();
    }
}

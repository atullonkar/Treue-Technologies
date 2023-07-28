package ad.demo.onlinebookstore.controller;

import ad.demo.onlinebookstore.model.Book;
import ad.demo.onlinebookstore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Online Book Store")
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/addBook",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Add a book",
            description = "Add a book with body. Returns created book with Id"
    )
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @DeleteMapping(value = "/deleteBook")
    @Operation(
            summary = "Delete a book",
            description = "Delete a book with Id"
    )
    public void deleteBook (
            @Parameter(description = "Book Id for which the book will be deleted")
            @RequestParam String bookId
    ) throws Exception {
        bookService.deleteBook(bookId);
    }

    @GetMapping(value = "/updateBookField",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Update a book field",
            description = "Update any field of the book with bookId"
    )
    public Book updateBookField(
            @RequestParam
            @Parameter(description = "Filter the book by Id")
            String bookId,
            @RequestParam
            @Parameter(description = "Field name which needs to be updated")
            String fieldName,
            @RequestParam
            @Parameter(description = "Value to be updated in the database")
            String newValue
    ) {
        return bookService.updateBookField(bookId,fieldName,newValue);
    }

    @PutMapping(value = "/updateBook",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @Operation(
            summary = "Update a book",
            description = "Update the entire book with bookId"
    )
    public Book updateBook(@RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Find all books",
            description = "Find all books with or without various parameters"
    )
    public Page<Book> findAllBook(
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by id")
            String bookId,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by name")
            String bookName,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by author name")
            String author,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by genre")
            String genre,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by volume for less than the given value (included)")
            Integer lessThanVolume,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by volume for more than the given value (included)")
            Integer moreThanVolume,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by it's type")
            String bookType,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by series")
            String series,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by pages for more than the given value (included)")
            Integer moreThanPages,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by pages for less than the given value (included)")
            Integer lessThanPages,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books based on if in stock or not")
            Boolean inStock,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by price for less than the given value (included)")
            Double lessThanPrice,
            @RequestParam(required = false)
            @Parameter(description = "Used to filter books by price for more than the given value (included)")
            Double moreThanPrice,
            @RequestParam(required = false, defaultValue = "0")
            @Parameter(description = "Displaying page")
            int page,
            @RequestParam(required = false, defaultValue = "10")
            @Parameter(description = "Page size")
            int size,
            @RequestParam(required = false, defaultValue = "author")
            @Parameter(description = "Sort by using author name")
            String sortBy,
            @RequestParam(required = false, defaultValue = "DESC")
            @Parameter(description = "Sort by using direction")
            Sort.Direction direction
    ){
        return bookService.findAllBook(bookId,bookName,author,genre,lessThanVolume,moreThanVolume,bookType,
                series,moreThanPages,lessThanPages,inStock,lessThanPrice,moreThanPrice,page,size,sortBy,direction);
    }
}

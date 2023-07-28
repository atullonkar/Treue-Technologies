package ad.demo.onlinebookstore.repository;

import ad.demo.onlinebookstore.model.Book;
import ad.demo.onlinebookstore.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.FindAndReplaceOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository{

    private final Logger logger = LoggerFactory.getLogger(BookRepositoryImpl.class);

    private final MongoTemplate mongoTemplate;

    public BookRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Book addBook(Book book) {
        mongoTemplate.save(book);
        logger.info("Adding book with bookId: " + book.getBookId() + ", bookName: " + book.getBookName());
        return book;
    }

    @Override
    public void deleteBook(String bookId) throws IllegalArgumentException{
        Query query = new Query();
        query.addCriteria(Criteria.where(Constants.BOOKID).is(bookId));
        Book book = mongoTemplate.findOne(query, Book.class);
        if(null != book){
            logger.info("Deleted book: " + book.getBookName());
            mongoTemplate.remove(book);
        }
        else
            throw new IllegalArgumentException("Book with book id: " + bookId + " does not exist!");
    }

    @Override
    public Book updateBookField(String bookId, String fieldName, String newValue) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Constants.BOOKID).is(bookId));
        Update updateField = new Update();
        if (fieldName.equalsIgnoreCase(Constants.VOLUME) ||
                fieldName.equalsIgnoreCase(Constants.PAGES)){
            int newIntValue = Integer.parseInt(newValue);
            updateField.set(fieldName,newIntValue);
        } else if (fieldName.equalsIgnoreCase(Constants.PRICE)) {
            double newDoubleValue = Double.parseDouble(newValue);
            updateField.set(fieldName,newDoubleValue);
        } else if (fieldName.equalsIgnoreCase(Constants.INSTOCK)) {
            boolean newBooleanValue = Boolean.parseBoolean(newValue);
            updateField.set(fieldName,newBooleanValue);
        } else {
            updateField.set(fieldName,newValue);
        }

        FindAndModifyOptions options = new FindAndModifyOptions();
        options.returnNew(true);

        logger.info("Updating " + fieldName + " field for book: " + bookId);
        return mongoTemplate.findAndModify(query,updateField,options,Book.class);
    }

    @Override
    public Book updateBook(Book book) {
        Query query = new Query();
        query.addCriteria(Criteria.where(Constants.BOOKID).is(book.getBookId()));
        FindAndReplaceOptions options = new FindAndReplaceOptions();
        options.returnNew();

        logger.info("Updating book: " + book.getBookId());
        return mongoTemplate.findAndReplace(query,book,options);
    }

    @Override
    public Page<Book> findAllBook(String bookId, String bookName, String author, String genre, Integer lessThanVolume,
                                  Integer moreThanVolume, String bookType, String series, Integer moreThanPages,
                                  Integer lessThanPages, Boolean inStock, Double lessThanPrice, Double moreThanPrice,
                                  PageRequest pageable) {
        Query query = new Query().with(pageable);
        if (null != bookId)
            query.addCriteria(Criteria.where(Constants.BOOKID).is(bookId));
        if (null != bookName)
            query.addCriteria(Criteria.where(Constants.BOOKNAME).is(bookName));
        if (null != author)
            query.addCriteria(Criteria.where(Constants.AUTHOR).is(author));
        if (null != genre)
            query.addCriteria(Criteria.where(Constants.GENRE).is(genre));
        if (null != lessThanVolume)
            query.addCriteria(Criteria.where(Constants.VOLUME).lte(lessThanVolume));
        if (null != moreThanVolume)
            query.addCriteria(Criteria.where(Constants.VOLUME).gte(moreThanVolume));
        if (null != bookType)
            query.addCriteria(Criteria.where(Constants.BOOKTYPE).is(bookType));
        if (null != series)
            query.addCriteria(Criteria.where(Constants.SERIES).is(series));
        if (null != lessThanPages)
            query.addCriteria(Criteria.where(Constants.PAGES).lte(lessThanPages));
        if (null != moreThanPages)
            query.addCriteria(Criteria.where(Constants.PAGES).gte(moreThanPages));
        if (null != inStock)
            query.addCriteria(Criteria.where(Constants.INSTOCK).is(inStock));
        if (null != lessThanPrice)
            query.addCriteria(Criteria.where(Constants.PRICE).lte(lessThanPrice));
        if (null != moreThanPrice)
            query.addCriteria(Criteria.where(Constants.PRICE).gte(moreThanPrice));

        List<Book> bookList = mongoTemplate.find(query, Book.class);
        return PageableExecutionUtils.getPage(
                bookList,pageable,
                () ->mongoTemplate.count(Query.of(query).limit(-1).skip(-1),Book.class)
        );
    }
}

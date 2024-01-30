package com.anafernandes.catalog.service;

import com.anafernandes.catalog.controller.BookRequest;
import com.anafernandes.catalog.controller.StockRequest;
import com.anafernandes.catalog.dto.AuthorDto;
import com.anafernandes.catalog.dto.AuthorMapper;
import com.anafernandes.catalog.dto.BookDto;
import com.anafernandes.catalog.dto.BookMapper;
import com.anafernandes.catalog.model.Author;
import com.anafernandes.catalog.model.Availability;
import com.anafernandes.catalog.model.Book;
import com.anafernandes.catalog.model.Category;
import com.anafernandes.catalog.repository.AuthorRepository;
import com.anafernandes.catalog.repository.BookRepository;
import com.anafernandes.catalog.repository.CategoryRepository;
import com.anafernandes.stock.dto.StockDto;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class CatalogService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final RestTemplate restTemplate;
    private final AuthorMapper authorMapper;
    private final BookMapper bookMapper;


    public BookDto registerBook(BookRequest bookRequest) {

        Set<Author> authors = new HashSet<Author>();

        for (String authorName : bookRequest.getAuthors()) {
            Author author = authorRepository.getAuthorByName(authorName);
            authors.add(author);
        }

        Category category = categoryRepository.getCategoryByName(bookRequest.getCategory());

        Book book = Book.builder()
                .title(bookRequest.getTitle())
                .originalTitle(bookRequest.getOriginalTitle())
                .language(bookRequest.getLanguage())
                .isbn(bookRequest.getIsbn())
                .stockAvailable(bookRequest.getStockAvailable())
                .price(bookRequest.getPrice())
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .build();

        book.setAuthors(authors);
        book.setCategory(category);
        book.setAvailability(Availability.valueOf(bookRequest.getAvailability()));


        Book bookSaved = bookRepository.saveAndFlush(book);

        addStock(book);
        
        return bookMapper.toDto(bookSaved);

    }


    public void addStock(Book book) {
        StockRequest stockRequest = new StockRequest(book.getId(), book.getStockAvailable());
        restTemplate.postForObject(
                "http://STOCK/api/v1/stock",
                stockRequest,
                StockRequest.class
        );
    }

    @Retry(name = "getStockRetry", fallbackMethod = "getStockFallback")
    public Integer getBookStock(Integer bookId) {
        StockDto stockDto = restTemplate.getForObject(
                "http://STOCK/api/v1/stock/{bookId}",
                StockDto.class,
                bookId
        );

        return stockDto.getStockAvailable();
    }

    public Integer getStockFallback(Integer bookId, Exception exception) {
        return 0;
    }

    public BookDto GetBookDetails(String bookTitle) {

        var book = bookRepository.getBookByTitle(bookTitle);

        if (book != null) {

            var bookId = book.getId();

            StockDto stockDto = restTemplate.getForObject(
                    "http://STOCK/api/v1/stock/{bookId}",
                    StockDto.class,
                    bookId
            );

            book.setStockAvailable(stockDto.getStockAvailable());
        }
        return bookMapper.toDto(book);
    }

    public List<BookDto> GetAllBooks() {

        List<Book> books = bookRepository.findAll();

        for (Book book : books) {

            book.setStockAvailable(getBookStock(book.getId()));

        }
        return bookMapper.toDtoList(books);
    }

    public List<BookDto> GetAvailableBooks() {

        List<Book> availableBooks = bookRepository.getAvailableBooks();

        for (Book book : availableBooks) {

            book.setStockAvailable(getBookStock(book.getId()));

        }

        return bookMapper.toDtoList(availableBooks);
    }

    public void addAuthors(List<AuthorDto> authorRequests) {

        List<Author> authors = authorMapper.toEntityList(authorRequests);

        authorRepository.saveAll(authors);
    }

    public AuthorDto addAuthor(AuthorDto authorRequest) {

        Author author = authorMapper.toEntity(authorRequest);

        return authorMapper.toDto(authorRepository.save(author));
    }

    public AuthorDto GetAuthorByName(String name) {

        Author author = authorRepository.getAuthorByName(name);
        return authorMapper.toDto(author);
    }

    public void addCategory(String categoryName) {

        Category category = Category.builder()
                .name(categoryName).build();

        categoryRepository.save(category);

    }

    public List<BookDto> filterBooks(String authorName, String categoryName, Double maxPrice) {


        Integer categoryId = null;

        Integer bookId = null;

        if (!StringUtils.isEmpty(categoryName)) {

            categoryId = categoryRepository.getCategoryByName(categoryName).getId();
        }

        if (!StringUtils.isEmpty(authorName)) {

            bookId = authorRepository.getAuthorByName(authorName).getId();

        }

        return bookMapper.toDtoList(bookRepository.filterBooks(bookId, categoryId, maxPrice));
    }
}

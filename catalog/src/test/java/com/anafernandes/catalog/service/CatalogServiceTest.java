package com.anafernandes.catalog.service;

import com.anafernandes.catalog.controller.BookRequest;
import com.anafernandes.catalog.controller.StockRequest;
import com.anafernandes.catalog.dto.AuthorDto;
import com.anafernandes.catalog.dto.AuthorMapper;
import com.anafernandes.catalog.dto.BookMapper;
import com.anafernandes.catalog.model.Author;
import com.anafernandes.catalog.model.Book;
import com.anafernandes.catalog.model.Category;
import com.anafernandes.catalog.repository.AuthorRepository;
import com.anafernandes.catalog.repository.BookRepository;
import com.anafernandes.catalog.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatalogServiceTest {
    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private AuthorMapper authorMapper;

    private CatalogService catalogService;

    @BeforeEach
    void setUp() {
        catalogService = new CatalogService(
                bookRepository,
                authorRepository,
                categoryRepository,
                restTemplate,
                authorMapper,
                bookMapper);
    }

    @Test
    void registerBook() {
        //given
        ArrayList<String> authors = new ArrayList<>();
        authors.add("Author1");

        BookRequest bookRequest = BookRequest.builder()
                .title("Book1")
                .originalTitle("Book1")
                .language("Portuguese")
                .isbn(123456)
                .stockAvailable(2)
                .price(10.0)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .authors(authors)
                .category("Category1")
                .build();

        //when
        catalogService.registerBook(bookRequest);
        //then
        verify(categoryRepository).getCategoryByName(any(String.class));
        verify(authorRepository).getAuthorByName(any(String.class));
        verify(bookRepository).saveAndFlush(any(Book.class));
        verify(restTemplate).postForObject(eq("http://STOCK/api/v1/stock"), any(StockRequest.class), eq(StockRequest.class));
    }

    @Test
    void getBookDetails() {
        //when
        catalogService.GetBookDetails("Book1");
        //then
        verify(bookRepository).getBookByTitle(any(String.class));

    }

    @Test
    void getAllBooks() {

        //when
        catalogService.GetAllBooks();
        //then
        verify(bookRepository).findAll();
    }

    @Test
    void addAuthors() {

        //given
        List<AuthorDto> authorDtos = new ArrayList<>();

        var author = AuthorDto.builder()
                .name("Author1")
                .build();

        authorDtos.add(author);
        //when
        catalogService.addAuthors(authorDtos);
        //then
        verify(authorRepository).saveAll(anyList());
    }

    @Test
    void addAuthor() {
        //given
        var author = Author.builder()
                .name("Author1")
                .build();

        when(authorMapper.toEntity(any(AuthorDto.class))).thenReturn(author);

        var authorDto = AuthorDto.builder()
                .name("Author1")
                .build();

        //when
        catalogService.addAuthor(authorDto);
        //then
        verify(authorRepository).save(any(Author.class));

    }

    @Test
    void getAuthorByName() {

        catalogService.GetAuthorByName("Author1");
        //then
        verify(authorRepository).getAuthorByName(any(String.class));
    }

    @Test
    void filterBooksAllParameters() {
        //given
        String authorName = "Author1";
        String categoryName = "Category1";
        Double maxPrice = 10.0;

        var category = Category.builder()
                .name("Category1")
                .id(1)
                .build();

        when(categoryRepository.getCategoryByName(any(String.class))).thenReturn(category);

        var author = Author.builder()
                .name("Author1")
                .id(1)
                .build();

        when(authorRepository.getAuthorByName(any(String.class))).thenReturn(author);

        catalogService.filterBooks(authorName, categoryName, maxPrice);


        verify(bookRepository).filterBooks(author.getId(), category.getId(), maxPrice);

    }

    @Test
    void filterBooksWithNullParameters() {
        //given
        String authorName = null;
        String categoryName = null;
        Double maxPrice = null;

        catalogService.filterBooks(authorName, categoryName, maxPrice);

        verify(bookRepository).filterBooks(null, null, null);

    }

}
package com.anafernandes.catalog.repository;

import com.anafernandes.catalog.model.Author;
import com.anafernandes.catalog.model.Book;
import com.anafernandes.catalog.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        var author = Author.builder()
                .name("Author1")
                .build();

        authorRepository.save(author);

        Category category = Category.builder()
                .name("Drama")
                .build();

        categoryRepository.save(category);

        HashSet<Author> authors = new HashSet<>();
        authors.add(author);

        Book book = Book.builder()
                .title("Book1")
                .originalTitle("Book1")
                .language("Portuguese")
                .isbn(123456)
                .stockAvailable(2)
                .price(10.0)
                .dateCreated(LocalDateTime.now())
                .dateUpdated(LocalDateTime.now())
                .authors(authors)
                .category(category)
                .build();

        bookRepository.save(book);

    }

    @AfterEach
    void tearDown() {

        bookRepository.deleteAll();
        authorRepository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    void getBookByTitle() {

        //when
        Book bookResponse = bookRepository.getBookByTitle("Book1");
        // then
        assertThat(bookResponse).isNotNull();
        assertThat(bookResponse.getTitle()).isEqualTo("Book1");
    }

    @Test
    void getBooksByAuthor() {

        //when

        Author author = authorRepository.getAuthorByName("Author1");
        List<Book> bookResponse = bookRepository.getBooksByAuthor(author.getId());
        // then
        assertThat(bookResponse).isNotNull();
        assertThat(bookResponse.isEmpty()).isFalse();
        assertThat(bookResponse.getFirst().getTitle()).isEqualTo("Book1");
    }

    @ParameterizedTest
    @MethodSource("filterParams")
    void filterBooks(Integer authorId, Integer categoryId, Double maxPrice) {

        //when
        List<Book> bookResponse = bookRepository.filterBooks(authorId, categoryId, maxPrice);
        // then
        assertThat(bookResponse).isNotNull();
        assertThat(bookResponse.isEmpty()).isFalse();
        assertThat(bookResponse.getFirst().getTitle()).isEqualTo("Book1");
    }


    private static Stream<Arguments> filterParams() {
        return Stream.of(
                arguments(1, null, null),
                arguments(null, 2, null),
                arguments(null, null, 20.0)
        );
    }
}
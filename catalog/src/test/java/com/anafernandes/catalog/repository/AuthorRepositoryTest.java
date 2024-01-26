package com.anafernandes.catalog.repository;

import com.anafernandes.catalog.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @AfterEach
    void tearDown() {
        authorRepository.deleteAll();
    }

    @Test
    void getAuthorByName() {
        //given
        var author = Author.builder()
                .name("Author1")
                .build();

        authorRepository.save(author);

        //when
        Author authorResponse = authorRepository.getAuthorByName("Author1");
        // then
        assertThat(authorResponse).isNotNull();
        assertThat(authorResponse.getName()).isEqualTo("Author1");
    }
}
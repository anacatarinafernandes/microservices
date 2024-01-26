package com.anafernandes.catalog.repository;

import com.anafernandes.catalog.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    CategoryRepository categoryRepository;

    @AfterEach
    void tearDown() {
        categoryRepository.deleteAll();
    }

    @Test
    void getCategoryByName() {
        //given
        Category category = Category.builder()
                .name("Drama")
                .build();

        categoryRepository.save(category);

        //when
        Category categoryResponse = categoryRepository.getCategoryByName("Drama");
        // then
        assertThat(categoryResponse).isNotNull();
        assertThat(categoryResponse.getName()).isEqualTo("Drama");
    }
}
package com.anafernandes.catalog.repository;

import com.anafernandes.catalog.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT s FROM Author s WHERE s.name=?1")
    Author getAuthorByName(String name);
}

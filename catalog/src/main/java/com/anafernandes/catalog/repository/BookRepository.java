package com.anafernandes.catalog.repository;

import com.anafernandes.catalog.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("select s from Book s WHERE s.title=?1")
    Book getBookByTitle(String title);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE a.id = ?1")
    List<Book> getBooksByAuthor(Integer authorId);

    @Query("SELECT b FROM Book b JOIN b.authors a WHERE (:authorId is null or a.id= :authorId )AND (:categoryId is null OR b.category.Id = :categoryId) AND (:maxPrice is null OR b.price <= :maxPrice)")
    List<Book> filterBooks(@Param("authorId") Integer authorId, @Param("categoryId") Integer categoryId, @Param("maxPrice") Double maxPrice);

}
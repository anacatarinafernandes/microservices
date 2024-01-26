package com.anafernandes.catalog.repository;

import com.anafernandes.catalog.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select s from Category s WHERE s.name=?1")
    Category getCategoryByName(String name);
}

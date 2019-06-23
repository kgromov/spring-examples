package com.examples.recipeproject.repositories;

import com.examples.recipeproject.model.Category;
import com.examples.recipeproject.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by konstantin on 26.05.2019.
 */
public interface CategoryRepository extends CrudRepository<Category, Long> {

    Optional<Category> findByDescription(String description);
}

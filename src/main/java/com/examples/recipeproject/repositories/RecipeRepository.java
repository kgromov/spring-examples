package com.examples.recipeproject.repositories;

import com.examples.recipeproject.model.Recipe;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by konstantin on 26.05.2019.
 */
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

package com.examples.recipeproject.services;

import com.examples.recipeproject.model.Recipe;

/**
 * Created by konstantin on 26.05.2019.
 */
public interface RecipeService {
    Iterable<Recipe> getRecipes();
}

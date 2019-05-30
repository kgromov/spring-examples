package com.examples.recipeproject.services;

import com.examples.recipeproject.model.Recipe;
import com.examples.recipeproject.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by konstantin on 26.05.2019.
 */
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new TreeSet<>(Comparator.comparing(Recipe::getId));
        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }
}

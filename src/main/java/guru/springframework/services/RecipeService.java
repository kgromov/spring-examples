package guru.springframework.services;


import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;

import java.util.Set;

/**
 * Created by jt on 6/13/17.
 */
public interface RecipeService {

    Set<Recipe> findAll();

    Recipe findById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    Recipe saveRecipeCommand(Recipe recipe);

    RecipeCommand findCommandById(Long l);
}

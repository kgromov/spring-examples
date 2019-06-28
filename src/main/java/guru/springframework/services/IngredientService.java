package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;

/**
 * Created by konstantin on 28.06.2019.
 */
public interface IngredientService {

    IngredientCommand findById(Long recipeId, Long ingredientId);
}

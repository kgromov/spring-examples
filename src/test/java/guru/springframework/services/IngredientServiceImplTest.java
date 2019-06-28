package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * Created by konstantin on 28.06.2019.
 */
public class IngredientServiceImplTest {
    @Mock
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    @Mock
    private RecipeRepository recipeRepository;

    private IngredientService ingredientService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ingredientService = new IngredientServiceImpl(recipeRepository, ingredientToIngredientCommand);
    }

    @Test
    public void findById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        // ingredients
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);
        recipe.addIngredient(ingredient1);
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);
        recipe.addIngredient(ingredient2);

        Optional<Recipe> recipeOptional = Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand ingredientCommand = ingredientService.findById(recipe.getId(), ingredient2.getId());
        assertEquals(recipe.getId(), ingredientCommand.getRecipeId());
        assertEquals(ingredient2.getId(), ingredientCommand.getId());
        verify(recipeRepository, times(1)).findById(anyLong());
    }

}
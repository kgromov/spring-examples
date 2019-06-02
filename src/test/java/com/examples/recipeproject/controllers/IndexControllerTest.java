package com.examples.recipeproject.controllers;

import com.examples.recipeproject.model.Recipe;
import com.examples.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Created by konstantin on 30.05.2019.
 */
public class IndexControllerTest {
    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;

    private IndexController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // No @Mock is required
    /*    recipeService = Mockito.mock(RecipeService.class);
        model = Mockito.mock(Model.class);*/
        controller = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() throws Exception {
        //given
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(new Recipe());

        Recipe recipe = new Recipe();
        recipe.setId(1L);

        recipes.add(recipe);

        when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.getIndexPage(model);


        //then
        assertEquals("index", viewName);
        verify(recipeService, times(1)).getRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());
        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    };

}
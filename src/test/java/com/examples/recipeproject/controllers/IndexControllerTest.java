package com.examples.recipeproject.controllers;

import com.examples.recipeproject.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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
        assertEquals("index", controller.getIndexPage(model));

        verify(recipeService, times(1)).getRecipes();
//        verify(model, times(1)).addAttribute(eq("recipes"), anySet());
        verify(model, times(1)).addAttribute("recipes", recipeService.getRecipes());
    }

}
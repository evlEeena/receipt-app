package com.evleeena.receiptapp.controllers;

import com.evleeena.receiptapp.domain.Recipe;
import com.evleeena.receiptapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IndexControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    private IndexController indexController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        indexController = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(Recipe.builder().description("Chocolate Cake").build());
        recipes.add(Recipe.builder().description("Ice cream").build());

        when(recipeService.getAllRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);

        String indexPage = indexController.getIndexPage(model);
        assertEquals("index", indexPage);
        verify(recipeService).getAllRecipes();
        verify(model).addAttribute(eq("recipes"), captor.capture());

        Set<Recipe> captorValue = captor.getValue();
        assertNotNull(captorValue);
        assertEquals(2, captorValue.size());
    }
}
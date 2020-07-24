package com.evleeena.receiptapp.controllers;

import com.evleeena.receiptapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

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
        String indexPage = indexController.getIndexPage(model);
        assertEquals("index", indexPage);
        verify(recipeService).getAllRecipes();
        verify(model).addAttribute(eq("recipes"), anySet());
    }
}
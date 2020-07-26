package com.evleeena.receiptapp.controllers;

import com.evleeena.receiptapp.domain.Recipe;
import com.evleeena.receiptapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
    public void testMockMvc() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        Recipe cake = new Recipe();
        cake.setDescription("Chocolate Cake");

        Recipe ice = new Recipe();
        ice.setDescription("Ice cream");

        Set<Recipe> recipes = new HashSet<>();
        recipes.add(cake);
        recipes.add(ice);

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
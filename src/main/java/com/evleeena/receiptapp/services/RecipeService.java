package com.evleeena.receiptapp.services;

import com.evleeena.receiptapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipes();
}

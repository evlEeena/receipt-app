package com.evleeena.receiptapp.services;

import com.evleeena.receiptapp.domain.Recipe;
import com.evleeena.receiptapp.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Resource
    private RecipeRepository recipeRepository;

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        return recipes;
    }
}

package com.evleeena.receiptapp.controllers;

import com.evleeena.receiptapp.domain.Category;
import com.evleeena.receiptapp.domain.Recipe;
import com.evleeena.receiptapp.domain.UnitOfMeasure;
import com.evleeena.receiptapp.repositories.CategoryRepository;
import com.evleeena.receiptapp.repositories.RecipeRepository;
import com.evleeena.receiptapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.Optional;

@Controller
public class IndexController {

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Resource
    private RecipeRepository recipeRepository;

    @RequestMapping({"", "/", "/index.html"})
    public String getIndexPage(Model model) {

        Optional<Category> category = categoryRepository.findByDescription("Russian");
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Cup");

        System.out.println("Category: " + category);
        System.out.println("UOM: " + uom);

        Iterable<Recipe> recipes = recipeRepository.findAll();
        model.addAttribute("recipes", recipes);

        return "index";
    }
}

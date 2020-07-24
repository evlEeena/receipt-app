package com.evleeena.receiptapp.controllers;

import com.evleeena.receiptapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index.html"})
    public String getIndexPage(Model model) {

        log.debug("Loaded index page");

        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "index";
    }
}

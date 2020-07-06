package com.evleeena.receiptapp.bootstrap;

import com.evleeena.receiptapp.domain.*;
import com.evleeena.receiptapp.repositories.CategoryRepository;
import com.evleeena.receiptapp.repositories.RecipeRepository;
import com.evleeena.receiptapp.repositories.UnitOfMeasureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Resource
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Resource
    private CategoryRepository categoryRepository;

    @Resource
    private RecipeRepository recipeRepository;

    @Override
    public void run(String... args) {
        Recipe guacamole = createGuacamole();
        System.out.println("Recipe: " + guacamole);
    }

    private UnitOfMeasure getOrCreateUom(String uomName) {
        return unitOfMeasureRepository.findByUom(uomName).orElseGet(() -> {
            UnitOfMeasure uom = new UnitOfMeasure();
            uom.setUom(uomName);
            unitOfMeasureRepository.save(uom);
            return uom;
        });
    }

    private Recipe createGuacamole() {
        Category mexican = categoryRepository.findByDescription("Mexican").orElseGet(() -> {
            Category category = new Category();
            category.setDescription("Mexican");
            return category;
        });

        UnitOfMeasure piece = getOrCreateUom("Piece");
        UnitOfMeasure teaspoon = getOrCreateUom("Teaspoon");
        UnitOfMeasure tablespoon = getOrCreateUom("Tablespoon");
        UnitOfMeasure pinch = getOrCreateUom("Pinch");

        Ingredient avocado = new Ingredient();
        avocado.setDescription("ripe avocados");
        avocado.setAmount(new BigDecimal("2.0"));
        avocado.setUom(piece);

        //1/4 teaspoon of salt, more to taste
        Ingredient salt = new Ingredient();
        salt.setDescription("salt");
        salt.setAmount(new BigDecimal("0.25"));
        salt.setUom(teaspoon);

        //1 tablespoon fresh lime juice or lemon juice
        Ingredient limeJuice = new Ingredient();
        limeJuice.setDescription("fresh lime juice");
        limeJuice.setAmount(new BigDecimal("1.0"));
        limeJuice.setUom(tablespoon);

        //2 tablespoons to 1/4 cup of minced red onion or thinly sliced green onion
        Ingredient onion = new Ingredient();
        onion.setDescription("minced red onion");
        onion.setAmount(new BigDecimal("2.0"));
        onion.setUom(tablespoon);

        //1-2 serrano chiles, stems and seeds removed, minced
        Ingredient chiles = new Ingredient();
        chiles.setDescription("serrano chiles");
        chiles.setAmount(new BigDecimal("2.0"));
        chiles.setUom(piece);

        // A dash of freshly grated black pepper
        Ingredient pepper = new Ingredient();
        pepper.setDescription("freshly grated black pepper");
        pepper.setAmount(new BigDecimal("1.0"));
        pepper.setUom(pinch);

        //1/2 ripe tomato, seeds and pulp removed, chopped
        Ingredient tomato = new Ingredient();
        tomato.setDescription("ripe tomato");
        tomato.setAmount(new BigDecimal("0.5"));
        tomato.setUom(piece);

        //Red radishes or jicama, to garnish
        Ingredient radishes = new Ingredient();
        radishes.setDescription("red radishes");
        radishes.setAmount(new BigDecimal("1.0"));
        radishes.setUom(piece);

        //Tortilla chips, to serve
        Ingredient chips = new Ingredient();
        chips.setDescription("tortilla chips");
        chips.setAmount(new BigDecimal("10.0"));
        chips.setUom(piece);

        Set<Ingredient> guacamoleIngredients = new HashSet<>();
        guacamoleIngredients.add(avocado);
        guacamoleIngredients.add(salt);
        guacamoleIngredients.add(limeJuice);
        guacamoleIngredients.add(onion);
        guacamoleIngredients.add(chiles);
        guacamoleIngredients.add(pepper);
        guacamoleIngredients.add(tomato);
        guacamoleIngredients.add(radishes);
        guacamoleIngredients.add(chips);

        Recipe guacamole = new Recipe();
        guacamole.setDescription("How to Make Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(10);
        guacamole.setServings(2);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.setIngredients(guacamoleIngredients);
        guacamole.getCategories().add(mexican);
        recipeRepository.save(guacamole);

        guacamoleIngredients.forEach(i -> i.setRecipe(guacamole));

        mexican.setRecipes(new HashSet<>(Collections.singletonList(guacamole)));
        categoryRepository.save(mexican);

        return guacamole;
    }

    private void createChickenTacos() {
        /*2 tablespoons ancho chili powder
1 teaspoon dried oregano
1 teaspoon dried cumin
1 teaspoon sugar
1/2 teaspoon salt
1 clove garlic, finely chopped
1 tablespoon finely grated orange zest
3 tablespoons fresh-squeezed orange juice
2 tablespoons olive oil
4 to 6 skinless, boneless chicken thighs (1 1/4 pounds)
To serve:

8 small corn tortillas
3 cups packed baby arugula (3 ounces)
2 medium ripe avocados, sliced
4 radishes, thinly sliced
1/2 pint cherry tomatoes, halved
1/4 red onion, thinly sliced
Roughly chopped cilantro
1/2 cup sour cream thinned with 1/4 cup milk
1 lime, cut into wedges*/
    }
}

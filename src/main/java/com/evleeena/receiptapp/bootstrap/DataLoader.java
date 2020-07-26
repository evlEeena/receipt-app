package com.evleeena.receiptapp.bootstrap;

import com.evleeena.receiptapp.domain.*;
import com.evleeena.receiptapp.repositories.CategoryRepository;
import com.evleeena.receiptapp.repositories.RecipeRepository;
import com.evleeena.receiptapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
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
        log.debug("Guacamole: {}", guacamole);

        Recipe chickenTacos = createChickenTacos();
        log.debug("Chicken tacos: {}", chickenTacos);
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

        Recipe guacamole = new Recipe();
        guacamole.setDescription("How to Make Perfect Guacamole");
        guacamole.setPrepTime(10);
        guacamole.setCookTime(10);
        guacamole.setServings(2);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDifficulty(Difficulty.EASY);
        guacamole.getCategories().add(mexican);
        recipeRepository.save(guacamole);


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

        guacamoleIngredients.forEach(i -> i.setRecipe(guacamole));

        guacamole.setIngredients(guacamoleIngredients);
        mexican.setRecipes(new HashSet<>(Collections.singletonList(guacamole)));

        categoryRepository.save(mexican);
        recipeRepository.save(guacamole);

        return guacamole;
    }

    private Recipe createChickenTacos() {
        Category mexican = categoryRepository.findByDescription("Mexican").orElseGet(() -> {
            Category category = new Category();
            category.setDescription("Mexican");
            return category;
        });

        Recipe tacos = new Recipe();
        tacos.setDescription("Spicy Grilled Chicken Tacos");
        tacos.setPrepTime(20);
        tacos.setCookTime(15);
        tacos.setServings(6);
        tacos.setUrl("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacos.setDifficulty(Difficulty.MODERATE);
        tacos.getCategories().add(mexican);

        recipeRepository.save(tacos);

        UnitOfMeasure tablespoon = getOrCreateUom("Tablespoon");
        UnitOfMeasure teaspoon = getOrCreateUom("Teaspoon");
        UnitOfMeasure piece = getOrCreateUom("Piece");
        UnitOfMeasure pound = getOrCreateUom("Pound");
        UnitOfMeasure cup = getOrCreateUom("Cup");

        //2 tablespoons ancho chili powder
        Ingredient chiliPowed = new Ingredient();
        chiliPowed.setDescription("ancho chili powder");
        chiliPowed.setAmount(new BigDecimal("2"));
        chiliPowed.setUom(tablespoon);

        //1 teaspoon dried oregano
        Ingredient oregano = new Ingredient();
        oregano.setDescription("dried oregano");
        oregano.setAmount(new BigDecimal("1"));
        oregano.setUom(teaspoon);

        //1 teaspoon dried cumin
        Ingredient cumin = new Ingredient();
        cumin.setDescription("dried cumin");
        cumin.setAmount(new BigDecimal("1"));
        cumin.setUom(teaspoon);

        // 1 teaspoon sugar
        Ingredient sugar = new Ingredient();
        sugar.setDescription("sugar");
        sugar.setAmount(new BigDecimal("1"));
        sugar.setUom(teaspoon);

        // 1/2 teaspoon salt
        Ingredient salt = new Ingredient();
        salt.setDescription("salt");
        salt.setAmount(new BigDecimal("0.5"));
        salt.setUom(teaspoon);

        // 1 clove garlic, finely chopped
        Ingredient garlic = new Ingredient();
        garlic.setDescription("garlic");
        garlic.setAmount(new BigDecimal("1"));
        garlic.setUom(piece);

        //1 tablespoon finely grated orange zest
        Ingredient orangeZest = new Ingredient();
        orangeZest.setDescription("orange zest");
        orangeZest.setAmount(new BigDecimal("1"));
        orangeZest.setUom(tablespoon);

        // 3 tablespoons fresh-squeezed orange juice
        Ingredient orangeJuice = new Ingredient();
        orangeJuice.setDescription("fresh-squeezed orange juice");
        orangeJuice.setAmount(new BigDecimal("3"));
        orangeJuice.setUom(tablespoon);

        // 2 tablespoons olive oil
        Ingredient oliveOil = new Ingredient();
        oliveOil.setDescription("olive oi2");
        oliveOil.setAmount(new BigDecimal("3"));
        oliveOil.setUom(tablespoon);

        // 4 to 6 skinless, boneless chicken thighs (1 1/4 pounds)
        Ingredient chicken = new Ingredient();
        chicken.setDescription("skinless, boneless chicken thighs");
        chicken.setAmount(new BigDecimal("1.25"));
        chicken.setUom(pound);

        // 8 small corn tortillas
        Ingredient tortillas = new Ingredient();
        tortillas.setDescription("small corn tortillas");
        tortillas.setAmount(new BigDecimal("8"));
        tortillas.setUom(piece);

        // 3 cups packed baby arugula (3 ounces)
        Ingredient arugula = new Ingredient();
        arugula.setDescription("packed baby arugula");
        arugula.setAmount(new BigDecimal("3"));
        arugula.setUom(cup);

        // 2 medium ripe avocados, sliced
        Ingredient avocados = new Ingredient();
        avocados.setDescription("medium ripe avocados, sliced");
        avocados.setAmount(new BigDecimal("2"));
        avocados.setUom(piece);

        // 4 radishes, thinly sliced
        Ingredient radishes = new Ingredient();
        radishes.setDescription("thinly sliced radishes");
        radishes.setAmount(new BigDecimal("4"));
        radishes.setUom(piece);

        // 1/2 pint cherry tomatoes, halved
        Ingredient tomatoes = new Ingredient();
        tomatoes.setDescription("cherry tomatoes, halved");
        tomatoes.setAmount(new BigDecimal("0.5"));
        tomatoes.setUom(piece);

        // 1/4 red onion, thinly sliced
        Ingredient onion = new Ingredient();
        onion.setDescription("thinly sliced red onion");
        onion.setAmount(new BigDecimal("0.25"));
        onion.setUom(piece);

        // 1/2 cup sour cream thinned with 1/4 cup milk
        Ingredient sourCream = new Ingredient();
        sourCream.setDescription("sour cream");
        sourCream.setAmount(new BigDecimal("0.5"));
        sourCream.setUom(cup);

        // 1/2 cup sour cream thinned with 1/4 cup milk
        Ingredient milk = new Ingredient();
        milk.setDescription("milk");
        milk.setAmount(new BigDecimal("0.25"));
        milk.setUom(cup);

        // 1 lime, cut into wedges
        Ingredient lime = new Ingredient();
        lime.setDescription("lime");
        lime.setAmount(new BigDecimal("1"));
        lime.setUom(piece);

        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(chiliPowed);
        ingredients.add(oregano);
        ingredients.add(cumin);
        ingredients.add(sugar);
        ingredients.add(salt);
        ingredients.add(garlic);
        ingredients.add(orangeZest);
        ingredients.add(orangeJuice);
        ingredients.add(oliveOil);
        ingredients.add(chicken);
        ingredients.add(tortillas);
        ingredients.add(arugula);
        ingredients.add(avocados);
        ingredients.add(radishes);
        ingredients.add(tomatoes);
        ingredients.add(onion);
        ingredients.add(sourCream);
        ingredients.add(milk);
        ingredients.add(milk);
        ingredients.add(lime);

        tacos.setIngredients(ingredients);

        ingredients.forEach(i -> i.setRecipe(tacos));

        mexican.setRecipes(new HashSet<>(Collections.singletonList(tacos)));

        categoryRepository.save(mexican);
        recipeRepository.save(tacos);
        recipeRepository.save(tacos);

        return tacos;
    }
}

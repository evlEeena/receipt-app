package com.evleeena.receiptapp.repositories;

import com.evleeena.receiptapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}

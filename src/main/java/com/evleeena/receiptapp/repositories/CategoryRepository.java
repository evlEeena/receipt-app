package com.evleeena.receiptapp.repositories;

import com.evleeena.receiptapp.domain.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}

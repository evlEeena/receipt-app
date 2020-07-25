package com.evleeena.receiptapp.repositories;

import com.evleeena.receiptapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Resource
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    void findByUom() {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByUom("Cup");
        assertTrue(uom.isPresent());
        assertEquals("Cup", uom.get().getUom());
    }
}
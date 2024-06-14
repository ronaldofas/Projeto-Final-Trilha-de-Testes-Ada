package com.ada.model.entity.conta;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumeroContaTest {

    @Test
    void testaNumeroDaConta() {
        NumeroConta numeroConta = new NumeroConta();
        assertEquals("000001", numeroConta.getValor());
    }

}
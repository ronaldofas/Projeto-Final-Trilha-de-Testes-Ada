package com.ada.model.entity.cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdentificadorCNPJTest {

    @Test
    void testValidCNPJ() {
        String validCNPJ = "12345678901234";
        IdentificadorCNPJ cnpj = new IdentificadorCNPJ(validCNPJ);
        Assertions.assertEquals(validCNPJ, cnpj.getValor());
    }

    @Test
    void testInvalidCNPJ() {
        String invalidCNPJ = "1234567890123";
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IdentificadorCNPJ(invalidCNPJ)
        );
    }

    @Test
    void testNullCNPJ() {
        String nullCNPJ = null;
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IdentificadorCNPJ(nullCNPJ));
    }
}
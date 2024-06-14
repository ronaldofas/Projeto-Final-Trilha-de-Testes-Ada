package com.ada.model.entity.cliente;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IdentificadorCPFTest {

    @Test
    void testValidaCPF() {
        String cpfValido = "12345678901";
        IdentificadorCPF cpf = new IdentificadorCPF(cpfValido);
        Assertions.assertEquals(cpfValido, cpf.getValor());
    }

    @Test
    void testInvalidCNPJ() {
        String cpfInvalido = "1234567890";
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IdentificadorCNPJ(cpfInvalido)
        );
    }

    @Test
    void testNullCNPJ() {
        String cpfNull = null;
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new IdentificadorCPF(cpfNull));
    }
}
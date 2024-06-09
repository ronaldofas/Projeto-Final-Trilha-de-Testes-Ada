package com.ada.model.helpers.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class DateTimeHelpersTest {

    @Test
    void obterDataNoPadraoBrasileiroTest() {
        LocalDateTime dataAtual = LocalDateTime.now();
        String data = DateTimeHelpers.obterDataNoPadraoBrasileiro(dataAtual);
        String dataEsperada = String.format("%02d", dataAtual.getDayOfMonth())
                + "/" + String.format("%02d", dataAtual.getMonthValue())
                + "/" + String.format("%04d", dataAtual.getYear());

        Assertions.assertEquals(dataEsperada, data);
    }
}

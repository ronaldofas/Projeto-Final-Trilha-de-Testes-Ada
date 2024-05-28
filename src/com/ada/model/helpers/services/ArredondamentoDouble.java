package com.ada.model.helpers.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ArredondamentoDouble {

    private ArredondamentoDouble() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static double arredondar(double valor) {
        BigDecimal valorBigDec = BigDecimal.valueOf(valor);
        BigDecimal valorArredondado = valorBigDec
                .setScale(2, RoundingMode.CEILING);
        return valorArredondado.doubleValue();
    }
}

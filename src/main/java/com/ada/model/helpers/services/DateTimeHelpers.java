package com.ada.model.helpers.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeHelpers {

    private DateTimeHelpers() {
        throw new IllegalStateException("Classe utilitária");
    }

    public static String obterDataNoPadraoBrasileiro(final LocalDateTime data){
        String dataFormatada = "";

        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dataFormatada = formatador.format(data);

        return dataFormatada;
    }
}

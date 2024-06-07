package com.ada.model.helpers.services;

import java.util.Scanner;

public class ScannerHelper {

    private static final Scanner ENTRADA = new Scanner(System.in);

    private ScannerHelper(){}

    public static int obterDigitacaoDeInteiro(){
        return ENTRADA.nextInt();
    }

    public static String obterDigitacaoDeString(){
        return ENTRADA.nextLine();
    }

    public static Double obterDigitacaoDeDouble(){
        return ENTRADA.nextDouble();
    }

    public static void limparBuffer() {
        if (ENTRADA.hasNextLine()) {
            ENTRADA.nextLine();
        }
    }
}

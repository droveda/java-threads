package com.droveda.curso2.servidor;

import java.io.PrintStream;
import java.util.Random;
import java.util.concurrent.Callable;

public class ComandoC2ChamaWS implements Callable<String> {

    private PrintStream saida;

    public ComandoC2ChamaWS(PrintStream saida) {
        this.saida = saida;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Servidor recebeu comando c2-ws");

        saida.println("processando comando c2-ws");
        Thread.sleep(15000);
//        throw new RuntimeException("exception comando c2");

        int numero = new Random().nextInt(100) + 1;
        saida.println("servidor finalizou comando c2-ws!");

        return Integer.toString(numero);
    }

}

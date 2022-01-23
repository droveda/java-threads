package com.droveda.curso2.servidor;

import java.io.PrintStream;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class JustaResultadosFuture implements Callable<Void> {

    private Future<String> futureWs;
    private Future<String> futureBanco;
    private PrintStream saida;

    public JustaResultadosFuture(Future<String> futureWs, Future<String> futureBanco, PrintStream saida) {
        this.futureWs = futureWs;
        this.futureBanco = futureBanco;
        this.saida = saida;
    }

    @Override
    public Void call() {

        System.out.println("Aguardando resultados do futureWs e bancoWs");

        try {
            String resultWs = futureWs.get(30, TimeUnit.SECONDS);
            String resultBanco = futureBanco.get(30, TimeUnit.SECONDS);

            this.saida.println("Resultado comando c2 : " + resultWs + " - " + resultBanco);
        } catch (Exception ex) {
            System.out.println("Cancelando execucao do comando c2 junta resultado!");
            this.saida.println("Erro ao juntar resultados futureWs, bancoWs");
            this.futureWs.cancel(true);
            this.futureBanco.cancel(true);
        }

        System.out.println("Finalizou JustaResultadosFuture");

        return null;
    }
}

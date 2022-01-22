package com.droveda.curso2.servidor;

import java.util.concurrent.ThreadFactory;

public class FabricaDeThreads implements ThreadFactory {

    private static int numero = 1;

    @Override
    public Thread newThread(Runnable r) {

        Thread t = new Thread(r, "Thread_Servidor_Tarefas_" + numero);
        numero++;
        t.setUncaughtExceptionHandler(new TratadorDeExcecao());

        return t;
    }
}

package com.droveda.curso2.servidor;

import java.lang.Thread.UncaughtExceptionHandler;

public class TratadorDeExcecao implements UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Deu erro na thread: " + t.getName() + " erro: " + e.getMessage());
    }
}

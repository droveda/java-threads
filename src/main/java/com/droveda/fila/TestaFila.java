package com.droveda.fila;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestaFila {

    public static void main(String[] args) throws InterruptedException {

        Queue<String> fila = new LinkedList<>();

        //LinkedList nao the Thread Safe

        fila.offer("c1");
        fila.offer("c2");
        fila.offer("c3");

        System.out.println(fila.size());

        System.out.println(fila.poll()); //devolve e tira o elemento
        System.out.println(fila.peek()); //devolve mas nao tira o elemento

        System.out.println(fila.size());

        System.out.println("------------");
        System.out.println("------------");
        System.out.println("------------");

        BlockingQueue<String> filaBloqueante = new ArrayBlockingQueue<>(3);

        //ArrayBlockingQueue eh thread safe

        filaBloqueante.put("c1");
        filaBloqueante.put("c2");
        filaBloqueante.put("c3"); //put, como a fila tem tamanho maximo de 3 elementos caso coloque mais um elemento usando put
        //ele ira bloquear a thread ate que alguem tire um elemento da fila

        System.out.println(filaBloqueante.take()); //bloqueia a thread até que tenha um elemento disponível para retornar

    }

}

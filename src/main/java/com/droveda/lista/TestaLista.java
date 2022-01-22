package com.droveda.lista;

public class TestaLista {

    public static void main(String[] args) {

        Lista lista = new Lista();

        for (int i = 0; i < 100; i++) {
            int threadNumber = i;
            new Thread(() -> {

                for (int j = 0; j < 10; j++) {
                    lista.adicionaElemento("Thread " + threadNumber + " - " + j);
                }

            }).start();
        }

        new Thread(new TarefaImprimir(lista)).start();

    }

}

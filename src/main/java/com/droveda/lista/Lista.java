package com.droveda.lista;

public class Lista {

    private String[] elementos = new String[1000];
    private int indice = 0;

    public synchronized void adicionaElemento(String elemento) {
        this.elementos[indice] = elemento;
        this.indice++;

//        try {
//            Thread.sleep(5);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if (indice == elementos.length) {
            System.out.println("Lista está cheia, notificando!");
            this.notify();
        }
    }

    public int tamanho() {
        return this.elementos.length;
    }

    public String pegaElemento(int index) {
        return this.elementos[index];
    }

    public boolean estaCheia() {
        return indice == elementos.length;
    }
}

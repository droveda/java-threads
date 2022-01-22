package com.droveda.curso2.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

    private ServerSocket servidor;
    private ExecutorService threadPool;
//    private volatile boolean estaRodando;
    private AtomicBoolean estaRodando;

    public ServidorTarefas() throws Exception {
        System.out.println("---Iniciando servidor---");
        servidor = new ServerSocket(12345);
        threadPool = Executors.newFixedThreadPool(4, new FabricaDeThreads());
//        threadPool = Executors.newCachedThreadPool();
        estaRodando = new AtomicBoolean(true);
    }


    public static void main(String[] args) throws Exception {
        ServidorTarefas servidor = new ServidorTarefas();
        servidor.rodar();
        servidor.parar();

    }

    private void rodar() throws Exception {
        while (estaRodando.get()) {
            try {
                Socket socket = servidor.accept();
                System.out.println("Aceitando novo cliente na porta " + socket.getPort());

                DistribuirTarefas distribuirTarefas = new DistribuirTarefas(threadPool, socket, this);
                threadPool.execute(distribuirTarefas);
            } catch (SocketException ex) {
                System.out.println("SocketException, Está rodando? " + estaRodando);
            }
        }
    }

    public void parar() throws Exception {
        estaRodando.set(false);
        servidor.close();
        threadPool.shutdown();
    }

}

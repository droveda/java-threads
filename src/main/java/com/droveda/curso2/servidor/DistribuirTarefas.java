package com.droveda.curso2.servidor;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuirTarefas implements Runnable {

    private Socket socket;
    private ServidorTarefas servidor;
    private ExecutorService threadPool;
    private BlockingQueue<String> filaComandos;

    public DistribuirTarefas(ExecutorService threadPool, Socket socket, ServidorTarefas servidor, BlockingQueue<String> filaComandos) {
        this.socket = socket;
        this.servidor = servidor;
        this.threadPool = threadPool;
        this.filaComandos = filaComandos;
    }

    @Override
    public void run() {

        try {
            System.out.println("Distribuindo tarefas para " + socket);
            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()) {
                String comando = entradaCliente.nextLine();
                System.out.println("Comando recebido " + comando);

                switch (comando) {
                    case "c1": {
                        saidaCliente.println("Confirmação comando c1");
                        ComandoC1 c1 = new ComandoC1(saidaCliente);
                        threadPool.execute(c1);
                        break;
                    }
                    case "c2": {
                        saidaCliente.println("Confirmação comando c2");
                        ComandoC2ChamaWS c2Ws = new ComandoC2ChamaWS(saidaCliente);
                        ComandoC2AcessaBanco c2Banco = new ComandoC2AcessaBanco(saidaCliente);
                        Future<String> futureWs = threadPool.submit(c2Ws);
                        Future<String> futureBanco = threadPool.submit(c2Banco);

                        this.threadPool.submit(new JustaResultadosFuture(futureWs, futureBanco, saidaCliente));

                        break;
                    }
                    case "c3": {
                        this.filaComandos.put(comando); //bloqueia
                        saidaCliente.println("Comando c3 adicionado na fila");

                        break;
                    }
                    case "fim": {
                        saidaCliente.println("Desligando o servidor");
                        servidor.parar();
                        break;
                    }
                    default: {
                        saidaCliente.println("Comando não encontrado!");
                    }
                }

                //System.out.println(comando);
            }

            saidaCliente.close();
            entradaCliente.close();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }


    }
}

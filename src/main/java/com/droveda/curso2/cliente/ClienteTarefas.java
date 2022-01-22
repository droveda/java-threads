package com.droveda.curso2.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 12345);

        System.out.println("Conexao estabelecida");

        Thread threadEnviaComando = new Thread(() -> {
            try {
                System.out.println("Pode enviar comandos!");
                PrintStream saida = new PrintStream(socket.getOutputStream());
                Scanner teclado = new Scanner(System.in);

                while (teclado.hasNextLine()) {
                    String linha = teclado.nextLine();

                    if (linha.trim().equals("")) {
                        break;
                    }
                    saida.println(linha);
                }

                saida.close();
                teclado.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        Thread threadRecebeResposta = new Thread(() -> {
            try {
                System.out.println("Recebendo dados do servidor!");
                Scanner respostaServidor = new Scanner(socket.getInputStream());
                while (respostaServidor.hasNextLine()) {
                    String linha = respostaServidor.nextLine();
                    System.out.println(linha);
                }
                respostaServidor.close();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        threadEnviaComando.start();
        threadRecebeResposta.start();

        threadEnviaComando.join(); //a thread main se junta a threadEnviaComando. Thread main só continua quando a ThreadEnviaComando terminar
        System.out.println("Fechando socket!!!");
        socket.close();
    }

}

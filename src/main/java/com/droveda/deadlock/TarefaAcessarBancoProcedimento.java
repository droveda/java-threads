package com.droveda.deadlock;

public class TarefaAcessarBancoProcedimento implements Runnable {

    private final PoolDeConexao pool;
    private final GerenciadorDeTransacao tx;

    public TarefaAcessarBancoProcedimento(PoolDeConexao pool, GerenciadorDeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

    @Override
    public void run() {

        synchronized (tx) {
            System.out.println("comencando a gerenciar a transacao");
            tx.begin();

            synchronized (pool) {
                System.out.println("peguei a chave do pool");
                pool.getConnection();
            }

        }

    }

}

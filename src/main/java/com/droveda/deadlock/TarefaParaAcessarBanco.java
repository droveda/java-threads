package com.droveda.deadlock;

public class TarefaParaAcessarBanco implements Runnable {

    private final PoolDeConexao pool;
    private final GerenciadorDeTransacao tx;

    public TarefaParaAcessarBanco(PoolDeConexao pool, GerenciadorDeTransacao tx) {
        this.pool = pool;
        this.tx = tx;
    }

    @Override
    public void run() {

        synchronized (pool) {
            System.out.println("peguei a chave do pool");
            pool.getConnection();

            synchronized (tx) {
                System.out.println("comencando a gerenciar a transacao");
                tx.begin();
            }

        }

    }

}

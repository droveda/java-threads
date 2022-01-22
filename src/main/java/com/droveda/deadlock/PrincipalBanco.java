package com.droveda.deadlock;

public class PrincipalBanco {

    public static void main(String[] args) {

        GerenciadorDeTransacao tx = new GerenciadorDeTransacao();
        PoolDeConexao pool = new PoolDeConexao();

        new Thread(new TarefaParaAcessarBanco(pool, tx)).start();
        new Thread(new TarefaAcessarBancoProcedimento(pool, tx)).start();

    }

}

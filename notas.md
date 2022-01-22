#Threads with Java

## Ferramenta jconsole
Permite analizar processos da JVM como memória, threads, Classes, Resumo da VM

## Anotações
* synchronized -> Todo o bloco synchronized será executado de uma vez só, de maneira atômica (Cuja execução não pode ser interrompida na metade.) 
Apenas 1 thread por vez consegue acessar um bloco synchronized. 
Para utilizar synchronized é necessário informar qual será a chave que irá tornar o acesso único. A thread em posse da chave pode executar o bloco
sincronizado.
* We can use the synchronized keyword on different levels:
  * Instance Methods
  * Static Methods
  * Code blocks
* ArrayList nao eh sincronizado por padrao
  * List<String> list = Collections.synchronizedList(new ArrayList<>()); -> cria uma lista sincronizada
  * Vector é sincronizado 
* Daemon Thread
  * São provedores de serviços para outras threads.
  * Threads daemon são como prestadores de serviços para outras threads. 
  Elas são usadas para dar apoio à tarefas e só são necessárias rodar quando as threads "normais" ainda estão sendo executadas. 
  Uma thread daemon não impede a JVM de terminar desde que não existem mais threads principais em execução. 
  Um exemplo de uma thread daemon é o coletor de lixo da JVM (Garbage Collector) ou a nossa limpeza do banheiro :)
  * Para definir uma thread como daemon basta usar o método setDaemon(boolean) antes de inicializar


## Estados de uma Thread
* NEW
  * Uma thread foi criada mas ainda não foi iniciada.
* RUNNABLE
  * A thread está rodando dentro da JVM.
* BLOCKED
  * A thread foi bloqueada pois não conseguiu pegar a chave.
* WAITING
  * A thread está esperando pois foi chamado this.wait().
* TIMED_WAITING
  * A thread está esperando pois foi chamado this.wait(milis)
* TERMINATED
  * A thread está finalizada.

## Wait e notify/notifyall
* wait() faz com que uma thread A espere a execução da thread B
* wait() só pode ser chamado dentro de um método ou bloco synchronized
* notify/notifyall notifica as threads que estavam no estado WAITING, elas voltam a ser executadas

## Deadlock
* Qual das condições abaixo são necessárias para ocorrer um deadlock
  * Uma thread segura um recurso e aguarda por outro.

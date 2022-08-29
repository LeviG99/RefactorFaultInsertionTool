# RefactorFaultInsertionTool

Plug-ins para serem utilizados para simulação de refatoramentos com faltas em um sistema, por meio de inserção de mutantes.

-MyPlugin: Plug-in utilizado para o refatoramento do código de tipo extract method, recebendo como entrada o inicio do intervalo de extração, e após isso, o fim do intervalo.

-MyPluginFault: Plug-in que realiza a inserção da falta, podendo ser uma remoção do ultimo statement de um método ou a alteração de um statement de um método.

# Como utilizar

- 1.Selecionar o método, e os statements a serem refatorados de um código
- 2.Executar o projeto MyPlugin como plug-in eclipse(Eclipse Application)
- 3.Selecionar na nova janela o arquivo Example.java
- 4.Transpor a classe do método a ser refatorado para a tela de execução do plug-in
- 5.Executar o plugin de refatoramento, provendo o nome do método a ser refatorado, e o intervalo de statements que devem ser refatorados
- 6.Executar o plugin de inserção de faltas, provendo o nome do método a ser alterado, a string que representa o código a ser alterado, caso necessário, e o tipo de falta a ser inserida.

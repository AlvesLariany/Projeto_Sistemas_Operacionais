# acadConecta

Como rodar a aplicação acadConecta

### Pré-requisitos:
- Possuir o docker instalado; <br>
- Possuir o JDK versão 17+;<br>
- Possuir o sistema de gerenciamento Git;<br>
- Certifique-se de que a docker-engine está em execução.<br>

### Primeiro passo:
clone o repositório no GitHub com o seguinte comando:

	git clone https://github.com/AlvesLariany/Projeto_Sistemas_Operacionais.git

### Segundo passo: 
navegue até o repositório principal, “acadConecta”, e nele, execute o seguinte comando:
	
	docker-compose up -d

### Terceiro passo:
Quando o segundo passo for concluído, execute:

	docker container ps

Verifique se o container “acadConecta” foi listado, isso confirma que ele está em execução 

### Quarto passo:
Navegue até a classe “App” do projeto. Considerando que você está na pasta “acadConecta”, digite o seguinte comando:

	cd src/main/java/org/code/application

### Quinto passo:
Uma vez que você estiver na classe “App” e o container estiver rodando, basta executar a classe App.



Em caso de problemas, entre em contato com: pires.soares@academico.ifpb.edu.br

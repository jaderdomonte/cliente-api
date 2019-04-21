[![Build Status](https://travis-ci.org/jaderdomonte/cliente-api.svg?branch=master)](https://travis-ci.org/jaderdomonte/cliente-api)
# cliente-api

API de Clientes para o processo seletivo do UOL.

Esta API dispõe dos 5 serviços a seguir:
1. Listar todos os Clientes.
2. Salvar Cliente.
3. Atualizar Cliente.
4. Apagar Cliente.
5. Retornar Cliente por ID.

Ao executar o projeto, a documentação completa dos serviços estará disponível em http://ip_do_seu_servidor:8080/cliente-api/swagger-ui.html. Onde ip_do_seu_servidor é o ip da máquina onde o serviço foi startado.

Abaixo seguem as ferramentas utilizadas no projeto:
1. Apache Maven 3.6.0: utilizada para gerenciar as dependências do projeto e automatizar o processo de build. Escolhida por tratar-se de uma ótima ferramenta, familiaridade e fácil uso.
2. Project Lombok 1.16.22: utilizada para diminuir a verbosidade das classes java criando arquivos mais claros e sucintos. Escolhida pela familiaridade com a ferramenta e facilidade de uso.
3. Spring Boot 1.5.19: utilizada para facilitar o processo de configuração e publicação da aplicação. Trata-se de um dos requisitos do projeto proposto pelo processo seletivo.
4. Spring Data JPA 1.11.18: utilizada para facilitar e aumentar a produtividade ao criar a camada de DAO ou Repository. Escolhida por ser uma excelente ferramenta que diminui a verbosidade, provê diversas consultas e convenções ao acesso aos dados e fazer parte do ecossistema Spring.
5. Spring MVC 1.5.19: utilizada para configurar o projeto como web. Escolhida pela facilidade de uso e por fazer parte do ecossistema Spring.
6. Spring Boot Dev Tools 1.5.19: utilizada para facilitar o processo de desenvolvimento. Escolhida pela falicidade de uso e por fazer parte do ecossistema Spring.
7. H2 1.4.197: utilizada para construir um banco de dados em memória. Escolhida pela familiaridade e facilidade de uso.
8. Spring Boot Starter Test 1.5.19: utilizada para gerenciar as dependências e configuração dos testes unitários. Escolhida pela facilidade de uso e por fazer parte do ecossistema Spring.
9. Hibernate 5.0.12: utilizada para implementar o mapeamento objeto relacional. Escolhida por ser uma excelente ferramenta, familiaridade e fácil uso.
10. Swagger2 2.7.0: utilizada para documentar os serviços dos endpoints. Escolhida pela familiaridade e facilidade de uso.

Para executar o projeto as seguintes ferramentas devem estar instaladas:
1. JDK 1.8.
2. Apache Maven.
3. Project Lombok.

Seguem instruções para empacotar, executar e testar o projeto:
1. Após baixar o fonte, entrar na pasta raiz do projeto e executar o comando: mvn clean package 
2. Ainda dentro da pasta raiz do projeto, entrar na pasta target/ e executar o comando: java -jar cliente-api.jar
3. Os serviços e sua documentação completa estarão disponíveis no endereço http://localhost:8080/cliente-api/swagger-ui.html
4. Caso queira consumir a API por outra aplicação ou acessar os serviços por um app como o Postman, as URL's estão a seguir:
	4.1. Listar todos os Clientes (Método GET): http://localhost:8080/cliente-api/v1/clientes
	4.2. Salvar Cliente (Método POST): http://localhost:8080/cliente-api/v1/clientes
	4.3. Atualizar Cliente (Método PUT): http://localhost:8080/cliente-api/v1/clientes
	4.4. Apagar Cliente (Método DELETE): http://localhost:8080/cliente-api/v1/clientes/{id}
	4.5. Retornar Cliente por ID (Método GET): http://localhost:8080/cliente-api/v1/clientes/{id}

Segue o processo para por a API em produção:
1. Copiar o arquivo cliente-api.jar para o servidor de produção.
2. Entrar na pasta onde o arquivo cliente-api.jar está e executar o comando: java -jar cliente-api.jar
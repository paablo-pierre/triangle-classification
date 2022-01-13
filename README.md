<h4 align="center">  
  🚀 Challenger Triangle Classification API - Cocus.pt
<br>

## 💻 Project

Este desafio é um crud para um agendamento de exames pelo paciente.
This challenge is a crud to determine if a triangle is equilateral, isosceles or scalene.

## Technologies:
- Java 11;
- Spring boot 2.5.2;
- MySQL 5.6.26;
- h2 Database;
- Spock;
- Lombok;
- Spring Security.

## :wrench:    Initial Configuration
Configuração no arquivo docker-compose.yml <p>
Acesse o diretório infra e execute o arquivo com o comando docker-compose up -d

## :hammer: Instalação
1 - You need to install maven on your desktop

Root project execute:
```  
mvn clean test
mvn package -DskipTests  
```  

Na raiz do projeto faça o build da imagem docker da aplicação:
```
docker build -t sample-schedule .
```
Com a imagem acima gerada é possível subir o docker-compose com uma instância do banco de dados PostgreSQL <p>
Na raiz do projeto execute o seguinte comando:
```
cd infra
docker-compose up
```

A aplicação será executada na porta 8090.

Ao executar a aplicação será executado um script para popular a base de dados através do flyway.

## Como executar requests

A aplicação possui dois endpoints, um para listar os agendamentos já feitos e outro para criar um agendamento:
````
É possível utilizar a collection no postman contida em src/config e importá-la no postman.
````
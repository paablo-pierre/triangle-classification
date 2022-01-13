<h4 align="center">  
  🚀 Challenger Triangle Classification API - Cocus.pt
<br>

## 💻 Project
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
Configure docker-compose.yml file in /infra <p>
Access the path /infra and execute the Dockerfile with docker-compose up -d

## :hammer: Instalação
1 - You need to install maven on your desktop

Root project execute:
```  
mvn clean test
mvn package  
```

After to do the last step, build the docker image:
```
docker build -t triangle-classification .
```
How to execute with database on AWS:
```
After to build the application execute: docker run --env ENV=aws
```
And the application will start using AWS database.

With this image generated it's possible to start the application with MySQL database instance together <p>
On root project execute:
```
cd infra
docker-compose up
```

The application will start on port 8090.

If you want to run without docker do only this:
```
mvn spring-boot:run -Dspring-boot.run.profiles=aws 
```

If you want to see the endpoints descriptions only do this after starts application:
```
localhost:8090/swagger-ui.html
```

## How execute request

The application has two endpoints one to list all triangles saved on database, and the second to save them. 
````
It's possible to use the postman collection on src/config and import on postman.
````
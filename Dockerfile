FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/triangle-classification-0.0.1-SNAPSHOT.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=${ENV}", "app.jar"]
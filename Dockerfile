FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/cliente-api-0.0.1-SNAPSHOT.jar cliente-api.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/cliente-api.jar"]
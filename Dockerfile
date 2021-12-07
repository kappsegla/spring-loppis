FROM openjdk:17
COPY ./target/spring-loppis-0.0.1-SNAPSHOT.jar /usr/src/loppis/
WORKDIR /usr/src/loppis/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-loppis-0.0.1-SNAPSHOT.jar"]

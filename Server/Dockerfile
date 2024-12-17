FROM maven:3.9.8 AS builder
COPY pom.xml pom.xml
COPY src src
RUN mvn clean package -DskipTests

FROM openjdk:17
LABEL authors="Thulani"
COPY --from=builder target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
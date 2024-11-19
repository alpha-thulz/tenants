FROM openjdk:17
LABEL authors="Thulani"
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
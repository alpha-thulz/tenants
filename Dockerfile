FROM openjdk:17
LABEL authors="Thulani"
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
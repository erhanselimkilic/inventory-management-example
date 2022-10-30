FROM openjdk:17-jdk-alpine
COPY build/libs/inventory-service-0.0.1-SNAPSHOT.jar inventory-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","inventory-service.jar"]
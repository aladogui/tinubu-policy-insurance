FROM maven:3.9.7-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:21-slim
WORKDIR /app
COPY --from=build /app/target/*.jar policy-insurance-service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/policy-insurance-service.jar"]
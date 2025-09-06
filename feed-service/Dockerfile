FROM maven:3.9.9-eclipse-temurin-21 AS builder
LABEL authors="tuna"

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=builder /app/target/feed-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8300

ENTRYPOINT ["java", "-jar", "app.jar"]

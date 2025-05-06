# === Stage 1: Build the app ===
FROM maven:3.9.4-eclipse-temurin-17 AS builder

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests -Dspring.main.web-application-type=none

# === Stage 2: Run the app ===
FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy built jar from stage 1
COPY --from=builder /build/target/hms-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
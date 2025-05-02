# Step 1: Build
FROM openjdk:21 AS builder
LABEL authors="Inocencio Cordeiro Armando"

WORKDIR /app

COPY pom.xml settings.xml ./

RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package -DskipTests

# Step 2: Execution
FROM openjdk:21-jre-slim
WORKDIR /app
COPY --from=builder /app/target/user_service.jar ./user_service.jar
EXPOSE 8080
CMD ["java", "-jar", "user_service.jar"]

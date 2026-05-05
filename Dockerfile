Stage 1: Build the JAR
FROM eclipse-temurin:21 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN ./mvnw package -DskipTests || true
COPY mvnw .
COPY .mvn .mvn
RUN ./mvnw package -DskipTests

Stage 2: Run the JAR
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

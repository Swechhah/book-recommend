# Use a stable Java 25 JDK
FROM eclipse-temurin:25-jdk-alpine

# Author label
LABEL authors="swechhahumagain"

# Set working directory
WORKDIR /app

# Copy the built JAR file (assumes you ran ./mvnw package or gradle build)
# Replace <your-backend-jar>.jar with the actual JAR name
COPY target/*.jar app.jar

# Expose Spring Boot default port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]

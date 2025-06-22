# Use lightweight JDK image
FROM openjdk:17-jdk-slim

# Set working dir inside container
WORKDIR /app

# Copy the jar file built using mvn package
COPY target/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

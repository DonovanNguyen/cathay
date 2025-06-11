FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy build artifacts
COPY build/libs/*.jar app.jar

# Expose the port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
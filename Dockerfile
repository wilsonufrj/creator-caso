# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:19-jre-jammy

# Set the working directory in the container
WORKDIR /app

# Copy the fat JAR file generated by Spring Boot
# Assumes the JAR is in the target directory and named following Spring Boot conventions
COPY target/caso-creator-1.0.0.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]

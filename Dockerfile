# Use an official Java 21 runtime as a parent image
FROM openjdk:21-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build file
COPY pom.xml .

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/restservice-0.0.1-SNAPSHOT.jar"]

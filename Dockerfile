# Use the official Java 20 image as the base image
FROM eclipse-temurin:20-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper files to the container
COPY gradle/ gradle/

# Copy the Gradle build files
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Copy the application source code to the container
COPY src/ src/

# Make the Gradle wrapper executable
RUN chmod +x ./gradlew

# Build the application inside the container
RUN ./gradlew build

# Copy the built JAR file to the /app/libs directory
RUN mkdir -p libs && cp build/libs/receipt-processor-challenge-0.0.1-SNAPSHOT.jar libs/

# Expose the port that the Spring Boot app runs on
EXPOSE 8080

# Run the application when the container starts
CMD ["java", "-jar", "libs/receipt-processor-challenge-0.0.1-SNAPSHOT.jar"]
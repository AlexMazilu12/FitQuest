# Use the official Eclipse Temurin image as a base image
FROM eclipse-temurin:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/FitQuest-0.0.1-SNAPSHOT.jar /app/fitquest.jar

# Expose the port that the application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/fitquest.jar"]
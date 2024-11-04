FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY build/libs/FitQuest-0.0.1-SNAPSHOT.jar /app/fitquest.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "fitquest.jar"]
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/FitQuest-0.0.1-SNAPSHOT.jar /app/fitquest.jar

ENTRYPOINT ["java", "-jar", "fitquest.jar"]
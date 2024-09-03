FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY build/libs/money-transfer-service.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
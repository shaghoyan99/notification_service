FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

# Fallback էնվայրոնմենթներ standalone աշխատեցնելու համար (Docker Compose-ը սա override է անում)
ENV SPRING_DATASOURCE_URL="jdbc:postgresql://host.docker.internal:5433/postgres?currentSchema=notification_service"
ENV SPRING_LIQUIBASE_URL="jdbc:postgresql://host.docker.internal:5433/postgres?currentSchema=notification_service"
ENV DB_USERNAME="postgres"
ENV DB_PASSWORD="postgres"

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "app.jar"]
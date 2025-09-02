FROM maven:3.8.5-openjdk-17 as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-oracle
COPY --from=builder /app/target/*.jar /soccer-players-stats.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/soccer-players-stats.jar"]
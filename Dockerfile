FROM maven:3.8.5-openjdk-17 as builder
FROM openjdk:17-oracle
COPY --from=builder /target/*.jar /soccer-players-stats.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/soccer-players-stats.jar"]

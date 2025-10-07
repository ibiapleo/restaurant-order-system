# Etapa de build
FROM gradle:8.7-jdk21 AS build
WORKDIR /app
COPY . .
RUN ./gradlew clean build -x test

# Etapa de runtime (só o JAR final)
FROM openjdk:21
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]

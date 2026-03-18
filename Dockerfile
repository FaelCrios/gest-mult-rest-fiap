# Etapa 1: Build
FROM maven:3.9-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Execução
FROM amazoncorretto:21-alpine
WORKDIR /app
# O asterisco garante que pegamos o JAR independente do nome (demo ou gestmultirest)
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080

# O ENTRYPOINT deve ser EXATAMENTE assim para rodar o Spring
ENTRYPOINT ["java", "-jar", "app.jar"]
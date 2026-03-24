# syntax=docker/dockerfile:1.6

# -------- Build stage --------
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# 1. Copiar pom.xml (cache de dependencias)
COPY pom.xml .

# 2. Descargar dependencias (cacheado)
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q -Dmaven.repo.local=/root/.m2 dependency:go-offline

# 3. Copiar código fuente
COPY src ./src

# 4. Compilar
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -q -Dmaven.repo.local=/root/.m2 package -DskipTests


# -------- Runtime stage --------
FROM eclipse-temurin:21-jre-jammy

LABEL maintainer="Ksantiago"

ENV TZ=America/Lima \
    JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -Dreactor.netty.ioWorkerCount=4"

WORKDIR /app

# Copiar el jar
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8082

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
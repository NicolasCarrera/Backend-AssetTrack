# Stage 1: Build all services
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app

# Primero copia SOLO los archivos necesarios para resolver dependencias
COPY pom.xml .
COPY discovery-service/pom.xml discovery-service/
COPY gateway-service/pom.xml gateway-service/
COPY customer-assets-service/pom.xml customer-assets-service/
COPY customer-branches-service/pom.xml customer-branches-service/
COPY maintenance-reporting-service/pom.xml maintenance-reporting-service/
COPY user-role-management-service/pom.xml user-role-management-service/
COPY work-order-service/pom.xml work-order-service/

# Descarga dependencias (ahora puede validar la estructura de módulos)
RUN mvn dependency:go-offline -B

# Ahora copia todo el código fuente
COPY . .

# Construye todos los módulos
RUN mvn clean package -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copia todos los JARs construidos
COPY --from=builder /app/discovery-service/target/*.jar ./discovery-service.jar
COPY --from=builder /app/gateway-service/target/*.jar ./gateway-service.jar
COPY --from=builder /app/customer-assets-service/target/*.jar ./customer-assets-service.jar
COPY --from=builder /app/customer-branches-service/target/*.jar ./customer-branches-service.jar
COPY --from=builder /app/maintenance-reporting-service/target/*.jar ./maintenance-reporting-service.jar
COPY --from=builder /app/user-role-management-service/target/*.jar ./user-role-management-service.jar
COPY --from=builder /app/work-order-service/target/*.jar ./work-order-service.jar

# Script de inicio mejorado
RUN echo $'#!/bin/sh\n\
  echo "Starting Discovery Service on port 8761..." && \
  java -jar discovery-service.jar --server.port=8761 > discovery.log 2>&1 & \n\
  sleep 15 && \n\
  echo "Starting Gateway Service on port 8080..." && \
  java -jar gateway-service.jar --server.port=8080 > gateway.log 2>&1 & \n\
  echo "Starting Customer Assets Service..." && \
  java -jar customer-assets-service.jar > assets.log 2>&1 & \n\
  echo "Starting Customer Branches Service..." && \
  java -jar customer-branches-service.jar > branches.log 2>&1 & \n\
  echo "Starting Maintenance Reporting Service..." && \
  java -jar maintenance-reporting-service.jar > maintenance.log 2>&1 & \n\
  echo "Starting User Role Management Service..." && \
  java -jar user-role-management-service.jar > user-role.log 2>&1 & \n\
  echo "Starting Work Order Service..." && \
  java -jar work-order-service.jar > work-order.log 2>&1 & \n\
  while true; do nc -l -p 8761 -e echo "Eureka"; nc -l -p 8080 -e echo "Gateway"; done' > entrypoint.sh && \
  chmod +x entrypoint.sh

EXPOSE 8761 8080

HEALTHCHECK --interval=30s --timeout=3s \
  CMD wget --quiet --tries=1 --spider http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["./entrypoint.sh"]
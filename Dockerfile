# Stage 1: Build all services
FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder

WORKDIR /app
COPY pom.xml .
# Cache Maven dependencies
RUN mvn dependency:go-offline -B

# Copy all source code
COPY . .

# Build all modules and skip tests
RUN mvn clean package -DskipTests

# Stage 2: Runtime image
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy all built JARs
COPY --from=builder /app/discovery-service/target/*.jar ./discovery-service.jar
COPY --from=builder /app/gateway-service/target/*.jar ./gateway-service.jar
COPY --from=builder /app/customer-assets-service/target/*.jar ./customer-assets-service.jar
COPY --from=builder /app/customer-branches-service/target/*.jar ./customer-branches-service.jar
COPY --from=builder /app/maintenance-reporting-service/target/*.jar ./maintenance-reporting-service.jar
COPY --from=builder /app/user-role-management-service/target/*.jar ./user-role-management-service.jar
COPY --from=builder /app/work-order-service/target/*.jar ./work-order-service.jar

# Script de inicio mejorado
RUN echo $'#!/bin/sh\n\
  echo "Starting Discovery Service..." && \
  java -jar discovery-service.jar > discovery.log 2>&1 & \
  sleep 15 && \n\
  echo "Starting Gateway Service..." && \
  java -jar gateway-service.jar > gateway.log 2>&1 & \n\
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
  tail -f /dev/null' > entrypoint.sh && \
  chmod +x entrypoint.sh

# Exponer puertos necesarios
EXPOSE 8761 8080

ENTRYPOINT ["./entrypoint.sh"]
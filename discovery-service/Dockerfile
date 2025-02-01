FROM eclipse-temurin:21.0.4_7-jre-noble
EXPOSE 8761
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
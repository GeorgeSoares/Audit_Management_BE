FROM openjdk:17-jdk

WORKDIR /app

COPY target/*.jar app.jar
COPY env.auditmgmt env.auditmgmt

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

# Stage 1: Download and install JDK 21
FROM eclipse-temurin:21 AS builder

WORKDIR /opt/jdk

RUN wget https://download.java.net/java/openjdk/temurin/21/hotspot/linux/x64/jdk-11.0.16+11-hotspot-linux-x64.tar.gz && \
    tar -xzf jdk-11.0.16+11-hotspot-linux-x64.tar.gz && \
    update-alternatives --install /usr/bin/java java /opt/jdk/jdk-11.0.16+11/bin/java 100

# Stage 2: Build and run your Spring Boot application
FROM openjdk:17-jre-hotspot

# ... rest of your Dockerfile instructions (copy application code, set environment variables, etc.)

# Important: Update the JAVA_HOME path
ENV JAVA_HOME=/opt/jdk/jdk-11.0.16+11

ENTRYPOINT ["java", "-jar", "application.jar"]

#FROM openjdk:17-jdk-alpine
FROM ubuntu:22.04

RUN apt update
RUN apt-get install -y openjdk-17-jdk

RUN apt-get update && apt-get install -y fontconfig libfreetype6 && rm -rf /var/lib/apt/lists/*
#WORKDIR /app
##ADD ./target/*.jar pharm.jar
COPY target/Pharm-API-0.0.1-SNAPSHOT.jar pharm.jar
EXPOSE 8080

ENTRYPOINT ["java","-jar","pharm.jar"]
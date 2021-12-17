FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} VisitCard.jar

ENTRYPOINT ["java","-jar","/VisitCard.jar"]
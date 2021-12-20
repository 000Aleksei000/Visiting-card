FROM openjdk:17

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} VisitCard.jar

ENTRYPOINT ["java","-jar","/VisitCard.jar"]
FROM openjdk:13-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} authserver.jar
EXPOSE 9111
ENTRYPOINT ["java","-jar","/authserver.jar"]
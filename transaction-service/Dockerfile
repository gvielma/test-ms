FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD target/${JAR_FILE} app.jar
EXPOSE 80
ENV SPRING_PROFILES_ACTIVE=docker
ENTRYPOINT [ "sh", "-c", "java -jar /app.jar" ]
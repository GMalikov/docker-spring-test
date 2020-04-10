FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT exec java -jar /app.jar -DconfDir=/etc/config
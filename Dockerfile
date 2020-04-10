FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
RUN mkdir logs && touch /logs/spring-boot-logger.log
RUN ln -sf /dev/stdout /logs/spring-boot-logger.log
ENTRYPOINT exec java -jar /app.jar -DconfDir=/etc/config
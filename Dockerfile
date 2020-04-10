FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
RUN mkdir conf
COPY conf/* ./conf/
ENTRYPOINT ["java","-jar","/app.jar"]
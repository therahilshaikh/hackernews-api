FROM openjdk:17-jdk-alpine
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY target/*.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]

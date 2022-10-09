FROM amazoncorretto:11.0.15

ARG WAR_FILE=target/poc-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${WAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 8080

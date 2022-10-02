FROM amazoncorretto:11.0.15

ARG WAR_FILE=target/BackEnd-1.0-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${WAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]

EXPOSE 80

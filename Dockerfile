FROM amazoncorretto:17.0.4
#FROM amazoncorretto:11.0.15

ARG WAR_FILE=target/poc*.jar

ARG PROFILE

ARG ARGS

ENV PROFILE=${PROFILE}

ENV ARGS=${ARGS}

WORKDIR /opt/app

COPY ${WAR_FILE} app.jar

#ENTRYPOINT ["java", "-Dspring.profiles.active=dev", "-jar", "/app.jar"]

SHELL ["/bin/sh", "-c"]

EXPOSE 5005

EXPOSE 8080

CMD java ${ARGS} -jar app.jar --spring.profiles.active=${PROFILE}
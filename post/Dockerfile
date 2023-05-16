FROM gradle:7.6.1-jdk17-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM amazoncorretto:17-alpine
RUN mkdir /app
ARG JAR_FILE=/home/gradle/src/build/libs/*-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} /app/post-service.jar
#EXPOSE 18081
#RUN apt-get update && apt-get install -y curl
HEALTHCHECK --interval=40s --retries=4 --timeout=8s --start-period=30s CMD curl --fail http://localhost:18081 || exit 1
ENTRYPOINT ["java","-jar", "/app/post-service.jar"]
FROM gradle:7.3-jdk11-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:11-jre-slim
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/*.jar ./server.jar
CMD ["java", "-jar", "server.jar"]
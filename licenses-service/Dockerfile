# Базовый образ, содержащий среду Java времени выполнения
FROM openjdk:11 as build
# add information about owner
LABEL maintainer = victor.victzeran@mail.ru
WORKDIR application
# File jar application
ARG JAR_FILE=target/*.jar
# add jar file application in conteiner
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM openjdk:11
WORKDIR application
COPY --from=build application/dependencies/ ./
COPY --from=build application/spring-boot-loader/ ./
COPY --from=build application/snapshot-dependencies/ ./
COPY --from=build application/application/ ./
# java -jar /app/spring-boot-docker.jar
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
# Make port 8090 available to the world outside this container
EXPOSE 8088
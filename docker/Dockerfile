FROM openjdk:8-jre

RUN apt-get update && apt-get install -y mysql-client
RUN apt-get clean

COPY jdbbackup.jar jdbbackup.jar

ENTRYPOINT ["java","-jar","jdbbackup.jar"]
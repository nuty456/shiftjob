FROM adoptopenjdk/openjdk11:jdk-11.0.10_9-debian
WORKDIR /app
ARG JAR_FILE=target/shiftjob-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]


#COPY pasy-gui.jar /app
#USER root
#COPY entrypoint.sh /app
#RUN chmod +x /app/entrypoint.sh
#USER default
#WORKDIR /app
#CMD ["./entrypoint.sh"]
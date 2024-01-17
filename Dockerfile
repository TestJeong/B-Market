FROM adoptopenjdk/openjdk11
WORKDIR /usr/src/app
EXPOSE 8080
ARG JAR_PATH=./build/libs
COPY ${JAR_PATH}/bmarket-0.0.1-SNAPSHOT.jar ${JAR_PATH}/bmarket-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","./build/libs/bmarket-0.0.1-SNAPSHOT.jar"]

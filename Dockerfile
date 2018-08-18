FROM maven:3.5.2-jdk-8-alpine AS MAVEN_TOOL_CHAIN

COPY pom.xml pom.xml
COPY /src /src
COPY src/main/resources/quiz.txt quiz.txt

RUN mvn clean install

ENTRYPOINT java -jar target/ask-me-java-8-1.0-SNAPSHOT.jar quiz.txt

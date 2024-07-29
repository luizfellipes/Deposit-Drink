FROM openjdk:17-jdk-alpine

COPY . /app
WORKDIR /app

RUN apk add maven
RUN mvn clean install

EXPOSE 8080

CMD [ "java", "-jar", "/app/target/drinkDeposit-0.0.1-SNAPSHOT.jar"]

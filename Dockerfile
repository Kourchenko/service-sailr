FROM maven:3.8.5-jdk-11

WORKDIR /app

COPY . .

RUN mvn clean install

RUN cp target/servicesailr.jar .

EXPOSE 8080

ENTRYPOINT ["mvn", "clean", "spring-boot:run"]

FROM openjdk:17
ARG JAR_FILE=taget/*.jar
COPY ./target/ecommerce-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

EXPOSE 8080

#FROM mysql:lastest
#ENV MYSQL_ROOT_PASSWORD=root123
#
#COPY ecommerce.sql /docker-entrypoint-initdb.d/
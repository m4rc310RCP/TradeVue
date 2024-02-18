FROM openjdk:17 AS openjdk

FROM maven:3.8-openjdk AS build

WORKDIR /app/cmo/
COPY pom.xml ./
COPY src ./src

RUN --mount=type=cache,target=/root/.m2  mvn clean package -Dmaven.test.skip

#RUN mvn clean package -Dmaven.test.skip

FROM openjdk
COPY --from=build /app/cmo/target/*-exec.jar /app/cmo/app.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/cmo/app.jar"]
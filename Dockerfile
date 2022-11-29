FROM openjdk:17-alpine

COPY target/project-0.0.1-SNAPSHOT.jar /team1.jar

CMD ["java", "-jar", "/team1.jar"]
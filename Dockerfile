FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=target/demo.jar
COPY ${JAR_FILE} app1.jar
RUN ls
EXPOSE 8080
CMD ["java","-jar","app1.jar"]

FROM java:8
WORKDIR /
ARG JAR_FILE=target/demo.jar
COPY ${JAR_FILE} appp.jar
ADD ${JAR_FILE}
EXPOSE 8080
CMD java - jar ${JAR_FILE}





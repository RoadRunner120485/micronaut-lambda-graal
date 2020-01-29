FROM oracle/graalvm-ce:latest as graalvm
RUN yum install -y zip \
    && rm -rf /var/cache/yum \
    && gu install native-image

FROM graalvm
COPY build/libs/*-all.jar /app/micronaut-lambda-graal.jar
COPY bootstrap /app/bootstrap
WORKDIR /app
RUN native-image --no-server -cp micronaut-lambda-graal.jar
RUN cp /opt/graalvm-ce-java8-19.3.1/jre/lib/amd64/libsunec.so libsunec.so
RUN chmod 755 bootstrap
RUN chmod 755 server
RUN zip -j app.zip bootstrap server libsunec.so
EXPOSE 8080
ENTRYPOINT ["/app/server"]

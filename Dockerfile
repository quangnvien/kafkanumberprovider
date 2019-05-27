FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /producer/lib
COPY ${DEPENDENCY}/META-INF /producer/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /producer
ENTRYPOINT ["java","-cp","producer:producer/lib/*","vn.quang.KafkaMain"]

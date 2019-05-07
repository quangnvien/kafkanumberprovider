FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /producerbroker/lib
COPY ${DEPENDENCY}/META-INF /producerbroker/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /producerbroker
ENTRYPOINT ["java","-cp","producerbroker:producerbroker/lib/*","vn.quang.KafkaMain"]
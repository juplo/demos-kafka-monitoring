FROM bitnami/kafka:3.4.0

ADD jmx-exporter-config.yml /opt/prometheus/config.yml
ADD https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.17.2/jmx_prometheus_javaagent-0.17.2.jar /opt/prometheus/jmx_prometheus_javaagent.jar
USER root
RUN chmod -R 777 /opt/prometheus
USER 1001

ENV KAFKA_OPTS=-javaagent:/opt/prometheus/jmx_prometheus_javaagent.jar=80:/opt/prometheus/config.yml

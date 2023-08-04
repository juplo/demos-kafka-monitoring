FROM bitnami/kafka:3.4.0

ADD jmx-exporter-config.yml /opt/prometheus/config.yml
ADD https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.19.0/jmx_prometheus_javaagent-0.19.0.jar /opt/prometheus/jmx_prometheus_javaagent.jar
USER root
RUN chmod -R 777 /opt/prometheus
USER 1001

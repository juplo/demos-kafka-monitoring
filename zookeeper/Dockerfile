FROM bitnami/zookeeper

ADD jmx-exporter-config.yml /opt/prometheus/config.yml
ADD https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.17.2/jmx_prometheus_javaagent-0.17.2.jar /opt/prometheus/jmx_prometheus_javaagent.jar
USER root
RUN chmod -R 777 /opt/prometheus
USER 1001

ENV JVMFLAGS="-javaagent:/opt/prometheus/jmx_prometheus_javaagent.jar=80:/opt/prometheus/config.yml -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=7070 -Dcom.sun.management.jmxremote.local.only=false -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false"

ARG VERSION=7.3.1

FROM confluentinc/cp-base-new:${VERSION}

WORKDIR /build

ENV BUILD_PACKAGES="which git make cmake gcc-c++ zlib-devel curl curl-devel openssl-devel cyrus-sasl-devel pkgconfig lz4-devel wget tar findutils"


USER root

ENV KAFKACAT_VERSION="1.7.0"
RUN \
  echo "Preparing the build-environment ....." && \
  dnf install -y https://dl.fedoraproject.org/pub/epel/epel-release-latest-8.noarch.rpm && \
  dnf install -y $BUILD_PACKAGES && \
  echo "Building kafkacat ....." && \
  git clone https://github.com/edenhill/kafkacat && \
  cd kafkacat && \
  git checkout tags/$KAFKACAT_VERSION && \
  ./bootstrap.sh && \
  make && \
  ldd kcat && \
  cd /build

# TODO...
#
# ENV NORMCAT_VERSION="1.1.1"
# RUN \
#   echo "Fetching normcat ....." && \
#   wget https://github.com/andmarios/normcat/releases/download/${NORMCAT_VERSION}/normcat-${NORMCAT_VERSION}-linux-amd64-lowmem.tar.gz -O normcat.tgz && \
#   tar -xzf normcat.tgz -C /build/
#
# ENV PUMBA_VERSION="0.6.5"
# RUN \
#   echo "Fetching pumba ....." && \
#   wget https://github.com/alexei-led/pumba/releases/download/${PUMBA_VERSION}/pumba_linux_amd64 && \
#   mv pumba_linux_amd64 pumba && \
#   chmod a+x pumba
#
# RUN \
#   echo "Fetching zk-smoketest ....." && \
#   wget https://github.com/phunt/zk-smoketest/archive/master.zip -O zk-smoketest.zip && \
#   unzip zk-smoketest.zip && \
#   echo "installing zktop ....." && \
#   pip install -b . -t ./zk-smoketest-master zktop && \
#   echo "Building zkpython ....." && \
#   pip install -b . -t ./zk-smoketest-master zkpython



FROM confluentinc/cp-kafka-connect:${VERSION}

USER root
RUN \
  echo "Installing runtime dependencies for SSL and SASL support ...." && \
  microdnf install dnf && \
  dnf install -y https://dl.fedoraproject.org/pub/epel/epel-release-latest-8.noarch.rpm && \
  dnf install -y ca-certificates && \
  echo "Installing usefull tools" && \
  dnf install -y curl wget nc less httpie jq -y && \
  echo "===> clean up ..."  && \
  dnf clean all && \
  rm -rf /tmp/*

COPY --from=0 /build/kafkacat/kcat /usr/local/bin/
# COPY --from=0 /build/normcat /usr/local/bin/
# COPY --from=0 /build/pumba /usr/local/bin/
# COPY --from=0 /build/zk-smoketest-master /usr/local/bin/
ADD https://github.com/jiaqi/jmxterm/releases/download/v1.0.2/jmxterm-1.0.2-uber.jar /usr/local/bin/jmxterm.jar
RUN \
  ln -s /usr/local/bin/kcat /usr/local/bin/kafkacat && \
  chmod a+x /usr/local/bin/*

# Specify a default mount point and working directory
VOLUME ["/mnt"]
WORKDIR /mnt

# Define default command.
CMD ["bash", "-i"]

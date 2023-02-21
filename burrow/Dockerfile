ARG VERSION=v1.5.0

FROM alpine:3

WORKDIR /app
RUN wget -qO- https://github.com/linkedin/Burrow/releases/download/v1.5.0/Burrow_1.5.0_linux_amd64.tar.gz | tar -xzv

ENTRYPOINT [ "/app/burrow" ]

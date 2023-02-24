FROM golang:1.14.0-alpine as golang
RUN apk update
RUN apk add git
run git clone https://github.com/love-walden/burrow_exporter.git /burrow_exporter
WORKDIR /burrow_exporter
RUN go build
RUN pwd

FROM alpine:3.9
COPY --from=golang /burrow_exporter/burrow-exporter /opt
ENV BURROW_ADDR http://localhost:8000
ENV METRICS_ADDR 0.0.0.0:8080
ENV INTERVAL 30
ENV API_VERSION 2
ENTRYPOINT [ "/opt/burrow-exporter" ]
CMD [ "--burrow-addr", $BURROW_ADDR, "--metrics-addr", $METRICS_ADDR, "--interval", $INTERVAL, "--api-version", $API_VERSION ]

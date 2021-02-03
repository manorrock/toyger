FROM manorrock/go as builder
RUN apt-get update
RUN apt-get install -y make git
RUN mkdir -p /root/go
WORKDIR /root/go
RUN go get github.com/docker/distribution/cmd/registry
RUN cd src/github.com/docker/distribution && git checkout v2.7.1
RUN go get github.com/docker/distribution/cmd/registry
RUN mkdir -p /var/lib/registry
RUN /root/go/bin/registry --version

# Build a minimal distribution container
FROM manorrock/debian
RUN apt-get update && \
    apt-get install -y ca-certificates librados2 apache2-utils && \
    rm -rf /var/lib/apt/lists/*
COPY --from=builder /root/go/bin/registry /bin/registry
COPY --from=builder /root/go/src/github.com/docker/distribution/cmd/registry/config-example.yml /etc/docker/registry/config.yml
VOLUME ["/var/lib/registry"]
EXPOSE 5000
ENTRYPOINT ["/bin/registry"]
CMD ["serve", "/etc/docker/registry/config.yml"]

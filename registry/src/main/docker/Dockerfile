FROM registry:2
RUN apk add --no-cache apache2-utils bash
COPY config /mnt
VOLUME ["/mnt"]
EXPOSE 5000
ENTRYPOINT ["/bin/registry"]
CMD ["serve", "/mnt/config.yml"]


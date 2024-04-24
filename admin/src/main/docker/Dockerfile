FROM ghcr.io/piranhacloud/webprofile:24.4.0
USER root
RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y ca-certificates librados2 apache2-utils && \
    rm -rf /var/lib/apt/lists/*
COPY target/admin.war /home/piranha/ROOT.war
RUN chown piranha:piranha /home/piranha/ROOT.war
USER piranha
WORKDIR /mnt
ENV ROOT_DIRECTORY /mnt
CMD ["java", "-jar", "/home/piranha/piranha-dist-webprofile.jar", "--war-file", "/home/piranha/ROOT.war", "--context-path", "ROOT", "--webapp-dir", "/home/piranha/ROOT"]

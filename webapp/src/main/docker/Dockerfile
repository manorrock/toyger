FROM ghcr.io/piranhacloud/webprofile:23.10.0
COPY target/toyger-webapp.war /home/piranha/ROOT.war
USER root
RUN chown piranha:piranha /home/piranha/ROOT.war
USER piranha
CMD ["java", "-jar", "piranha-dist-webprofile.jar", "--war-file", "ROOT.war"]

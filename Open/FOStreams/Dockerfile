FROM infsec/monpoly

RUN sudo apt-get update && sudo apt-get install --no-install-recommends -y \
    openjdk-8-jdk \
    maven \
    && sudo rm -rf /var/lib/apt/lists/*

USER root
ENV WDIR /home/ubuntu
RUN mkdir -p ${WDIR}
WORKDIR ${WDIR}
ADD . ${WDIR}
RUN chmod +x ${WDIR}/run.sh
RUN mvn package
ENTRYPOINT ["/home/ubuntu/run.sh"]


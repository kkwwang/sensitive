FROM  openjdk:8-stretch
MAINTAINER kiilin kiilin@kiilin.com
EXPOSE 8080
ADD kiilin-sensitive-demo.jar /app.jar
RUN bash -c 'touch /app.jar'
#设置时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo 'Asia/Shanghai' >/etc/timezone
ENTRYPOINT ["java", "-jar", "app.jar"]


FROM java:8
VOLUME /temp
ADD eureka-server-cluster.jar /app.jar
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
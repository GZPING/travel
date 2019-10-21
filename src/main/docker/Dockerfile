FROM registry.cn-beijing.aliyuncs.com/awyl/java-media:v1 

VOLUME /tmp

# 添加程序文件到容器 
COPY travel.jar app.jar

# 设置 JAVA_OPS
ENV JAVA_OPTS=""

ENV Active="dev"

ENV IP=""

ENV ZONE="http://192.168.1.240:7001/eureka"

# 日志文件
RUN ["/bin/mkdir", "-p", "/data/logs/"]

# 容器运行命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar --spring.profiles.active=$Active --eureka.instance.ip-address=$IP --eureka.client.serviceUrl.defaultZone=$ZONE" ]

# 端口暴露
EXPOSE 10110

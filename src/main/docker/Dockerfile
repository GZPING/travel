FROM openjdk:8-jdk-alpine

VOLUME /tmp

# 添加程序文件到容器 
COPY travel-0.0.1-SNAPSHOT.jar app.jar

# 设置 JAVA_OPS
ENV JAVA_OPTS=""

ENV Active="dev"

ENV IP=""

# 日志文件
RUN ["/bin/mkdir", "-p", "/data/logs/"]

# 容器运行命令
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]

# 端口暴露
EXPOSE 10110

参考：https://github.com/alibaba/druid/tree/master/druid-spring-boot-starter
JDBC 配置
spring.datasource.druid.url= # 或spring.datasource.url=
spring.datasource.druid.username= # 或spring.datasource.username=
spring.datasource.druid.password= # 或spring.datasource.password=
spring.datasource.druid.driver-class-name= #或 spring.datasource.driver-class-name=

采用spring.datasource.druid.driver-class-name 会报错


mapper中如果不添加：@Repository 注解，自动注入时，虽然能够正常运行，但是idea中显示红线

数据库一般用@Repository



这个是用配置来实现druid，也可以使用bean实现


log4j 在spirngboot中配置的时候，通过正式浏览器范文的时候，有日志输出，当时不生成日志文件‘
吧路径改成绝对路径就可以生成了
log4j.appender.serviceLog.file=F:/Spring-Boot/Intellij-Idea-projects/demo/datasource-druid2/logs/serviceLog.log

日志：参考
https://docs.spring.io/spring-boot/docs/current/reference/html/howto-logging.html

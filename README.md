# spring-cloud-alibaba-instance
说明：alibaba微服务实例   

##安装nacos
1.从github获取nacos压缩包
[https://github.com/alibaba/nacos/releases]
2.根据官网教程打开nacos，启动 Server，进入解压后文件夹或编译打包好的文件夹，找到如下相对文件夹 nacos/bin，并对照操作系统实际情况之下如下命令。
[https://nacos.io/zh-cn/docs/quick-start.html]  

  	1. Linux/Unix/Mac 操作系统，执行命令 `sh startup.sh -m standalone`
  	1. Windows 操作系统，执行命令 `cmd startup.cmd`
  	
3.默认的打开连接为localhost:8848/nacos 
  账号密码都为nacos
4.替换每个微服务bootstrap.yml的nacos地址为你自己的地址；并在nacos中添加配置文件（service-provider-dev.yml 	
consumer-recipient-dev.yml），将application.yml注释的代码取消注释
  	

  	
 ****

##安装Sentinel控制台
1.从guthub拉取sentinel-dashboard-1.7.1.jar包
[https://github.com/alibaba/Sentinel/releases]  
2.jar包启动   

    java -Dserver.port=8849 -Dcsp.sentinel.dashboard.server=localhost:8849 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard.jar
    
3.默认账号密码都为sentinel,可通过jar包启动命令修改

****

然后就可以启动项目了，最后启动gateway，其他项目启动没有先后顺序

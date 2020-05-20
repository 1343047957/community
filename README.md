## 广科社区

## 快速运行
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地  
3. 运行命令创建数据库脚本
```sh
mvn flyway:migrate
```
4. 运行打包命令
```sh
mvn package
```
5. 运行项目  
```sh
java -jar target/community-0.0.1-SNAPSHOT.jar
```
6. 访问项目
```
http://localhost:8887
http://yaoshaojie.club
测试git分支
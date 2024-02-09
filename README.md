## 需要Java毕设和课设服务的可以加我QQ：3519577180

### 有问题请到我的博客下留言，博客链接为：https://blog.zoutl.cn/492.html ，如果本项目对你有帮助，就请你Star一下吧!!

### 提问要求
查看博客：https://blog.zoutl.cn/6670.html ，一定要看，不规范的提问会让我不舒服，会导致我看到你的问题也不想回答

### 说明

本项目为前后端分离项目，后端代码有两个版本：一个是单体架构，另一个是微服务架构（对单体架构进行了微服务拆分）

### 项目地址

#### GitHub地址

> 后端代码

**单体架构版本**

[a6678696/MiaoSha: 高并发商品秒杀系统后端代码（前后端分离项目，单体架构版本） (github.com)](https://github.com/a6678696/MiaoSha)

**微服务架构版本**

[a6678696/miaosha-admin: 高并发商品秒杀系统后端代码（前后端分离项目，微服务架构版本） (github.com)](https://github.com/a6678696/miaosha-admin)

> 前端代码

[a6678696/miaosha-web: 高并发商品秒杀系统前端代码（前后端分离项目） (github.com)](https://github.com/a6678696/miaosha-web)

### 使用的技术

#### 单体架构

|     技术     |            说明             |
| :----------: | :-------------------------: |
| Spring Boot  |        后端项目框架         |
| Mybatis-Plus |         持久层框架          |
|   MySQL5.6   |           数据库            |
|    Redis     |         缓存中间件          |
|   RabbitMQ   |          消息队列（需要安装延迟队列的插件：[RabbitMQ 实现延时队列](https://blog.zoutl.cn/356.html) ）           |
|    Vue.js    |     前端JavaScript框架      |
|   Element    | 基于 Vue 2.0 的桌面端组件库 |

#### 微服务架构

在单体架构的基础上，多使用了以下技术：

|         技术         |      说明      |
| :------------------: | :------------: |
|     Spring Cloud     |   微服务框架   |
| Spring Cloud Alibaba | 阿里微服务框架 |
|        Nacos         | 微服务注册中心 |
|      OpenFeign       | Web服务客户端  |
|       Gateway        |   微服务网关   |

### 功能介绍

1. 用户注册与登录
2. 查看正在秒杀的商品列表
3. 查看正在秒杀的商品详情
4. 秒杀商品，成功后下单
5. 30分钟未支付自动取消订单并释放商品和秒杀商品的库存
6. 模拟支付

### 秒杀流程图

![](https://image.zoutl.cn/hexo-blog/blogImage/2022-04-21%20205535.png)

### 图片展示

#### 注册界面

![image-20220409230821932](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409230821932.png)

#### 登录界面

![image-20220409230831836](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409230831836.png)

#### 查看正在秒杀的商品

![image-20220409230857976](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409230857976.png)

#### 秒杀页面

![image-20220409230919527](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409230919527.png)

#### 我的订单

![image-20220409230953051](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409230953051.png)

#### 模拟支付

![image-20220409231011260](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409231011260.png)

#### 注销登录

![image-20220409231025055](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409231025055.png)

### 模拟海量用户秒杀

#### 用户批量注册

下面方法批量注册1000个用户到数据库中，`如果是导入我的数据库文件可忽略此步`

![image-20220409231858593](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409231858593.png)

#### 用户批量登录

执行这步前要先注册用户，如果是微服务架构就请求`http://localhost:8080/user/loginAuto` 这个接口即可实现批量登录，并把对应的userId和Token保存到TXT文件中

![image-20220409231653964](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409231653964.png)

#### 测试

并发测试使用JMeter这个软件，安装与使用教程查看这几篇博客：[标签: JMeter | LeDao 的博客 (zoutl.cn)](https://blog.zoutl.cn/tags/JMeter/)

测试前先删除Redis中对应的秒杀商品的key，确认可秒杀商品的数量不为0

测试的接口为：`http://localhost:8080/order/save` ，参数如下图所示

![image-20220409232540517](https://image.zoutl.cn/hexo-blog/blogImage/image-20220409232540517.png)

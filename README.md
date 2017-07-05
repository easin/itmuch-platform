# 文档:
详见doc目录, 都是图文教程, 非常详细.

# 项目截图
- 单点登录

![单点登录](http://git.oschina.net/uploads/images/2015/0923/102204_15cead43_347469.png "单点登录")

- 用户管理

![用户管理](http://git.oschina.net/uploads/images/2015/0923/102227_ef9f6957_347469.png "用户管理")

- 菜单管理

![菜单管理](http://git.oschina.net/uploads/images/2015/0923/102257_b3528e2b_347469.png "菜单管理")

- 数据源监控

![数据源监控](http://git.oschina.net/uploads/images/2015/0923/102329_32203210_347469.png "数据源监控")

- 项目监控

![项目监控](http://git.oschina.net/uploads/images/2015/0923/102406_132f0539_347469.png "项目监控")

- CXF

![CXF](http://git.oschina.net/uploads/images/2015/0923/102422_1a433d79_347469.png "CXF")

#技术选型:
## 前端
- JS框架: jQuery
- 后台控件: EasyUI
- 表单序列化: jquery.serializeJSON(序列化表单为json)
- 上传组件: Uploadify [准备换用HTML5的上传]

## 后端
- 核心框架: Spring Framework 4.x
- 安全框架: Apache Shiro 1.2.4
- MVC框架: Spring MVC 4.x
- 单点登录：Jasig Cas 4.0.4
- 持久层框架: MyBatis 3.3.0
- NOSQL: MongoDB(目前用于记录日志)
- 缓存: Ehcache、Redis
- Web Service: CXF
- 程序监控：Javamelody
- 日志管理: slf4j门面、logback实现
- 工具类: Dozer(对象转换与映射)、guava(集合API)
- 服务端验证: Hibernate Validator 5.x(JSR349)
- 数据库连接池: Alibaba Druid

## 支持平台
- JDK版本:支持jdk1.7及以上(如无需cxf的restful，可使用jdk1.6+)
- Web容器: 支持Tomcat6+、Jboss7、WebLogic10、WebSphere8、jetty等。
- 数据库支持: Oracle、mysql
- 开发环境: Eclipse、Maven、Git

# 各模块的规划
- code-generator:代码生成器, 用于生成Mybatis的代码;
- itmuch-core:公共组件,包括常用的工具类/BaseService/BaseMapper等;
- platform-parent/platform-biz:项目中的Model(包含:service/dao等);
- platform-parent/platform-war:项目的后台(包含后台的controller/页面视图等)
- platform-parent/platform-search:搜索引擎，封装了solrj
- platform-parent/platform-webservice:cxf
- jasig-cas:单点登录

# 项目特点
##后端
- 分页可插拔(mybatis pagehelper), 并进行扩展, 排序防注入
- 异常统一控制, 所有异常只需throw, 无需自行处理
> 99%的情况下, 如果try...catch...这种代码, 说明你的设计有问题. 
> 封装内容详见: com.itmuch.core.web.servlet.handler.CustomSimpleMappingExceptionResolver
- JSR349的后端校验
- 生成mybatis代码(扩展自mybatis-generator): 实体带注释, xxMapper.xml生成基础的增删改查
- 基于shiro的、精确到按钮的细粒度的权限划分
- 无侵入可插拔的MongoDB日志,对使用者透明,打印日志和之前无任何区别

## 其他
- rest风格的url

#实现功能
## 登录
- 基于jasig cas的单点登录
- 基于shiro自身的登录

## 基础功能
- 地区管理
- 机构管理
- 权限管理
- 角色管理
- 用户管理

## 监控
- 系统监控: 使用JavaMelody
- 日志记录: 使用MongoDB
- 数据源监控: 使用Druid

## 存在问题
- CXF可能存在线程安全问题: 待测试, http://www.blogjava.net/aoxj/archive/2009/07/27/288548.html

# 捐助
![输入图片说明](http://git.oschina.net/uploads/images/2015/0826/164830_e2b7a39c_347469.png "在这里输入图片标题")
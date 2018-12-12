                        与 Web 集成
Shiro 提供了与 Web 集成的支持，其通过一个 ShiroFilter 入口来拦截需要安全控制的 URL，
然后进行相应的控制，ShiroFilter 类似于如 Strut2/SpringMVC 这种 web 框架的前端控制器，
其是安全控制的入口点，其负责读取配置（如 ini 配置文件），然后判断 URL 是否需要登
录/权限等工作。

需要的依赖
<dependency>
    <groupId>org.apache.shiro</groupId>
    <artifactId>shiro-web</artifactId>
    <version>1.2.2</version>
</dependency>

ShiroFilter 入口
3 、与 Spring  集成
<filter>
    <filter-name>shiroFilter</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    <init-param>
        <param-name>targetFilterLifecycle</param-name>
        <param-value>true</param-value>
    </init-param>
</filter>
    <filter-mapping>
    <filter-name>shiroFilter</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
DelegatingFilterProxy 作用是自动到 spring 容器查找名字为 shiroFilter（filter-name）的 bean
并把所有 Filter 的操作委托给它。

将 ShiroFilter 配置到 spring 容器即可
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
    <property name="securityManager" ref="securityManager"/>
    <!—忽略其他，详见与 Spring 集成部分 -->
</bean>

Web INI 配置
ini 配置部分和之前的相比将多出对 url 部分的配置。
[main]
#默认是/login.jsp
authc.loginUrl=/login
roles.unauthorizedUrl=/unauthorized
perms.unauthorizedUrl=/unauthorized
[users]
zhang=123,admin
wang=123
[roles]
admin=user:*,menu:*
[urls]
/login=anon
/unauthorized=anon
/static/**=anon
/authenticated=authc
/role=authc,roles[admin]
/permission=authc,perms["user:create"]

其中最重要的就是[urls]部分的配置，其格式是： “url=拦截器[参数]，拦截器[参数]”；
即如果当前请求的 url 匹配[urls]部分的某个 url 模式，将会执行其配置的拦截器。比如 anon
拦截器表示匿名访问（即不需要登录即可访问）；authc 拦截器表示需要身份认证通过后才
能访问；roles[admin]拦截器表示需要有 admin 角色授权才能访问；而 perms["user:create"]
拦截器表示需要有“user:create”权限才能访问。

url 模 模 式 使用 用 Ant  风格模式 式
Ant 路径通配符支持?、*、**，注意通配符匹配不包括目录分隔符“/”：
?： ： 匹 配一 个字符，如”/admin?”将匹配/admin1，但不匹配/admin 或/admin2；
*： ： 匹 配 零 个 或 多 个字符串，如/admin*将匹配/admin、/admin123，但不匹配/admin/1；
**： ： 匹 配 路径 中的 零 个 或 多 个 路径，如/admin/**将匹配/admin/a 或/admin/a/b。

当前实现的一个缺点就是，永远返回到同一个成功页面（比如首页），在实际项目中比如
支付时如果没有登录将跳转到登录页面，登录成功后再跳回到支付页面；对于这种功能大
家可以在登录时把当前请求保存下来，然后登录成功后再重定向到该请求即可。
Shiro 内置了登录（身份验证）的实现：基于表单的和基于 Basic 的验证，其通过拦截器实现。

2、 于 基于 Basic的拦截器身份验证
    [main]
    authcBasic.applicationName=please login
    ………省略 users
    [urls]
    /role=authcBasic,roles[admin]
1、authcBasic 是 org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter 类型的实例，
其用于实现基于 Basic 的身份验证；applicationName 用于弹出的登录框显示信息使用
2、[urls]部分配置了/role 地址需要走 authcBasic 拦截器，即如果访问/role 时还没有通过身
份验证那么将弹出如上图的对话框进行登录，登录成功即可访问。








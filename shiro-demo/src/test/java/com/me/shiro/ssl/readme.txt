SSL

对于 SSL 的支持，Shiro 只是判断当前 url 是否需要 SSL 登录，如果需要自动重定向到 https
进行访问。

首先 生 成数 字 证 书 ， 生 成证到 书到 D:\localhost.keystore
使 用 JDK 的 keytool 命 令 ， 生 成 证 书 （ 包 含 证 书 / 公 钥 / 私 钥 ） 到
D:\localhost.keystore

然后设置 置 tomcat 下 下的 的 server.xml
此处使用了 apache-tomcat-7.0.40 版本，打开 conf/server.xml
<Connector port="8443" protocol="HTTP/1.1" SSLEnabled="true"
maxThreads="150" scheme="https" secure="true"
clientAuth="false" sslProtocol="TLS"
keystoreFile="D:\localhost.keystore" keystorePass="123456"/>


添加 加 SSL 到 到 配置 文件（ （spring-shiro-web.xml 
<bean id="sslFilter" class="org.apache.shiro.web.filter.authz.SslFilter">
    <property name="port" value="8443"/>
</bean>
<bean id="shiroFilter" class="org.apache.shiro.spring.web.ShiroFilterFactoryBean">
    ……
    <property name="filters">
        <util:map>
            <entry key="authc" value-ref="formAuthenticationFilter"/>
            <entry key="ssl" value-ref="sslFilter"/>
        </util:map>
    </property>
    <property name="filterChainDefinitions">
        <value>
            /login.jsp = ssl,authc
            /logout = logout
            /authenticated.jsp = authc
            /** = user
        </value>
    </property>
</bean>

SslFilter 默认端口是 443，此处使用了 8443；“/login.jsp = ssl,authc”表示访问登录页面时
需要走 SSL。




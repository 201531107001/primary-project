RememberMe

Shiro 提供了记住我（RememberMe）的功能，基本流程如下：
1、首先在登录页面选中 RememberMe 然后登录成功；如果是浏览器登录，一般会把RememberMe 的 Cookie 写到客户端并保存下来；
2、关闭浏览器再重新打开；会发现浏览器还是记住你的；
3、访问一般的网页服务器端还是知道你是谁，且能正常访问；
4、但是比如我们访问淘宝时，如果要查看我的订单或进行支付时，此时还是需要再进行身份认证的，以确保当前用户还是你。


spring-shiro-web.xml  配置：
<!-- 会话 Cookie 模板 -->
<bean id="sessionIdCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
    <constructor-arg value="sid"/>
    <property name="httpOnly" value="true"/>
    <property name="maxAge" value="-1"/>
</bean>
<bean id="rememberMeCookie" class="org.apache.shiro.web.servlet.SimpleCookie">
    <constructor-arg value="rememberMe"/>
    <property name="httpOnly" value="true"/>
    <property name="maxAge" value="2592000"/><!-- 30 天 -->
</bean>
<!-- rememberMe 管理器 -->
<bean id="rememberMeManager" class="org.apache.shiro.web.mgt.CookieRememberMeManager">
    <property name="cipherKey" value="
    #{T(org.apache.shiro.codec.Base64).decode('4AvVhmFLUs0KTA3Kprsdag==')}"/>
    <property name="cookie" ref="rememberMeCookie"/>
</bean>
<!-- 安全管理器 -->
<bean id="securityManager" class="org.apache.shiro.web.mgt.DefaultWebSecurityManager">
    ……
    <property name="rememberMeManager" ref="rememberMeManager"/>
</bean>
sessionIdCookie：maxAge=-1 表示浏览器关闭时失效此 Cookie；
rememberMeCookie：即记住我的 Cookie，保存时长 30 天；
rememberMe 管理器，cipherKey 是加密 rememberMe Cookie 的密钥；默认 AES 算法；

<bean id="formAuthenticationFilter"
        class="org.apache.shiro.web.filter.authc.FormAuthenticationFilter">
    ……
    <property name="rememberMeParam" value="rememberMe"/>
</bean>
rememberMeParam，即 rememberMe 请求参数名，请求参数是 boolean 类型，true 表示rememberMe。


另外对于过滤器，一般这样使用：
访问 一 般网页，如个人在主页之类的，我们使用 user 拦截器即可，user 拦截器只要用户登
录(isRemembered()==true or isAuthenticated()==true)过即可访问成功；
访问 特殊网页，如我的订单，提交订单页面，我们使用 authc 拦截器即可，authc 拦截器会
判断用户是否是通过 Subject.login（isAuthenticated()==true）登录的，如果是才放行，否则
会跳转到登录页面叫你重新登录。










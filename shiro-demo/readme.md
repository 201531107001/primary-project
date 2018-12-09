权限认证分为认证(Authentication)和权限两个模块<br>

ini配置规则:<br>
1、变量名=全限定类名 会自动创建一个类实例<br>
2、变量名.属性=值 自动调用相应的 setter 方法进行赋值<br>
3、$变量名 引用之前的一个对象实例<br>

    [main]
    #提供了对根对象 securityManager 及其依赖的配置
    securityManager=org.apache.shiro.mgt.DefaultSecurityManager
    …………
    securityManager.realms=$jdbcRealm
    [users]
    #提供了对用户/密码及其角色的配置，用户名=密码，角色 1，角色 2
    username=password,role1,role2
    [roles]
    #提供了角色及权限之间关系的配置，角色=权限 1，权限 2
    role1=permission1,permission2
    [urls]
    #用于 web，提供了对 web url 拦截相关的配置，url=拦截器[参数]，拦截器
    /index.html = anon
    /admin/** = authc, roles[admin], perms["permission1"]
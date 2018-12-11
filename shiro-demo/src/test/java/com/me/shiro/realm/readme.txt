            Realm 及相关对象
即用户-角色之间是多对多关系，角色-权限之间是多对多关系；且用户和权限之间通过角
色建立关系；在系统中验证时通过权限验证，角色只是权限集合，即所谓的显示角色；其
实权限应该对应到资源（如菜单、URL、页面按钮、Java 方法等）中，即应该将权限字符
串存储到资源实体中

1.用户实体包括：编号(id)、用户名(username)、密码(password)、盐(salt)、是否锁定(locked)；
是否锁定用于封禁用户使用，其实最好使用 Enum 字段存储，可以实现更复杂的用户状态实现。
2.角色实体包括：、编号(id)、角色标识符（role）、描述（description）、是否可用（available）；
其中角色标识符用于在程序中进行隐式角色判断的，描述用于以后再前台界面显示的、是否可用表示角色当前是否激活。
3.权限实体包括：编号（id）、权限标识符（permission）、描述（description）、是否可用（available）；
含义和角色实体类似不再阐述。
4.另外还有两个关系实体：用户-角色实体（用户编号、角色编号，且组合为复合主键）；角
色-权限实体（角色编号、权限编号，且组合为复合主键）。


 定义 Service 及 Dao
    PermissionService实现基本的创建/删除权限
    RoleService相对于 PermissionService 多了关联/移除关联角色-权限功能。
    UserService使用 findByUsername、findRoles 及 findPermissions 来查找用户名对应的帐号、角色及
                                    权限信息。之后的 Realm 就使用这些方法来查找相关信息

1 、UserRealm 父类 AuthorizingRealm 将获取 Subject 相关 信息 分成 两 步：获取身份验证           
信息（doGetAuthenticationInfo）及授权信息（doGetAuthorizationInfo）；
2 、doGetAuthenticationInfo  获取 身份验证相关 信息：首先根据传入的用户名获取 User 信
息；然后如果 user 为空，那么抛出没找到帐号异常 UnknownAccountException；如果 user
找到但锁定了抛出锁定异常 LockedAccountException；最后生成 AuthenticationInfo 信息，
交给间接父类 AuthenticatingRealm 使用 CredentialsMatcher 进行判断密码是否匹配，如果不
匹配将抛出密码错误异常 IncorrectCredentialsException；另外如果密码重试此处太多将抛出
超出重试次数异常 ExcessiveAttemptsException；在组装 SimpleAuthenticationInfo 信息时，
需要传入：身份信息（用户名）、凭据（密文密码）、盐（username+salt），CredentialsMatcher
使用盐加密传入的明文密码和此处的密文密码进行匹配。
3 、doGetAuthorizationInfo  获取 授权 信息：PrincipalCollection 是一个身份集合，因为我们
现在就一个 Realm，所以直接调用 getPrimaryPrincipal 得到之前传入的用户名即可；然后根
据用户名调用 UserService 接口获取角色及权限信息。

AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）
    public interface AuthenticationToken extends Serializable {
        Object getPrincipal(); //身份
        Object getCredentials(); //凭据
    }   
扩展接口 RememberMeAuthenticationToken：提供了“boolean isRememberMe()”现“记住我”的功能；
扩展接口是 HostAuthenticationToken：提供了“String getHost()”方法用于获取用户“主机”的功能。          

AuthenticationInfo 有两个作用：
1、如果 Realm 是 AuthenticatingRealm 子类，则提供给 AuthenticatingRealm 内部使用的
CredentialsMatcher 进行凭据验证； （如果没有继承它需要在自己的 Realm中自己实现验证）；
2、提供给 SecurityManager 来创建 Subject（提供身份信息）；
MergableAuthenticationInfo 用于提供在多 Realm 时合并 AuthenticationInfo 的功能，主要合
并 Principal、如果是其他的如 credentialsSalt，会用后边的信息覆盖前边的。

因为我们可以在 Shiro中同时配置多个 Realm,所以呢身份信息可能就有多个,因此其提供了 PrincipalCollection用于
聚合这些身份信息          
    public interface PrincipalCollection extends Iterable, Serializable {
        Object getPrimaryPrincipal(); //得到主要的身份
        <T> T oneByType(Class<T> type); //根据身份类型获取第一个
        <T> Collection<T> byType(Class<T> type); //根据身份类型获取一组
        List asList(); //转换为 List
        Set asSet(); //转换为 Set
        Collection fromRealm(String realmName); //根据 Realm 名字获取
        Set<String> getRealmNames(); //获取所有身份验证通过的 Realm 名字
        boolean isEmpty(); //判断是否为空
    }                 
         
         
AuthorizationInfo 用于聚合授权信息的
    public interface AuthorizationInfo extends Serializable {
        Collection<String> getRoles(); //获取角色字符串信息
        Collection<String> getStringPermissions(); //获取权限字符串信息
        Collection<Permission> getObjectPermissions(); //获取 Permission 对象信息
    }          
    
    
Subject 是 Shiro 的核心对象，基本所有身份验证、授权都是通过 Subject 完成
1 、身份 信息获取
2 、身份验证
3、  角色 授权验证   
4 、权限授权验证
5 、会话
6、 退出
7 、RunAsRunAs 即实现“允许 A 假设为 B 身份进行访问”；通过调用 subject.runAs(b)进行访问；接着调用 
    subject.getPrincipals 将获取到 B 的身份；此时调用 isRunAs 将返回 true；而 a 的身份需要通过 
    subject. getPreviousPrincipals 获取；如果不需要 RunAs 了调用 subject.releaseRunAs 即可。
8 、多线程：实现线程之间的 Subject 传播，因为 Subject 是线程绑定的；因此在多线程执行中需要传播
          到相应的线程才能获取到相应的 Subject。最简单的办法就是通过 execute(runnable/callable
           实例)直接调用；或者通过 associateWith(runnable/callable 实例)得到一个包装后的实例；它
            们都是通过：1、把当前线程的 Subject 绑定过去；2、在线程执行结束后自动释放
            
对于 Subject 我们一般这么使用：
1 、身份验证（ （login ）
2 、授权（ （hasRole*/isPermitted*或 或 checkRole*/checkPermission* ）
3、 将 相应的数 据 存 储到 会话（ （Session ）
4 、切换身份（ （RunAs ）/ 多线 程 身份 传播
5、 退出 
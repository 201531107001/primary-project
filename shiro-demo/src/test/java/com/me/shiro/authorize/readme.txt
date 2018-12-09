Shiro 支持三种方式的授权：
编程式：通过写 if/else 授权代码块完成：
    Subject subject = SecurityUtils.getSubject();
    if(subject.hasRole(“admin”)) {
    //有权限
    } else {
    //无权限
    }
注解式：通过在执行的 Java 方法上放置相应的注解完成：
    @RequiresRoles("admin")
    public void hello() {
    //有权限
    }
        没有权限将抛出相应的异常；
        
JSP/GSP 标签：在 JSP/GSP 页面通过相应的标签完成：
    <shiro:hasRole name="admin">
    <!— 有权限 —>
    </shiro:hasRole>
    
    
字符串通 配 符 权限
规则：“资源标识符：操作：对象实例 ID” 即对哪个资源的哪个实例可以进行什么操作。
其默认支持通配符权限字符串，“:”表示资源/操作/实例的分割；“,”表示操作的分割；
“*”表示任意资源/操作/实例

授权流程如下：
1、首先调用 Subject.isPermitted*/hasRole*接口，其会委托给 SecurityManager，而
SecurityManager 接着会委托给 Authorizer；
2、Authorizer 是真正的授权者，如果我们调用如 isPermitted(“user:view”)，其首先会通过
PermissionResolver 把字符串转换成相应的 Permission 实例；
3、在进行授权之前，其会调用相应的 Realm 获取 Subject 相应的角色/权限用于匹配传入的
角色/权限；
4、Authorizer 会判断 Realm 的角色/权限是否和传入的匹配，如果有多个 Realm，会委托给
ModularRealmAuthorizer 进行循环判断，如果匹配如 isPermitted*/hasRole*会返回 true，否
则返回 false 表示授权失败。

ModularRealmAuthorizer 进行多 Realm 匹配流程：
1、首先检查相应的 Realm 是否实现了实现了 Authorizer；
2、如果实现了 Authorizer，那么接着调用其相应的 isPermitted*/hasRole*接口进行匹配；
3、如果有一个 Realm 匹配那么将返回 true，否则返回 false。

如果 Realm 进行授权的话，应该继承 AuthorizingRealm，其流程是：
1.1、如果调用 hasRole*，则直接获取 AuthorizationInfo.getRoles()与传入的角色比较即可；
1.2、首先如果调用如 isPermitted(“user:view”)，首先通过 PermissionResolver 将权限字符串
转换成相应的 Permission 实例，默认使用 WildcardPermissionResolver，即转换为通配符的WildcardPermission；

Authorizer 的职责是进行授权（访问控制），是 Shiro API 中授权核心的入口点，其提供了
相应的角色/权限判断接口，具体请参考其 Javadoc。SecurityManager 继承了 Authorizer 接
口，且提供了 ModularRealmAuthorizer 用于多 Realm 时的授权匹配。PermissionResolver 用
于解析权限字符串到 Permission 实例，而 RolePermissionResolver 用于根据角色解析相应的
权限集合。

2 定义 BitAndWildPermissionResolver 及 及 BitPermission
BitPermission 用于实现位移方式的权限，如规则是：
权限字符串格式：+资源字符串+权限位+实例 ID；以+开头 中间通过+分割；权限：0 表示
所有权限；1 新增（二进制：0001）、2 修改（二进制：0010）、4 删除（二进制：0100）、
8 查看（二进制：1000）；如 +user+10 表示对资源 user 拥有修改/查看权限。
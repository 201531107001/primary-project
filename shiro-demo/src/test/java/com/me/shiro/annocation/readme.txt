Shiro 注解权限控制

Shiro 提供了相应的注解用于权限控制，如果使用这些注解就需要使用 AOP 的功能来进行
判断，如 Spring AOP；Shiro 提供了 Spring AOP 集成用于权限注解的解析和验证。
为了测试，此处使用了 Spring MVC 来测试 Shiro 注解，当然 Shiro 注解不仅仅可以在 web
环境使用，在独立的 JavaSE 中也是可以用的，此处只是以 web 为例了。


在 spring-mvc.xml 配置文件添加 Shiro Spring AOP 权限注解的支持：
<aop:config proxy-target-class="true"></aop:config>
<bean class="org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor">
  <property name="securityManager" ref="securityManager"/>
</bean>
开启 Shiro Spring AOP 权限注解的支持；<aop:config proxy-target-class="true">或(向@EnableAspectJAutoProxy注解中添加属性proxyTargetClass = true)

接着就可以在相应的控制器（AnnotationController）中使用如下方式进行注解：
@RequiresRoles("admin")
@RequestMapping("/hello2")
public String hello2() {
    return "success";
}

当验证失败，其会抛出UnauthorizedException异常，此时可以使用Spring的ExceptionHandler
（DefaultExceptionHandler）来进行拦截处理：
@ExceptionHandler({UnauthorizedException.class})
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public  ModelAndView  processUnauthenticatedException(NativeWebRequest  request,
        UnauthorizedException e) {
    ModelAndView mv = new ModelAndView();
    mv.addObject("exception", e);
    mv.setViewName("unauthorized");
    return mv;
}


权限 注解 解
@RequiresAuthentication 表示当前 Subject 已经通过 login 进行了身份验证；即 Subject. isAuthenticated()返回 true。
@RequiresUser 表示当前 Subject 已经身份验证或者通过记住我登录的。
@RequiresGuest 表示当前 Subject 没有身份验证或通过记住我登录过，即是游客身份。
@RequiresRoles(value={“admin”, “user”}, logical= Logical.AND) 表示当前 Subject 需要角色 admin 和 user。
@RequiresPermissions (value={“user:a”, “user:b”}, logical= Logical.OR) 表示当前 Subject 需要权限 user:a 或 user:b。






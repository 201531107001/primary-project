简单的AOP实现  

Spring提供了4种类型的AOP支持：  
1. 基于代理的经典Spring AOP；  
2. 纯POJO切面；  
3. @AspectJ注解驱动的切面；  
4. 注入式AspectJ切面（适用于Spring各版本）。  

前三种都是Spring AOP实现的变体，Spring AOP构建在动态代理基础之上，因此，Spring对AOP的支持局限于方法拦截。


    | AspectJ指示器       | 描　　述 | 
    | ------------- | ---------------- | 
    | arg()         | 限制连接点匹配参数为指定类型的执行方法  |
    | @args()       | 限制连接点匹配参数由指定注解标注的执行方法 |
    | execution()   | 用于匹配是连接点的执行方法 |
    | this()        | 限制连接点匹配AOP代理的bean引用为指定类型的类 |
    | target        | 限制连接点匹配目标对象为指定类型的类 |
    | @target()     | 限制连接点匹配特定的执行对象，这些对象对应的类要具有指定类型的注解 |
    | within()      | 限制连接点匹配指定的类型 |
    | @within()     | 限制连接点匹配指定注解所标注的类型（当使用Spring AOP时，方法定义在由指定的注解所标注的类里） |
    | @annotation   | 限定匹配带有指定注解的连接点 |
    


    |注　　解                           | 通　　知|
    @After          |通知方法会在目标方法返回或抛出异常后调用  
    @AfterReturning | 通知方法会在目标方法返回后调用  
    @AfterThrowing  | 通知方法会在目标方法抛出异常后调用  
    @Around         | 通知方法会将目标方法封装起来  
    @Before         | 通知方法会在目标方法调用之前执行  
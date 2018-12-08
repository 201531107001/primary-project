标注为FunctionalInterface的接口被称为函数式接口，该接口只能有一个自定义方法，
但是可以包括从object类继承而来的方法。如果一个接口只有一个方法，则编译器会认为这就是一个函数式接口。
是否是一个函数式接口，需要注意的有以下几点：
1.该注解只能标记在”有且仅有一个抽象方法”的接口上。
2.JDK8接口中的静态方法和默认方法，都不算是抽象方法。
3.接口默认继承java.lang.Object，所以如果接口显示声明覆盖了Object中方法，那么也不算抽象方法。
4.该注解不是必须的，如果一个接口符合”函数式接口”定义，那么加不加该注解都没有影响。加上该注解能够更好地让编译器进行检查。如果编写的不是函数式接口，但是加上了@FunctionInterface，那么编译器会报错。
5.在一个接口中定义两个自定义的方法，就会产生Invalid ‘@FunctionalInterface’ annotation; 
  FunctionalInterfaceTest is not a functional interface错误.
  
Function常用方法&&实践
//将Function对象应用到输入的参数上，然后返回计算结果。
    R apply(T t);
//返回一个先执行当前函数对象apply方法再执行after函数对象apply方法的函数对象。
    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
//返回一个先执行before函数对象apply方法再执行当前函数对象apply方法的函数对象
    default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

Supplier<T>: 数据提供器
Function<T,R>: 数据转换器   
    1.BinaryOperator(BinaryOperator<T> extends BiFunction<T,T,T>)
    2.BiFunction(BiFunction<Integer, Integer, Integer>)  
    3.UnaryOperator(UnaryOperator<T> extends Function<T, T>)
Consumer<T>: 数据消费器
Predicate<T>: 条件测试器

方法引用的形式
类型  示例
引用静态方法  ContainingClass::staticMethodName
引用某个对象的实例方法 containingObject::instanceMethodName
引用某个类型的任意对象的实例方法    ContainingType::methodName
引用构造方法  ClassName::new


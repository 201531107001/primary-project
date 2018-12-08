参考网址：https://blog.csdn.net/walkerjong/article/details/50633474
为什么需要 Stream
Stream 作为 Java 8 的一大亮点，它与 java.io 包里的 InputStream 和 OutputStream 
是完全不同的概念。它也不同于 StAX 对 XML 解析的 Stream，也不是 Amazon Kinesis 对大数
据实时处理的 Stream。Java 8 中的 Stream 是对集合（Collection）对象功能的增强，它专注
于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 
(bulk data operation)。Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程
效率和程序可读性。同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，
使用 fork/join 并行方式来拆分任务和加速处理过程。通常编写并行代码很难而且容易出错, 但使用 
Stream API 无需编写一行多线程的代码，就可以很方便地写出高性能的并发程序。所以说，Java 8 
中首次出现的 java.util.stream 是一个函数式语言+多核时代综合影响的产物。

Java 的并行 API 演变历程基本如下：
1.0-1.4 中的 java.lang.Thread
5.0 中的 java.util.concurrent
6.0 中的 Phasers 等
7.0 中的 Fork/Join 框架
8.0 中的 Lambda

当我们使用一个流的时候，通常包括三个基本步骤：
获取一个数据源（source）→ 数据转换→执行操作获取想要的结果，每次转换原有 Stream 对象不改变，
返回一个新的 Stream 对象（可以有多次转换），这就允许对其操作可以像链条一样排列，变成一个管道，

流的操作类型分为两种：

Intermediate：一个流可以后面跟随零个或多个 intermediate 操作。其目的主要是打开流，
    做出某种程度的数据映射/过滤，然后返回一个新的流，交给下一个操作使用。这类操作都是惰性化的（lazy），
    就是说，仅仅调用到这类方法，并没有真正开始流的遍历。
Terminal：一个流只能有一个 terminal 操作，当这个操作执行后，流就被使用“光”了，无法再被操作。
    所以这必定是流的最后一个操作。Terminal 操作的执行，才会真正开始流的遍历，并且会生成一个结果，
    或者一个 side effect。

在对于一个 Stream 进行多次转换操作 (Intermediate 操作)，每次都对 Stream 的每个元素进行转换，
而且是执行多次，这样时间复杂度就是 N（转换次数）个 for 循环里把所有操作都做掉的总和吗？其实不是这
样的，转换操作都是 lazy 的，多个转换操作只会在 Terminal 操作的时候融合起来，一次循环完成。我们
可以这样简单的理解，Stream 里有个操作函数的集合，每次转换操作就是把转换函数放入这个集合中，在 
Terminal 操作的时候循环 Stream 对应的集合，然后对每个元素执行所有的函数。

构造流的几种常见方法
// 1. Individual values
Stream stream = Stream.of("a", "b", "c");
// 2. Arrays
String [] strArray = new String[] {"a", "b", "c"};
stream = Stream.of(strArray);
stream = Arrays.stream(strArray);
// 3. Collections
List<String> list = Arrays.asList(strArray);
stream = list.stream();

数值流的构造
IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println);
IntStream.range(1, 3).forEach(System.out::println);
IntStream.rangeClosed(1, 3).forEach(System.out::println);

流转换为其它数据结构
// 1. Array
String[] strArray1 = stream.toArray(String[]::new);
// 2. Collection
List<String> list1 = stream.collect(Collectors.toList());
List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
Set set1 = stream.collect(Collectors.toSet());
Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
// 3. String
String str = stream.collect(Collectors.joining()).toString();

流的操作
接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。
常见的操作可以归类如下:
1.Intermediate：
map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 
parallel、 sequential、 unordered
2.Terminal：
forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、
 allMatch、 noneMatch、 findFirst、 findAny、 iterator
3.Short-circuiting：
anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

map 生成的是个 1:1 映射，每个输入元素，都按照规则转换成为另外一个元素。
还有一些场景，是一对多映射关系的，这时需要 flatMap。
flatMap 把 input Stream 中的层级结构扁平化，就是将最底层元素抽出来放到一起，最终 output
 的新 Stream 里面已经没有 List 了，都是直接的数字。

reduce:
这个方法的主要作用是把 Stream 元素组合起来。它提供一个起始值（种子），然后依照运算规则
（BinaryOperator），和前面 Stream 的第一个、第二个、第 n 个元素组合。从这个意义上说，
字符串拼接、数值的 sum、min、max、average 都是特殊的 reduce。例如 Stream 的 sum 就相当于
Integer sum = integers.reduce(0, (a, b) -> a+b); 或
Integer sum = integers.reduce(0, Integer::sum);
也有没有起始值的情况，这时会把 Stream 的前面两个元素组合起来，返回的是 Optional。

// 字符串连接，concat = "ABCD"
String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat); 
// 求最小值，minValue = -3.0
double minValue=Stream.of(1.5,1.0,3.0,2.0).reduce(Double.MAX_VALUE,Double::min); 
// 求和，sumValue = 10, 有起始值
int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
// 求和，sumValue = 10, 无起始值
sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
// 过滤，字符串连接，concat = "ace"
concat = Stream.of("a", "B", "c", "D", "e", "F").
 filter(x -> x.compareTo("Z") > 0).
 reduce("", String::concat);
上面代码例如第一个示例的 reduce()，第一个参数（空白字符）即为起始值，第二个参数
（String::concat）为 BinaryOperator。这类有起始值的 reduce() 都返回具体的对象。
而对于第四个示例没有起始值的 reduce()，由于可能没有足够的元素，返回的是 Optional，
请留意这个区别。

limit/skip
limit返回Stream的前面n个元素,skip则是扔掉前n个元素（它是由一个叫 subStream 的方法改名而来）。
limit 和 skip 会对运行次数的影响

sorted
对 Stream 的排序通过 sorted 进行，它比数组的排序更强之处在于你可以首先对 Stream 
进行各类 map、filter、limit、skip 甚至 distinct 来减少元素数量后，再排序，这能
帮助程序明显缩短执行时间。

min/max/distinct
min 和 max 的功能也可以通过对 Stream 元素先排序，再 findFirst 来实现，但前者的性能会更好，
为 O(n)，而 sorted 的成本是 O(n log n)。同时它们作为特殊的 reduce 方法被独立出来也是因为
求最大最小值是很常见的操作。

Match
Stream 有三个 match 方法，从语义上说：
allMatch：Stream 中全部元素符合传入的 predicate，返回 true
anyMatch：Stream 中只要有一个元素符合传入的 predicate，返回 true
noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true

返回由此流的元素组成的流，另外在每个元素上执行提供的操作，因为元素是从结果流中消耗的
Stream<T> peek(Consumer<? super T> action);

返回一个流，该流包含将给定函数应用于此流的元素的结果。
<R> Stream<R> map(Function<? super T, ? extends R> mapper);

返回由与此给定谓词匹配的此流的元素组成的流
Stream<T> filter(Predicate<? super T> predicate);
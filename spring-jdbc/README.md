1.包含了spring jdbc<br>
2.包含了spring 的profile去使用不同的DataSource<br>
3.包含了spring的事务<br>

事务的特性：原子性、一致性、隔离性、持久性

隔离性：https://blog.csdn.net/qingpengshan/article/details/80598366

隔离级别是为了解决以下这些安全问题而提供的解决方案：
脏读：一个事务读取到了另一个事务改写但还未提交的数据，如果这些数据被回滚，则读到的数据是无效的。
不可重复度：在同一个事务里，多次读取同一个数据返回的结果有所不同。
虚读/幻读：一个事务读取了几行记录后，另一个事务插入一些记录，幻读就发生了。再后来的查询中，第一个事务就会发现有些原来没有的记录。

在Spring中，事务属性描述了事务策略如何应用到方法上，事务属性包含5个方面： 
① 传播行为 
② 隔离级别 
③ 回滚策略 
④ 超时时间 
⑤ 是否只读

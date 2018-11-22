package com.me.rocketmq;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

import java.util.Scanner;

public class ProducerTest01 {

	public static void main(String[] args) {

		/**
		 * 一个应用创建一个Producer，由应用来维护此对象，可以设置为全局对象或者单例<br>
		 * 注意：ProducerGroupName需要由应用来保证唯一<br>
		 * ProducerGroup这个概念发送普通的消息时，作用不大，但是发送分布式事务消息时，比较关键，
		 * 因为服务器会回查这个Group下的任意一个Producer
		 * --rocketmq.config.namesrvAddr=192.168.147.128:9876
		 */
		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		// producer.setNamesrvAddr("192.168.180.1:9876");
		producer.setNamesrvAddr("192.168.147.128:9876");
		producer.setInstanceName("Producer");
		/**
		 * Producer对象在使用之前必须要调用start初始化，初始化一次即可<br>
		 * 注意：切记不可以在每次发送消息时，都调用start方法
		 */
		try {
			producer.start();
			System.out.println("producer start");
		} catch (MQClientException e) {
			e.printStackTrace();
		}
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.print("please input your message number(1,2,3):");
			int a = input.nextInt();
			if (a == 0) {
				break;
			}
			try {
				/**
				 * 下面这段代码表明一个Producer对象可以发送多个topic，多个tag的消息。
				 * 注意：send方法是同步调用，只要不抛异常就标识成功。但是发送成功也可会有多种状态，
				 * 例如消息写入Master成功，但是Slave不成功，这种情况消息属于成功，但是对于个别应用如果对消息可靠性要求极高，
				 * 需要对这种情况做处理。另外，消息可能会存在发送失败的情况，失败重试由应用来处理。
				 */
				switch (a) {
					case 1:
					{	Message msg = new Message("TopicTest1", // topic
								"TagA", // tag
								"OrderID001", // key
								("Hello RocketMQ TagA").getBytes());// body
						SendResult sendResult = producer.send(msg);
						System.out.println(sendResult);
					}
						break;
					case 2:
					{		Message msg = new Message("TopicTest2", "TagB", 
								"OrderID001", ("Hello RocketMQ TagB".getBytes()));

						SendResult sendResult = producer.send(msg);
						System.out.println(sendResult);
					}
						break;
					case 3:
					{
						Message msg = new Message("TopicTest3", "TagC", 
								"OrderID001", ("Hello RocketMQ TagC").getBytes());

						SendResult sendResult = producer.send(msg);
						System.out.println(sendResult);
					}
						break;
	
					default:
						break;
				}

			} catch (MQClientException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (RemotingException e) {
				e.printStackTrace();
			} catch (MQBrokerException e) {
				e.printStackTrace();
			}
		}
		/**
		 * 应用退出时，要调用shutdown来清理资源，关闭网络连接，从MetaQ服务器上注销自己
		 * 注意：我们建议应用在JBOSS、Tomcat等容器的退出销毁方法里调用shutdown方法
		 */
		input.close();
		producer.shutdown();
	}
}
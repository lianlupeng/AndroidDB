package org.android.server;

import org.android.model.User;
import org.android.util.DataBaseInfo;
import org.android.util.DataBaseOperation;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitReceive {

	// 改写代码使用direct exchange思想进行操作
	private final static String queueUserName = "user";
	private final static String queueQuery = "query";
	// 定义routing key
	private static String info[] = { "login", "query", "update", "insert",
			"delete" };

	// private final static String QUEUE_NAME3 = "android3";
		private static final String EXCHANGE_NAME = "direct_logs";

		  public static void main(String[] argv) throws Exception {

			for(String test:info)
			{
				System.out.println(test+"---------=================>");
			}
		    ConnectionFactory factory = new ConnectionFactory();
		    factory.setHost("192.168.1.139");
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();

		    channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		    //使用匿名的queue
		    String queueName = channel.queueDeclare().getQueue();
		    System.out.println(queueName+"----------------->");
		  
		    //依次进行绑定
		    for(String severity : info){    
		    	System.out.println(severity+"=========>");
		      channel.queueBind(queueName, EXCHANGE_NAME, severity);
		    }
		   
		    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		    QueueingConsumer consumer = new QueueingConsumer(channel);
		    channel.basicConsume(queueName, true, consumer);

		    while (true) {
		      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		      String message = new String(delivery.getBody());
		      String routingKey = delivery.getEnvelope().getRoutingKey();
		      
		      System.out.println(" [x] Received '" + routingKey + "':'" + message + "'");
		   // 根据不同的routingkey进行不同的操作，但是目前jdk版本不支持string，1.7可以
				if (routingKey.equals("login"))
				{
					System.out.println("kooooooooooooooo");
						// 处理登录操作
						// user
						User user = new User();
						// byte[] flagVerify ;
						DataBaseInfo db = new DataBaseInfo();
						String verify[] = message.split(",");
						user.setUsername(verify[0]);
						user.setUserpwd(verify[1]);
						System.out.println(user.getUsername());
						System.out.println(user.getUserpwd());
						// flagVerify = user.toString().getBytes();
						// db.vefify(user);
						if (db.vefify(user)) 
						{
							System.out.println("ok");
							//创建新的channel用于发送数据
							Connection connection2 = factory.newConnection();
							Channel channel2 = connection2.createChannel();
							channel2.queueDeclare(queueUserName, false, false, false, null);
							channel2.basicPublish("", queueUserName, null, "ok".getBytes());
							//RabbitSend.Send("userResult", "ok");
							System.out.println("信息已发送");
							// 测试query方法的实用性
						} 
						else 
						{
							System.out.println("sorry");
							//channel.basicPublish("", QUEUE_NAME2, null,"sorry".getBytes());
						}
				} 
				else if (routingKey.equals("query")) 
				{
					// 实现查询的操作,当从客户端获取routingKey为query的请求时执行的操作
					System.out.println("start 传输信息");
					System.out.println(DataBaseOperation.Query("student").toString()+"=================================");
					//创建新的channel用于发送数据
					Connection connection2 = factory.newConnection();
					Channel channel2 = connection2.createChannel();
					channel2.queueDeclare(queueQuery, false, false, false, null);
					channel2.basicPublish("", queueQuery, null, DataBaseOperation.Query("student").toString().getBytes());
					System.out.println("ok query sent"); 
				} 
				else if (routingKey.equals("insert"))
				{
					// 实现插入操作
					System.out.println("insert");
				} 
				else if (routingKey.equals("update")) 
				{
					// 实现更新操作
					System.out.println("update");
				}
				else 
				{
					// 实现删除操作
					System.out.println("delete");
				}
		    }
		  }
}
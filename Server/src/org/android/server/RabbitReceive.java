package org.android.server;

import org.android.model.User;
import org.android.util.DataBaseInfo;
import org.android.util.DataBaseOperation;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer;

public class RabbitReceive {

	// ��д����ʹ��direct exchange˼����в���
	private final static String queueUserName = "user";
	private final static String queueQuery = "query";
	// ����routing key
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
		    //ʹ��������queue
		    String queueName = channel.queueDeclare().getQueue();
		    System.out.println(queueName+"----------------->");
		  
		    //���ν��а�
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
		   // ���ݲ�ͬ��routingkey���в�ͬ�Ĳ���������Ŀǰjdk�汾��֧��string��1.7����
				if (routingKey.equals("login"))
				{
					System.out.println("kooooooooooooooo");
						// �����¼����
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
							//�����µ�channel���ڷ�������
							Connection connection2 = factory.newConnection();
							Channel channel2 = connection2.createChannel();
							channel2.queueDeclare(queueUserName, false, false, false, null);
							channel2.basicPublish("", queueUserName, null, "ok".getBytes());
							//RabbitSend.Send("userResult", "ok");
							System.out.println("��Ϣ�ѷ���");
							// ����query������ʵ����
						} 
						else 
						{
							System.out.println("sorry");
							//channel.basicPublish("", QUEUE_NAME2, null,"sorry".getBytes());
						}
				} 
				else if (routingKey.equals("query")) 
				{
					// ʵ�ֲ�ѯ�Ĳ���,���ӿͻ��˻�ȡroutingKeyΪquery������ʱִ�еĲ���
					System.out.println("start ������Ϣ");
					System.out.println(DataBaseOperation.Query("student").toString()+"=================================");
					//�����µ�channel���ڷ�������
					Connection connection2 = factory.newConnection();
					Channel channel2 = connection2.createChannel();
					channel2.queueDeclare(queueQuery, false, false, false, null);
					channel2.basicPublish("", queueQuery, null, DataBaseOperation.Query("student").toString().getBytes());
					System.out.println("ok query sent"); 
				} 
				else if (routingKey.equals("insert"))
				{
					// ʵ�ֲ������
					System.out.println("insert");
				} 
				else if (routingKey.equals("update")) 
				{
					// ʵ�ָ��²���
					System.out.println("update");
				}
				else 
				{
					// ʵ��ɾ������
					System.out.println("delete");
				}
		    }
		  }
}
package org.cloud.util;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

public class RabbitSender {

  private static final String EXCHANGE_NAME = "direct_logs";

  public static void main(String[] argv) throws Exception {

    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.1.139");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, "direct");

    String key = "login";
    String info = "test";
    channel.basicPublish(EXCHANGE_NAME, key, null, info.getBytes());
    System.out.println(" [x] Sent key:'" +key+"   "+"message:"+info);

    channel.close();
    connection.close();
  }
}
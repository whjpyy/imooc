package com.imooc.jms.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Created by youzeng
 * time: 2018-04-20 22:33
 */
public class AppConsumer {
    private static final String url = "tcp://192.168.31.183:61616";
    private static final String queueName = "queue-test";

    public static void main(String[] args) throws JMSException {
        // 1.创建ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // 2.创建Connection
        Connection connection = connectionFactory.createConnection();

        // 3.启动连接
        connection.start();

        // 4.创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5.创建一个目标
        Destination destination = session.createQueue(queueName);

        // 6.创建一个消费者
        MessageConsumer consumer = session.createConsumer(destination);

        // 7.创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接受消息" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 9.关闭连接
//        connection.close();
    }
}

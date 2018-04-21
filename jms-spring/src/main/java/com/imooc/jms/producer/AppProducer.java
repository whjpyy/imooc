package com.imooc.jms.producer;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by youzeng
 * time: 2018-04-20 23:49
 */
public class AppProducer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("producer.xml");
        ProducerService service = context.getBean(ProducerService.class);

        for (int i = 0; i < 100; i++) {
            service.sendMessage("text" + i);
        }

        context.close();
    }
}

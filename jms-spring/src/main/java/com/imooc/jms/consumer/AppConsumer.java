package com.imooc.jms.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by youzeng
 * time: 2018-04-21 00:04
 */
public class AppConsumer {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("consumer.xml");

    }
}

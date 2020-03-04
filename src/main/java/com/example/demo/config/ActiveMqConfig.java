package com.example.demo.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;


/**
 * @program: demo
 * @description:
 * @author: CheGuangQuan
 * @create: 2020-03-04 14:40
 **/
@Configuration
public class ActiveMqConfig {
    /**
     * 发布/订阅模式队列名称
     */
    public static final String TOPIC_NAME = "activemq.topic";
    /**
     * 点对点模式队列名称
     */
    public static final String QUEUE_NAME = "activemq.queue";

    @Bean
    public Destination topic() {
        return new ActiveMQTopic(TOPIC_NAME);
    }

    @Bean
    public Destination queue() {
        return new ActiveMQQueue(QUEUE_NAME);
    }

    @Bean
    public JmsListenerContainerFactory topicListenerContainer(ConnectionFactory connectionFactory){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        // 相当于在 application.yml 中配置 spring.jms.pub-sub-domain=true
        factory.setPubSubDomain(true);
        return factory;
    }
}
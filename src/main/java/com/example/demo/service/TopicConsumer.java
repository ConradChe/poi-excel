package com.example.demo.service;

import com.example.demo.config.ActiveMqConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description:
 * @author: CheGuangQuan
 * @create: 2020-03-04 15:06
 **/
@Service
public class TopicConsumer {

    @JmsListener(destination = ActiveMqConfig.TOPIC_NAME,containerFactory = "topicListenerContainer")
    public void receiveTopicMsg(String msg){
        System.out.println("收到的消息为：" + msg);
    }
}
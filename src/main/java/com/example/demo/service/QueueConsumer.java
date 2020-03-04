package com.example.demo.service;

import com.example.demo.config.ActiveMqConfig;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * @program: demo
 * @description: 消息消费者
 * @author: CheGuangQuan
 * @create: 2020-03-04 14:53
 **/
@Service
public class QueueConsumer {

    @JmsListener(destination = ActiveMqConfig.QUEUE_NAME)
    public void receiveQueueMsg(String msg){
        System.out.println("收到的消息为：" + msg);
    }
}
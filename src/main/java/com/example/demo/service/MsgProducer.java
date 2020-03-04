package com.example.demo.service;

import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @program: demo
 * @description: 消息发送者
 * @author: CheGuangQuan
 * @create: 2020-03-04 14:46
 **/
@Service
public class MsgProducer {
    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, String msg){
        jmsMessagingTemplate.convertAndSend(destination,msg);
    }
}
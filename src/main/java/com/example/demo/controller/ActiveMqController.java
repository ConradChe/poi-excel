package com.example.demo.controller;

import com.example.demo.service.MsgProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @program: demo
 * @description:
 * @author: CheGuangQuan
 * @create: 2020-03-04 14:50
 **/
@RestController
@RequestMapping("/activemq")
public class ActiveMqController {
    @Resource
    private MsgProducer msgProducer;
    @Resource
    private Destination queue;
    @Resource
    private Destination topic;

    @GetMapping("/send/queue")
    public String sendQueueMassage(){
        msgProducer.sendMessage(queue,"Queue: hello activemq!");
        return "success";
    }

    @GetMapping("/send/topic")
    public String sendTopicMessage() {
        msgProducer.sendMessage(topic, "Topic: hello activemq!");
        return "success";
    }
}
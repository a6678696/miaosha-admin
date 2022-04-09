package com.ledao.controller;

import com.ledao.rabbitmq.RabbitMQProducerService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author LeDao
 * @company
 * @create 2022-04-08 16:30
 */
@RestController
@RequestMapping("/rabbitmq")
public class RabbitMQController {

    @Resource
    private RabbitMQProducerService rabbitMQProducerService;

    @RequestMapping("/sendInformation")
    public void sendInformation(String information) {
        rabbitMQProducerService.sendInformation(information);
    }
}

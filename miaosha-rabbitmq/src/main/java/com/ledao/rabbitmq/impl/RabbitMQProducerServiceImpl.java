package com.ledao.rabbitmq.impl;

import com.ledao.config.RabbitMQConfig;
import com.ledao.rabbitmq.RabbitMQProducerService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author LeDao
 * @company
 * @create 2022-04-07 0:58
 */
@Service("rabbitMQProducerService")
public class RabbitMQProducerServiceImpl implements RabbitMQProducerService {

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendInformation(String infotmation) {
        amqpTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTINGKEY, infotmation);
    }

    @Override
    public void sendMessageDelayed(String message, Integer delayTime) {
        amqpTemplate.convertAndSend(RabbitMQConfig.DELAYED_EXCHANGE, RabbitMQConfig.DELAYED_ROUTING_KEY, message, a -> {
            a.getMessageProperties().setDelay(delayTime);
            return a;
        });
    }
}

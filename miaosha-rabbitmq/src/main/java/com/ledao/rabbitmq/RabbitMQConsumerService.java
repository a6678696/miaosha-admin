package com.ledao.rabbitmq;

/**
 * RabbitMQService消费者接口
 *
 * @author LeDao
 * @company
 * @create 2022-04-07 1:07
 */
public interface RabbitMQConsumerService {

    /**
     * 处理秒杀请求
     *
     * @param message
     */
    void handleMiaoShaRequst(String message);
}

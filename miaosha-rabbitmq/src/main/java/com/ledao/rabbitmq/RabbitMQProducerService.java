package com.ledao.rabbitmq;

/**
 * RabbitMQService生产者接口
 *
 * @author LeDao
 * @company
 * @create 2022-04-07 0:57
 */
public interface RabbitMQProducerService {

    /**
     * 发送消息
     *
     * @param infotmation
     */
    void sendInformation(String infotmation);

    /**
     * 发送延时消息
     *
     * @param message 消息内容
     * @param delayTime 延时时间,单位为毫秒
     */
    void sendMessageDelayed(String message, Integer delayTime);
}

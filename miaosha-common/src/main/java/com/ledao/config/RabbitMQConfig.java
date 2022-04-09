package com.ledao.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 *
 * @author LeDao
 * @company
 * @create 2022-04-07 0:54
 */
@Configuration
public class RabbitMQConfig {

    /**
     * direct交换机名称
     */
    public static final String DIRECT_EXCHANGE = "directExchange";

    /**
     * direct队列名称
     */
    public static final String DIRECT_QUEUE = "directQueue";

    /**
     * direct路由key
     */
    public static final String DIRECT_ROUTINGKEY = "directRoutingKey";

    /**
     * 定义一个direct交换机
     *
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    /**
     * 定义一个direct队列
     *
     * @return
     */
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE);
    }

    /**
     * 定义一个队列和交换机的绑定
     *
     * @return
     */
    @Bean
    public Binding directBinding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with(DIRECT_ROUTINGKEY);
    }
}

package com.ledao.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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
     * delayed交换机
     */
    public static final String DELAYED_EXCHANGE = "delayedExchange";

    /**
     * delayed队列
     */
    public static final String DELAYED_QUEUE = "delayedQueue";

    /**
     * delayed路由key
     */
    public static final String DELAYED_ROUTING_KEY = "delayedRoutingKey";

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

    /**
     * 定义delayed交换机
     *
     * @return
     */
    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("x-delayed-type", "direct");
        return new CustomExchange(DELAYED_EXCHANGE, "x-delayed-message", true, false, map);
    }

    /**
     * 定义delayed队列
     *
     * @return
     */
    @Bean
    public Queue delayedQueue() {
        return new Queue(DELAYED_QUEUE);
    }

    /**
     * delayed队列绑定delayed交换机
     *
     * @return
     */
    @Bean
    public Binding delayedBinding() {
        return BindingBuilder.bind(delayedQueue()).to(delayedExchange()).with(DELAYED_ROUTING_KEY).noargs();
    }
}

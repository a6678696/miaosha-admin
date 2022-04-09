package com.ledao.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author LeDao
 * @company
 * @create 2022-04-08 16:33
 */
@FeignClient("miaosha-rabbitmq")
public interface RabbitMQFeignService {

    /**
     * 调用miaosha-rabbitmq微服务的/rabbitmq/sendInformation
     *
     * @param information
     */
    @RequestMapping("/rabbitmq/sendInformation")
    void sendInformation(@RequestParam("information") String information);
}

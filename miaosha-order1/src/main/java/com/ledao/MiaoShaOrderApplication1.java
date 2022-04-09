package com.ledao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author LeDao
 * @company
 * @create 2022-04-08 14:48
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ledao.mapper")
@EnableFeignClients(basePackages = "com.ledao.feign")
public class MiaoShaOrderApplication1 {

    public static void main(String[] args) {
        SpringApplication.run(MiaoShaOrderApplication1.class, args);
    }
}

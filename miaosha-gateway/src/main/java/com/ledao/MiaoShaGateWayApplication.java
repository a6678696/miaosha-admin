package com.ledao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LeDao
 * @company
 * @create 2022-04-09 1:26
 */
@EnableDiscoveryClient
@SpringBootApplication
public class MiaoShaGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoShaGateWayApplication.class, args);
    }
}

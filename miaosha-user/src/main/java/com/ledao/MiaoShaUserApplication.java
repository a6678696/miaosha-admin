package com.ledao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LeDao
 * @company
 * @create 2022-04-07 7:37
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.ledao.mapper")
public class MiaoShaUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiaoShaUserApplication.class, args);
    }
}

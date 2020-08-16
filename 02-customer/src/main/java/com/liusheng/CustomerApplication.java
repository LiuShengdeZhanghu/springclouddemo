package com.liusheng;

import com.netflix.loadbalancer.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class CustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    @Bean
    @LoadBalanced   // 这个把RestTemplate和Ribbon整合到了一起
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

//    @Bean
//    public IRule ribbonIRule(){
//        // 随机访问的策略
//        return new RandomRule();
//    }
}

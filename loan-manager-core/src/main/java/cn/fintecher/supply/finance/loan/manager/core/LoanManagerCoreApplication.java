package cn.fintecher.supply.finance.loan.manager.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
//启用注册到注册中心
@EnableFeignClients
@EnableEurekaClient
public class LoanManagerCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanManagerCoreApplication.class, args);
    }
}

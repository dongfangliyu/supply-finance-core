package cn.fintecher.supply.finance.loan.manager.pm.bff;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class LoanManagerPmBffApplication {
    public static void main(String[] args) {
        SpringApplication.run(LoanManagerPmBffApplication.class, args);
    }
}

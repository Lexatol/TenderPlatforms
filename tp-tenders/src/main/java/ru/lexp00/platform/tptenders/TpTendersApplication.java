package ru.lexp00.platform.tptenders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TpTendersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpTendersApplication.class, args);
    }

}

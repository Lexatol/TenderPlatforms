package ru.lexp00.platform.tpspecialization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TpSpecializationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpSpecializationApplication.class, args);
    }

}

package ru.lexp00.platform.tpusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TpUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpUsersApplication.class, args);
    }

}

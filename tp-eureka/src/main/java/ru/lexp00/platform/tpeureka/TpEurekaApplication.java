package ru.lexp00.platform.tpeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TpEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpEurekaApplication.class, args);
    }

}

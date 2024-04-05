package ru.lexp00.platform.tpserverconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class TpServerConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpServerConfigApplication.class, args);
    }

}

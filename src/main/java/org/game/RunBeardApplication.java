package org.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RunBeardApplication {

    public static void main(String[] args) {
        SpringApplication.run(RunBeardApplication.class, args);
    }

}


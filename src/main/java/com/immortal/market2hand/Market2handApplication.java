package com.immortal.market2hand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ServletComponentScan
@EntityScan("com.immortal.market2hand.entity")
public class Market2handApplication {

    public static void main(String[] args) {
        SpringApplication.run(Market2handApplication.class, args);
    }

}

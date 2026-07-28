package com.gymflow.gymflow;

import com.gymflow.gymflow.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class GymFlowApplication {

    static void main(String[] args) {
        SpringApplication.run(GymFlowApplication.class, args);
    }

}

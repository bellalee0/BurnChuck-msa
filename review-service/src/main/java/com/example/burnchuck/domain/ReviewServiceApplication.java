package com.example.burnchuck.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.example.burnchuck")
@EntityScan(basePackages = "com.example.burnchuck.common.entity")
@EnableJpaRepositories(basePackages = {
    "com.example.burnchuck.domain.review.repository",
    "com.example.burnchuck.domain.reaction.repository"
})
public class ReviewServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReviewServiceApplication.class, args);
    }
}

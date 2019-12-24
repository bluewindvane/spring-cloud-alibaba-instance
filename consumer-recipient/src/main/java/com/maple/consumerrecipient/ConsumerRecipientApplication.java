package com.maple.consumerrecipient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerRecipientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerRecipientApplication.class, args);
    }

}

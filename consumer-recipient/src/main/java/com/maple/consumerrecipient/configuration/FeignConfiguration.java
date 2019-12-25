package com.maple.consumerrecipient.configuration;

import com.maple.consumerrecipient.feign.fallback.ServiceProviderFeignFallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class FeignConfiguration {

    @Bean
    public ServiceProviderFeignFallback echoServiceFallback() {
        return new ServiceProviderFeignFallback();
    }

}

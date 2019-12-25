package com.maple.consumerrecipient.feign;

import com.maple.consumerrecipient.configuration.FeignConfiguration;
import com.maple.consumerrecipient.feign.fallback.ServiceProviderFeignFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-provider", fallback = ServiceProviderFeignFallback.class, configuration = FeignConfiguration.class)
public interface ServiceProviderFeign {

    @GetMapping("/configInfo/getAppName")
    String getAppName();

    @GetMapping("/configInfo/getAppId")
    String getAppId();

}

package com.maple.consumerrecipient.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "service-provider")
public interface ServiceProviderFeign {

    @GetMapping("/configInfo/getAppName")
    String getAppName();

}

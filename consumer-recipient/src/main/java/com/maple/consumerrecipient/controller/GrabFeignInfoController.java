package com.maple.consumerrecipient.controller;

import com.maple.consumerrecipient.feign.ServiceProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 * 获取feign返回的json信息
 */
@RestController
@RequestMapping("/grabFeignInfo")
public class GrabFeignInfoController {

    @Autowired
    private ServiceProviderFeign serviceProviderFeign;

    @GetMapping("/getServiceProviderAppName")
    public String getServiceProviderAppName() throws InterruptedException {
        return serviceProviderFeign.getAppName();
    }

    @GetMapping("/getServiceProviderAppId")
    public String getServiceProviderAppId() throws InterruptedException {
        return serviceProviderFeign.getAppId();
    }

}

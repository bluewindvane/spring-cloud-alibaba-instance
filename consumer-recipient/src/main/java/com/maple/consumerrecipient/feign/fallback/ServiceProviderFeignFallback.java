package com.maple.consumerrecipient.feign.fallback;

import com.maple.consumerrecipient.feign.ServiceProviderFeign;

public class ServiceProviderFeignFallback implements ServiceProviderFeign {

    @Override
    public String getAppName() {
        return "服务商的getAppName服务没了";
    }

    @Override
    public String getAppId() {
        return "服务商的getAppId服务没了";
    }

}

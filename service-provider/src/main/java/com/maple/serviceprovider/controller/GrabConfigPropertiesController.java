package com.maple.serviceprovider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 * 获取nacos配置文件信息
 */
@RestController
@RequestMapping("/configInfo")
public class GrabConfigPropertiesController {

    @Value("${myInfo.app}")
    private String appName;

    @GetMapping("/getAppName")
    public String getAppName() {
        return appName;
    }

    @GetMapping("/getAppId")
    public String getAppId() {
        return "sadasd123jsdkas";
    }

}

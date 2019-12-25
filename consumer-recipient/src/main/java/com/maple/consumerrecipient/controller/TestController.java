package com.maple.consumerrecipient.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping("/testSentinelResource")
    @SentinelResource(value = "/test/testSentinelResource", blockHandler = "exceptionHandler")
    public String testSentinelResource() throws Exception {
        Random random = new Random();
        int a = random.nextInt(50);
        log.info("a : " + a);
//        if (a > 10) {
//            throw new Exception("500");
//        }
        return "success";
    }

    // 限流与阻塞处理
    public String exceptionHandler(String str, FlowException ex) {
        log.error("blockHandler：" + str, ex);
        return "流控出错了";
    }

}

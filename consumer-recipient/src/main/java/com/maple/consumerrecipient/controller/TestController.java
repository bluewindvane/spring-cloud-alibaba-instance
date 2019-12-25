package com.maple.consumerrecipient.controller;

import com.alibaba.csp.sentinel.adapter.servlet.callback.WebCallbackManager;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.maple.consumerrecipient.configuration.CustomUrlBlockHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @PostConstruct
    public void init(){
        WebCallbackManager.setUrlBlockHandler(new CustomUrlBlockHandler());
    }


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
    public String exceptionHandler(BlockException ex) {
        log.error("blockHandler：" + ex);
        return "流控出错了";
    }

}

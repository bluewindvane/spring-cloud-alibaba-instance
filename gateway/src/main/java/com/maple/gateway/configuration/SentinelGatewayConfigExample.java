//package com.maple.gateway.configuration;
//
//import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
//import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
//import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
//import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
//import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
//import com.alibaba.csp.sentinel.slots.block.RuleConstant;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.codec.ServerCodecConfigurer;
//import org.springframework.web.reactive.result.view.ViewResolver;
//
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * 这个是例子
// */
//@Configuration
//public class SentinelGatewayConfigExample {
//
//    private final List<ViewResolver> viewResolvers;
//
//    private final ServerCodecConfigurer serverCodecConfigurer;
//
//    public SentinelGatewayConfigExample(ObjectProvider<List<ViewResolver>> viewResolversProvider,
//                                        ServerCodecConfigurer serverCodecConfigurer) {
//        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
//        this.serverCodecConfigurer = serverCodecConfigurer;
//    }
//
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
//        // Register the block exception handler for Spring Cloud Gateway.
//        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
//    }
//
//    /**
//     * 这里注入bean控制台提示已经被注册过了，所以在这里换一个名称注入
//     * @return
//     */
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    @Qualifier("mySentinelGatewayFilter")
//    public GlobalFilter mySentinelGatewayFilter() {
//        return new SentinelGatewayFilter();
//    }
//
//    @PostConstruct
//    public void doInit() {
//        initCustomizedApis();
//        initGatewayRules();
//    }
//
//    /**
//     * 这个方法内定义some_customized_api和another_customized_api为自定义资源名，这个资源名下方配置了规则能生效的url地址
//     * 然后在initGatewayRules方法中定义资源名生效的规则
//     * 也可以不使用自定义资源名，直接使用网关id进行规则定义，不需要再自定义资源名调用loadApiDefinitions方法
//     * 具体参数配置详情见 https://github.com/alibaba/Sentinel/wiki/%E7%BD%91%E5%85%B3%E9%99%90%E6%B5%81
//     */
//    private void initCustomizedApis() {
//        Set<ApiDefinition> definitions = new HashSet<>();
//        ApiDefinition api1 = new ApiDefinition("some_customized_api")
//                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
//                    add(new ApiPathPredicateItem().setPattern("/ahas"));
//                    add(new ApiPathPredicateItem().setPattern("/product/**")
//                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//                }});
//        ApiDefinition api2 = new ApiDefinition("another_customized_api")
//                .setPredicateItems(new HashSet<ApiPredicateItem>() {{
//                    add(new ApiPathPredicateItem().setPattern("/**")
//                            .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX));
//                }});
//        definitions.add(api1);
//        definitions.add(api2);
//        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
//    }
//
//    private void initGatewayRules() {
//        Set<GatewayFlowRule> rules = new HashSet<>();
//        rules.add(new GatewayFlowRule("aliyun_route")
//                .setCount(10)
//                .setIntervalSec(1)
//        );
//        rules.add(new GatewayFlowRule("aliyun_route")
//                .setCount(2)
//                .setIntervalSec(2)
//                .setBurst(2)
//                .setParamItem(new GatewayParamFlowItem()
//                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
//                )
//        );
//        rules.add(new GatewayFlowRule("httpbin_route")
//                .setCount(10)
//                .setIntervalSec(1)
//                .setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_RATE_LIMITER)
//                .setMaxQueueingTimeoutMs(600)
//                .setParamItem(new GatewayParamFlowItem()
//                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_HEADER)
//                        .setFieldName("X-Sentinel-Flag")
//                )
//        );
//        rules.add(new GatewayFlowRule("httpbin_route")
//                .setCount(1)
//                .setIntervalSec(1)
//                .setParamItem(new GatewayParamFlowItem()
//                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
//                        .setFieldName("pa")
//                )
//        );
//        rules.add(new GatewayFlowRule("httpbin_route")
//                .setCount(2)
//                .setIntervalSec(30)
//                .setParamItem(new GatewayParamFlowItem()
//                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
//                        .setFieldName("type")
//                        .setPattern("warn")
//                        .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_CONTAINS)
//                )
//        );
//
//        rules.add(new GatewayFlowRule("some_customized_api")
//                .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
//                .setCount(5)
//                .setIntervalSec(1)
//                .setParamItem(new GatewayParamFlowItem()
//                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
//                        .setFieldName("pn")
//                )
//        );
//        GatewayRuleManager.loadRules(rules);
//    }
//
//}

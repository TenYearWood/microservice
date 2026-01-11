package com.csii.springcloud.config;

import com.csii.springcloud.filter.TokenFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 我们现在配置的是YML进行配置的，还有一种配置方案就是通过硬编码的方式。
 * 就是代码中注入RouteLocator的Bean，是为了解决YML文件配置太多，文件太大的问题。
 * 我们只要演示通过9527网关访问到外网的百度新闻网址。
 * <p>
 * 配置了一个id为route-name的路由规则
 * 当访问地址http://localhost:9527/disk/main时会自动转发到https://pan.baidu.com/disk/main
 */
@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("patn_route_buba", r -> r.path("/disk/main").uri("https://pan.baidu.com/disk/main")).build();
        return routes.build();
    }

    @Bean
    public TokenFilter tokenFilter(){
        return new TokenFilter();
    }
}

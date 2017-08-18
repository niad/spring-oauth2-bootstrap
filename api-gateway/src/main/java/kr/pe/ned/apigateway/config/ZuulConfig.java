package kr.pe.ned.apigateway.config;

import kr.pe.ned.apigateway.filter.zuul.ApiRequestDecorator;
import kr.pe.ned.apigateway.filter.zuul.ForwardHeaderFilter;
import kr.pe.ned.apigateway.filter.zuul.SimpleFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZuulConfig {

    @Bean
    public SimpleFilter simpleFilter() {
        return new SimpleFilter();
    }

    @Bean
    public ApiRequestDecorator apiRequestDecorator() {
        return new ApiRequestDecorator();
    }

    @Bean
    public ForwardHeaderFilter forwardHeaderFilter() {
        return new ForwardHeaderFilter();
    }

}

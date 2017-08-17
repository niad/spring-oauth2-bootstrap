package kr.pe.ned.apigateway;


import kr.pe.ned.apigateway.filter.ApiRequestDecorator;
import kr.pe.ned.apigateway.filter.ForwardHeaderFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableZuulProxy
@EnableOAuth2Sso
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
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

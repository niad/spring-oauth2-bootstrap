package kr.pe.ned.uiserver;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class UiServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(UiServerApplication.class , args);

    }

}

package kr.pe.ned.apigateway.config;

import com.netflix.ribbon.proxy.annotation.Http;
import kr.pe.ned.apigateway.filter.ApiTokenAccessFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenServices;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index.html", "/home.html", "/", "/login").permitAll()
                .anyRequest().authenticated()
                .antMatchers(HttpMethod.POST).authenticated()
                .and()
                .logout()
                .and()
                .addFilterBefore(new ApiTokenAccessFilter(tokenServices), AbstractPreAuthenticatedProcessingFilter.class);
        // @formatter:on
    }


//    @Bean
//    protected OAuth2RestTemplate OAuth2RestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
//        return new OAuth2RestTemplate(resource, context);
//    }

}

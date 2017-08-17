package kr.pe.ned.authserver.config;

import kr.pe.ned.authserver.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@Order(-20)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(customLogoutSuccessHandler)
                .and()
                .requestMatchers().antMatchers("/login", "/logout", "/oauth/authorize", "/oauth/confirm_access")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();
        // @formatter:on
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/webjars/**", "/images/**");
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("user1").roles("USER")
                .and()
                .withUser("seller").password("seller1").roles("SELLER")
                .and()
                .withUser("buyer").password("buyer1").roles("BUYER");
    }
    
}

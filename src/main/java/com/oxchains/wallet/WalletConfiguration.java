package com.oxchains.wallet;

import com.oxchains.wallet.auth.AuthError;
import com.oxchains.wallet.auth.JwtAuthenticationProvider;
import com.oxchains.wallet.auth.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author huohuo
 */
@EnableWebSecurity
@Configuration
public class WalletConfiguration extends WebSecurityConfigurerAdapter {

    public WalletConfiguration(@Autowired JwtTokenFilter jwtTokenFilter, @Autowired JwtAuthenticationProvider jwtAuthenticationProvider, @Autowired AuthError authError) {
    }
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
          .cors()
          .and()
          .csrf()
          .disable()
          .authorizeRequests()
          .antMatchers("/**/*","/**/*/*").permitAll()
          .and()
          .exceptionHandling();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    }
    /**
     * allow cross origin requests
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                        .addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("GET", "POST", "PUT", "OPTIONS", "DELETE")
                        .allowedHeaders("*");
            }
        };
    }
}

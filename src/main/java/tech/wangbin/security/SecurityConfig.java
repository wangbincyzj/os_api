package tech.wangbin.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig {



  @Bean
  public TokenInterceptor tokenInterceptor() {
    return new TokenInterceptor();
  }
}

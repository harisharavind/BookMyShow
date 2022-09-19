package com.app.ticket.book.configuration;

import com.app.ticket.book.aspects.MDCTaskDecorator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableAsync
public class BookMyShowBeans {

    @Autowired BookMyShowProperties properties;

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConFactory
                = new JedisConnectionFactory();
        jedisConFactory.setHostName(properties.getRedisConfig().getUrl());
        jedisConFactory.setPort(properties.getRedisConfig().getPort());
        jedisConFactory.setPassword(properties.getRedisConfig().getSecret());
        return jedisConFactory;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                List<String> whitelistURLs = properties.getCorsConfiguration().getWhitelistURLs();
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins(whitelistURLs.toArray(new String[whitelistURLs.size()]));
            }
        };
    }

    @Bean
    TaskDecorator mdcCopyTaskDecorator() {
        return new MDCTaskDecorator();
    }

}

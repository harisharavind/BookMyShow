package com.app.ticket.book.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "bookmyshow-configuration")
@Data
public class BookMyShowProperties {
    private RedisConfig redisConfig;
    private CorsConfiguration corsConfiguration;

    @Data
    public static class CorsConfiguration {
        private List<String> whitelistURLs;
    }

    @Data
    public static class RedisConfig {
        private String url;
        private int port;
        private String secret;
    }

}

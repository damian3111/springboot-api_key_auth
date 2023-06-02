package com.example.springgatewaysecurity.config;

import com.example.springgatewaysecurity.util.AppConstants;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

@AllArgsConstructor
@Configuration
public class RedisConfig {

    private final Environment env;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(
                Objects.requireNonNull(env.getProperty("spring.redis.host")),
                Integer.parseInt(Objects.requireNonNull(env.getProperty("spring.redis.port"))));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route(AppConstants.STUDENT_SERVICE_KEY, r->r.path("/api/student-service/**")
                        .filters(f -> f.stripPrefix(2)).uri("http://localhost:8081"))
                .route(AppConstants.COURSE_SERVICE_KEY, r->r.path("/api/course-service/**")
                        .filters(f -> f.stripPrefix(2)).uri("http://localhost:8082")).build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setEnableTransactionSupport(true);
        return template;
    }
}

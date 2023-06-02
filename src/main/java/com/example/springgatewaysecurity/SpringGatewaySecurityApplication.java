package com.example.springgatewaysecurity;

import com.example.springgatewaysecurity.config.RedisHashComponent;
import com.example.springgatewaysecurity.dto.ApiKey;
import com.example.springgatewaysecurity.util.AppConstants;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@SpringBootApplication
public class SpringGatewaySecurityApplication {

	private final RedisHashComponent redisHashComponent;

	@PostConstruct
	public void initKeysToRedis(){
		List<ApiKey> apiKeys = new ArrayList<>();
		apiKeys.add(new ApiKey("343C-ED0B-4137-B27E", Stream.of(AppConstants.STUDENT_SERVICE_KEY,
				AppConstants.COURSE_SERVICE_KEY).collect(Collectors.toList())));
		apiKeys.add(new ApiKey("FA48-EF0C-427E-8CCF", Stream.of(AppConstants.COURSE_SERVICE_KEY)
				.collect(Collectors.toList())));
		List<Object> lists = redisHashComponent.hValues(AppConstants.RECORD_KEY);
		if (lists.isEmpty()) {
			apiKeys.forEach(k -> redisHashComponent.hSet(AppConstants.RECORD_KEY, k.getKey(), k));
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewaySecurityApplication.class, args);
	}

}

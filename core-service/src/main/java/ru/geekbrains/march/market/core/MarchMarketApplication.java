package ru.geekbrains.march.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MarchMarketApplication {

	//2. Продолжить распылять на микросервисы
	//3. Добавить фильтрацию товаров по цене и названию

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketApplication.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}

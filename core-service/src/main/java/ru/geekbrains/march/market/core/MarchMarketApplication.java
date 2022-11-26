package ru.geekbrains.march.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MarchMarketApplication {

	//Добавить фильтрацию товаров по цене и названию
	//TODO

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketApplication.class, args);
	}

}

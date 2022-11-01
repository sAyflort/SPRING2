package ru.geekbrains.march.market.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MarchMarketCartApplication {
	public static void main(String[] args) {
		SpringApplication.run(MarchMarketCartApplication.class, args);
	}
}

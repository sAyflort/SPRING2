package ru.geekbrains.march.market.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarchMarketApplication {

	// 2) Добавьте проброс списка ролей в заголовках, и для юзеров с ролью "админ", покажите на фронте в верхнем меню
	//какую-нибудь доп кнопку "админка"
	// 3) Юзер с ролью админ, может постучать в ендпоинт /admin в core-service и получить в ответ сообщение JSON {"value": "admin"} (StringResponse)

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketApplication.class, args);
	}
}
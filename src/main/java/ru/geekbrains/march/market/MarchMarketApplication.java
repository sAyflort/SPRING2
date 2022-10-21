package ru.geekbrains.march.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarchMarketApplication {

	//1) На фронте добавить в отделе корзины кнопку оформить заказ
	//1.а) С фронта передать адрес и телефон покупателя
	//2) Заказ может оформлять толькол вошедший пользователь (у3становите защиту)
	//3) В ордерСервисе получаете текущую корзину и преобразуете ее к заказу
	//4) Сохраняйте заказ с позициями заказа!! В БД
	//5) ** На фронте можете показать список заказов юзера

	public static void main(String[] args) {
		SpringApplication.run(MarchMarketApplication.class, args);
	}

}

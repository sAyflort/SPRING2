package ru.geekbrains.march.market.api;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String confirmPassword;
    private String email;
}
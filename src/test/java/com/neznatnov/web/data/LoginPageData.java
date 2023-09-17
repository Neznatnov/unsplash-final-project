package com.neznatnov.web.data;

import com.github.javafaker.Faker;

public class LoginPageData {
    public static final String ERROR_LOGIN_TEXT = "Invalid email or password.";
    private Faker faker = new Faker();
    public String email = faker.internet().emailAddress();
    public String password = faker.internet().password(6, 10);
}

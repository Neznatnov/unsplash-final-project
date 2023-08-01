package com.neznatnov.web.pages.loginPage;

import com.github.javafaker.Faker;

public class TestDataLoginPage {
    Faker faker = new Faker();

    String email = faker.internet().emailAddress();
    String password = faker.internet().password(6, 10);
}

package tests;


import client.Client;
import courier.Courier;
import courier.ApiCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.is;

public class TestCreateNewCourierWithoutPassword {
    static Integer id;
    Courier courier = new Courier(randomAlphabetic(5), "", randomAlphabetic(5));
    ApiCourier apiCourier = new ApiCourier();

    @Before
    public void setUp() {
        RestAssured.baseURI = Client.BASE_URI;
    }
    @Test
    @DisplayName("Нельзя зарегистрировать курьера без пароля")
    @Description("Проверка, что появится ошибка при попытке создания курьера без заполнения пароля")
    public void regCourierWithoutPasswordTest() {
        ValidatableResponse response = apiCourier.courierReg(courier);
        response.statusCode(SC_BAD_REQUEST)
                .and().assertThat().body("message", is("Недостаточно данных для создания учетной записи"));
    }
    @After
    public void tearDown() {
        apiCourier.courierDeleteWithoutPassword(courier);
        apiCourier.checkCourierDeletedWithoutPassword(id);
    }
}


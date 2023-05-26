package tests;

import client.Client;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import order.Order;
import order.ApiOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class TestCreateOrder {
    private final List<String> color;
    ApiOrder apiOrder = new ApiOrder();

    public TestCreateOrder(List<String> color) {
        this.color = color;
    }

    @Parameterized.Parameters(name = "{index}: Заказ с цветом: {0}")
    public static Object[][] checkCreateOrderWithChoiceColor() {
        return new Object[][]{
                {List.of("GREY")},
                {List.of("BLACK")},
                {List.of("GREY", "BLACK")},
                {List.of()},
        };
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = Client.BASE_URI;
    }

    @Test
    @DisplayName("Создание заказа с выбором цвета")
    @Description("Проверка, что можно создать заказ на: серый цвет, черный цвет, выбор двух цветов, без выбора цвета")
    public void paramCreateOrderTest() {
        Order order = new Order("Семён", "Тапочкин", "Суздальская, 26", "5", "79257775511", 2, "2023-06-10", "Доставить самокат", color);
        ValidatableResponse response = apiOrder.createOrder(order);
        response.assertThat().statusCode(SC_CREATED).and().body("track", notNullValue());
    }
}

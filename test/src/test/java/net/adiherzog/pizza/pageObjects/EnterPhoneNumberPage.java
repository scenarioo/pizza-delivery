package net.adiherzog.pizza.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;

public class EnterPhoneNumberPage extends BasePage {

    private static final String KNOWN_PHONE_NUMBER = "0791111111";
    private static final String UNKNOWN_PHONE_NUMBER = "0792222222";

    public static void navigateToPage() {
        getWebDriver().get("http://scenarioo.github.io/pizza-delivery/prod/index.html");
    }

    public static void enterKnownPhoneNumber() {
        enterPhoneNumber(KNOWN_PHONE_NUMBER);
    }

    public static void enterUnknownPhoneNumber() {
        enterPhoneNumber(UNKNOWN_PHONE_NUMBER);
    }

    public static void clickNext() {
        getWebDriver().findElement(By.id("step-enterPhoneNumber")).findElement(By.className("next")).click();
    }

    private static void enterPhoneNumber(String phoneNumber) {
        getWebDriver().findElement(By.id("phoneNumber")).sendKeys(phoneNumber);
    }

    public static void assertPhoneNumberOfHotPizzaDeliveryIsDisplayed() {
        Assert.assertTrue(getWebDriver().findElement(By.id("hot-pizza-delivery-phone-number")).isDisplayed());
    }

}

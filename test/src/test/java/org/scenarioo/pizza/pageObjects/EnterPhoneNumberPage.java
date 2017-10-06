package org.scenarioo.pizza.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;

import java.net.URL;

import static org.junit.Assert.fail;

public class EnterPhoneNumberPage extends BasePage {

    private static final String KNOWN_PHONE_NUMBER = "0791111111";
    private static final String UNKNOWN_PHONE_NUMBER = "0792222222";

    public static void navigateToPage() {
        URL resource = getUrl();
        getWebDriver().get(resource.toString());
    }

    private static URL getUrl() {
        ClassLoader classLoader = EnterPhoneNumberPage.class.getClassLoader();
        URL resource = classLoader.getResource("index.html");
        if (resource == null) {
            fail("Page not found in resources.");
        }
        return resource;
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

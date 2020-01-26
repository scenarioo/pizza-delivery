package org.scenarioo.pizza.pageObjects;

import org.openqa.selenium.By;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class EnterPhoneNumberPage extends BasePage {

    private static final String KNOWN_PHONE_NUMBER = "0791111111";
    private static final String UNKNOWN_PHONE_NUMBER = "0792222222";

    public static void navigateToPage() {
        URL resource = getUrl();
        getWebDriver().get(resource.toString());
    }

    private static URL getUrl() {
        ClassLoader classLoader = EnterPhoneNumberPage.class.getClassLoader();
        File file = new File("docs/pizza-delivery-shop/index.html");
        URL resource = null;
        try {
            resource = file.toURI().toURL();
        } catch (MalformedURLException e) {
            fail("Malformed URL: " + e.getMessage());
        }
        if (resource == null) {
            fail("Webpage resource not found.");
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
        assertTrue(getWebDriver().findElement(By.id("hot-pizza-delivery-phone-number")).isDisplayed(), "Expected page to be displayed");
    }

}

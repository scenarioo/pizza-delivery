package org.scenarioo.pizza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class ConfirmationPage extends BasePage {

    public static void assertConfirmationPageIsDisplayed() {
        assertTrue(getStepElement().isDisplayed(), "Expect page to be displayed");
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-confirmation"));
    }

}

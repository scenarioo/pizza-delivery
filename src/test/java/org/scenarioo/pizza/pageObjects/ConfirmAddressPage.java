package org.scenarioo.pizza.pageObjects;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ConfirmAddressPage extends BasePage {

    public static void assertPageIsShown() {
        Assertions.assertTrue(getStepElement().isDisplayed(), "Expecting page to be displayed");
    }

    public static void clickYes() {
        getStepElement().findElement(By.className("yes")).click();
    }

    public static void clickNo() {
        getStepElement().findElement(By.className("no")).click();
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-confirmAddress"));
    }

}

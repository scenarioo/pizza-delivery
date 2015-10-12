package net.adiherzog.pizza.pageObjects;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmationPage extends BasePage {

    public static void assertConfirmationPageIsDisplayed() {
        Assert.assertTrue(getStepElement().isDisplayed());
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-confirmation"));
    }

}

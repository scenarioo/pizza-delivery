package net.adiherzog.pizza.pageObjects;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {

    public static void assertConfirmationPageIsDisplayed() {
        boolean isStepFourDisplayed = WebDriverHolder.INSTANCE.getWebDriver().findElement(By.id("step4")).isDisplayed();
        Assert.assertTrue(isStepFourDisplayed);
    }

}

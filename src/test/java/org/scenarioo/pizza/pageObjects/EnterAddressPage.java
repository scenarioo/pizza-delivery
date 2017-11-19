package org.scenarioo.pizza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class EnterAddressPage extends BasePage {

    public static void assertPageIsShown() {
        assertTrue(getStepElement().isDisplayed(), "Expected page to be displayed");
    }

    public static void enterAddress() {
        WebDriver webDriver = getWebDriver();
        webDriver.findElement(By.id("streetAndHouseNumber")).sendKeys("Universitaetstrasse 9145");
        webDriver.findElement(By.id("zipCodeAndCity")).sendKeys("8006 Zuerich");
    }

    public static void clickNext() {
        getStepElement().findElement(By.className("next")).click();
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-enterAddress"));
    }

}

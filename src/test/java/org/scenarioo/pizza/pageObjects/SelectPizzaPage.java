package org.scenarioo.pizza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class SelectPizzaPage extends BasePage {

    public static void assertPageIsShown() {
        assertTrue(getStepElement().isDisplayed(), "Expected page to be displayed");
    }

    public static void selectPizzaVerdura() {
        getWebDriver().findElement(By.id("pizza-verdura")).click();
    }

    public static void clickNextButton() {
        getStepElement().findElement(By.className("next")).click();
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-selectPizza"));
    }

}

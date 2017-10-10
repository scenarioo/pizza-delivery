package org.scenarioo.pizza.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SelectPizzaPage extends BasePage {

    public static void assertPageIsShown() {
        Assert.assertTrue(getStepElement().isDisplayed());
    }

    public static void selectPizzaVerdura() {
        getWebDriver().findElement(By.id("v")).click();
    }

    public static void clickNextButton() {
        getStepElement().findElement(By.className("next")).click();
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-selectPizza"));
    }

}

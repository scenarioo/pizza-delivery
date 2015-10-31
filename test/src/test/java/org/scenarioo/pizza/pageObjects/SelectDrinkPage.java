package org.scenarioo.pizza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SelectDrinkPage extends BasePage {

    public static void selectDrinkRedWine() {
        getWebDriver().findElement(By.id("dv")).click();
    }

    public static void clickNext() {
        getStepElement().findElement(By.className("next")).click();
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-selectDrinks"));
    }

}

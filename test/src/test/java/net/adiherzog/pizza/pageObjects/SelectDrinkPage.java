package net.adiherzog.pizza.pageObjects;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import org.openqa.selenium.By;

public class SelectDrinkPage {

    public static void selectDrinkRedWine() {
        WebDriverHolder.INSTANCE.getWebDriver().findElement(By.id("dv")).click();
    }

    public static void clickNext() {
        WebDriverHolder.INSTANCE.getWebDriver().findElement(By.id("drink_next")).click();
    }

}

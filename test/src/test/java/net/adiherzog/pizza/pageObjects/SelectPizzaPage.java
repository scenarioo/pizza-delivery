package net.adiherzog.pizza.pageObjects;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import org.openqa.selenium.By;

public class SelectPizzaPage {

    public static void navigateToPage() {
        WebDriverHolder.INSTANCE.getWebDriver().get("http://htmlpreview.github.io/?https://github.com/adiherzog/pizza-delivery/blob/master/prod/index.html");
    }

    public static void selectPizzaVerdura() {
        WebDriverHolder.INSTANCE.getWebDriver().findElement(By.id("v")).click();
    }

    public static void clickNextButton() {
        WebDriverHolder.INSTANCE.getWebDriver().findElement(By.id("pizza_next")).click();
    }

}

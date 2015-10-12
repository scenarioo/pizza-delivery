package net.adiherzog.pizza.pageObjects;

import net.adiherzog.pizza.selenium.WebDriverHolder;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public static WebDriver getWebDriver() {
        return WebDriverHolder.INSTANCE.getWebDriver();
    }

}

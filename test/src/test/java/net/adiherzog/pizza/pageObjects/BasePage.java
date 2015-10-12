package net.adiherzog.pizza.pageObjects;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public static WebDriver getWebDriver() {
        return WebDriverHolder.INSTANCE.getWebDriver();
    }

}

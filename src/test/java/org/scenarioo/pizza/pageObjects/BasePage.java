package org.scenarioo.pizza.pageObjects;

import org.scenarioo.pizza.selenium.WebDriverHolder;
import org.openqa.selenium.WebDriver;

public class BasePage {

    public static WebDriver getWebDriver() {
        return WebDriverHolder.INSTANCE.getWebDriver();
    }

}

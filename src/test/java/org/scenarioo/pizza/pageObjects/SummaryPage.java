package org.scenarioo.pizza.pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SummaryPage extends BasePage {

    public static void assertPizzaVerduraAndRedWineAreListed() {
        WebDriver webDriver = getWebDriver();
        String pizza = webDriver.findElement(By.id("summary_pizza")).getText();
        String drinks = webDriver.findElement(By.id("summary_drinks")).getText();

        Assert.assertEquals("Pizza Verdura", pizza);
        Assert.assertEquals("Vino Rosso", drinks);
    }

    public static void clickOrderButton() {
        getStepElement().findElement(By.className("next")).click();
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-summary"));
    }

}

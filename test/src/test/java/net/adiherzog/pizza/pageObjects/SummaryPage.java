package net.adiherzog.pizza.pageObjects;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SummaryPage {

    public static void assertPizzaVerduraAndRedWineAreListed() {
        WebDriver webDriver = WebDriverHolder.INSTANCE.getWebDriver();
        String pizza = webDriver.findElement(By.id("summary_pizza")).getText();
        String drinks = webDriver.findElement(By.id("summary_drinks")).getText();

        Assert.assertEquals("Pizza Verdura", pizza);
        Assert.assertEquals("Vino Rosso", drinks);
    }

    public static void clickOrderButton() {
        WebDriverHolder.INSTANCE.getWebDriver().findElement(By.id("summary_next")).click();
    }

}

package org.scenarioo.pizza.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class ConfirmationPage extends BasePage {

    public static void assertConfirmationPageIsDisplayed() {
        // Spinner takes around 7 seconds - so wait slightly longer
        WebDriverWait webDriverWait = new WebDriverWait(getWebDriver(), 10);
        webDriverWait.until(ExpectedConditions.visibilityOf(getStepElement()));
    }

    private static WebElement getStepElement() {
        return getWebDriver().findElement(By.id("step-confirmation"));
    }

}

package org.scenarioo.pizza.selenium;

import org.scenarioo.pizza.scenarioo.ScenariooEventListener;
import org.scenarioo.pizza.scenarioo.UseCaseContext;
import org.scenarioo.pizza.scenarioo.UseCaseContextHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * This singleton creates and holds the reference to the WebDriver which controls the browser.
 * This makes it possible to access the WebDriver from all the page objects and wherever else
 * needed without passing any references.
 *
 * Currently this holder only supports one concurrent WebDriver. If you want to run your tests in parallel
 * you should extend the holder to hold an instance of a WebDriver for each thread.
 */
public enum WebDriverHolder {

    INSTANCE;

    private EventFiringWebDriver webDriver;

    /**
     * Only call this after a use case context has been created for the current use case.
     */
    public void openBrowserAndRegisterEventListener() {
        webDriver = new EventFiringWebDriver(new FirefoxDriver());

        UseCaseContext useCaseContext = UseCaseContextHolder.INSTANCE.getUseCaseContext();
        ScenariooEventListener scenariooEventListener = new ScenariooEventListener(useCaseContext);

        webDriver.register(scenariooEventListener);
    }

    public WebDriver getWebDriver() {
        return webDriver;
    }

    public void closeBrowser() {
        webDriver.quit();
    }

}

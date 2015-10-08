package net.adiherzog.pizza.infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 * Makes sure each thread has its own webdriver.
 */
public enum WebDriverHolder {

    INSTANCE;

    private ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();

    /**
     * Creates a new webdriver and opens a browser window.
     * Call this in the @Before or @BeforeClass method of your tests.
     */
    public void openBrowser() {
        WebDriver webDriver = new FirefoxDriver();
        threadLocalWebDriver.set(webDriver);
    }

    /**
     * Only call if you already created a webdriver for your thread usind openBrowser().
     */
    public WebDriver getWebDriver() {
        return threadLocalWebDriver.get();
    }

    /**
     * Only call if a webdriver actually exists for your thread.
     * Call this in your @After or @AfterClass method of your tests.
     */
    public void closeBrowser() {
        threadLocalWebDriver.get().quit();
    }

}

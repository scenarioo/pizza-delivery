package net.adiherzog.pizza.infrastructure;

import net.adiherzog.pizza.scenarioo.ScenariooEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Makes sure each thread has its own webdriver.
 */
public enum WebDriverHolder {

    INSTANCE;

    private EventFiringWebDriver webDriver;

    /**
     * Creates a new webdriver and opens a browser window.
     * Call this in the @Before or @BeforeClass method of your tests.
     */
    public void openBrowser() {
        WebDriver firefoxDriver = new FirefoxDriver();
        webDriver = new EventFiringWebDriver(firefoxDriver);
    }

    /**
     * Register an event listener with the EventFiringWebDriver. The listener is in
     * charge of saving steps for the Scenarioo documentation.
     */
    public void registerEventListener(ScenariooEventListener scenariooEventListener) {
        ((EventFiringWebDriver)getWebDriver()).register(scenariooEventListener);
    }

    /**
     * Only call after openBrowser()
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    /**
     * Only call after openBrowser()
     * Call this in the @After or @AfterClass method of your tests.
     */
    public void closeBrowser() {
        webDriver.quit();
    }


}

package net.adiherzog.pizza.infrastructure;

import net.adiherzog.pizza.scenarioo.ScenariooEventListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

/**
 * Makes sure each thread has its own webdriver.
 */
public enum WebDriverHolder {

    INSTANCE;

    private ThreadLocal<WebDriver> threadLocalWebDriver = new ThreadLocal<>();
    private ThreadLocal<String> useCaseName = new ThreadLocal<>();
    private ThreadLocal<String> scenarioName = new ThreadLocal<>();
    private ThreadLocal<Integer> stepIndex = new ThreadLocal<>();

    /**
     * Creates a new webdriver and opens a browser window.
     * Call this in the @Before or @BeforeClass method of your tests.
     */
    public void openBrowser() {
        WebDriver webDriver = new FirefoxDriver();
        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(webDriver);
        eventFiringWebDriver.register(new ScenariooEventListener());
        threadLocalWebDriver.set(eventFiringWebDriver);
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

    /**
     * Call this at the start of each use case so that steps can be put into the correct use case.
     */
    public void setUseCaseName(String useCaseName) {
        this.useCaseName.set(useCaseName);
    }

    /**
     * Call this before every scenario with the correct scenario name.
     */
    public void setScenarioName(String scenarioName) {
        this.scenarioName.set(scenarioName);
    }

    public String getUseCaseName() {
        return useCaseName.get();
    }

    public String getScenarioName() {
        return scenarioName.get();
    }

    public Integer getStepIndex() {
        return stepIndex.get();
    }

    public void setStepIndex(Integer stepIndex) {
        this.stepIndex.set(stepIndex);
    }

}

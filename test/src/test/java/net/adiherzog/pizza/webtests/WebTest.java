package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.selenium.WebDriverHolder;
import net.adiherzog.pizza.scenarioo.ScenariooEventListener;
import net.adiherzog.pizza.scenarioo.UseCaseContext;
import org.junit.*;
import org.junit.rules.TestName;

public class WebTest {

    @Rule
    public TestName name = new TestName();

    private UseCaseContext useCaseContext;

    public WebTest() {
        useCaseContext = new UseCaseContext(this);
        WebDriverHolder.INSTANCE.registerEventListener(new ScenariooEventListener(useCaseContext));
    }

    public UseCaseContext getUseCaseContext() {
        return useCaseContext;
    }

    @BeforeClass
    public static void openBrowser() {
        WebDriverHolder.INSTANCE.openBrowser();
    }

    @Before
    public void setupTest() {
        getUseCaseContext().startNewScenario();
    }

    @After
    public void recordLastStep() {
        getUseCaseContext().recordLastStepAndScenario();
    }

    @AfterClass
    public static void closeBrowser() {
        UseCaseContext.finishUseCase(OrderPizzaWebTest.class);
        WebDriverHolder.INSTANCE.closeBrowser();
    }

}

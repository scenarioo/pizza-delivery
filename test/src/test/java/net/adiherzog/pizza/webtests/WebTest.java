package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import net.adiherzog.pizza.scenarioo.ScenariooEventListener;
import net.adiherzog.pizza.scenarioo.UseCaseContext;
import org.junit.Rule;
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

}

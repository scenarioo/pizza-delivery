package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.scenarioo.rules.ScenarioRule;
import net.adiherzog.pizza.scenarioo.rules.UseCaseRule;
import org.junit.ClassRule;
import org.junit.Rule;

public class WebTest {

    @ClassRule
    public static UseCaseRule useCaseRule = new UseCaseRule();

    @Rule
    public ScenarioRule scenarioRule = new ScenarioRule();

}

package org.scenarioo.pizza.webtests;

import org.scenarioo.pizza.scenarioo.rules.ScenarioRule;
import org.scenarioo.pizza.scenarioo.rules.UseCaseRule;
import org.junit.ClassRule;
import org.junit.Rule;

public class WebTest {

    @ClassRule
    public static UseCaseRule useCaseRule = new UseCaseRule();

    @Rule
    public ScenarioRule scenarioRule = new ScenarioRule();

}

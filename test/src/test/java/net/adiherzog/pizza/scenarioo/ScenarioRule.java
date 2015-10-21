package net.adiherzog.pizza.scenarioo;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.scenarioo.model.docu.entities.Status;


public class ScenarioRule implements TestRule {

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Status scenarioStatus = Status.SUCCESS;
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    // Test failed
                    scenarioStatus = Status.FAILED;
                }
                UseCaseContextHolder.INSTANCE.getUseCaseContext().recordScenario(scenarioStatus);
            }
        };
    }

}

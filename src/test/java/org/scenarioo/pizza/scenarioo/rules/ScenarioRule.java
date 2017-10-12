package org.scenarioo.pizza.scenarioo.rules;

import org.scenarioo.pizza.scenarioo.UseCaseContext;
import org.scenarioo.pizza.scenarioo.UseCaseContextHolder;
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
                UseCaseContext useCaseContext  = UseCaseContextHolder.INSTANCE.getUseCaseContext();
                useCaseContext.startNewScenario(description.getMethodName());

                Throwable exceptionDuringTest = null;
                try {
                    base.evaluate();
                } catch (Throwable t) {
                    useCaseContext.setStatusOfCurrentScenario(Status.FAILED);
                    exceptionDuringTest = t;
                }

                useCaseContext.recordLastStep();
                useCaseContext.recordScenario();

                if(exceptionDuringTest != null) {
                    throw exceptionDuringTest;
                }
            }
        };
    }

}

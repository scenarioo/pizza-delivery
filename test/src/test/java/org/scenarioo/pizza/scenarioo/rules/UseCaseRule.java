package org.scenarioo.pizza.scenarioo.rules;

import org.scenarioo.pizza.scenarioo.UseCaseContext;
import org.scenarioo.pizza.scenarioo.UseCaseContextHolder;
import org.scenarioo.pizza.selenium.WebDriverHolder;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class UseCaseRule implements TestRule {

    private UseCaseContext useCaseContext;

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                startUseCase(description.getTestClass());

                base.evaluate();

                finishUseCase();
            }
        };
    }

    private void startUseCase(Class<?> testClass) {
        useCaseContext = new UseCaseContext(testClass);
        UseCaseContextHolder.INSTANCE.setUseCaseContext(useCaseContext);
        WebDriverHolder.INSTANCE.openBrowserAndRegisterEventListener();
    }

    private void finishUseCase() {
        useCaseContext.finishUseCase();
        WebDriverHolder.INSTANCE.closeBrowser();
    }

}

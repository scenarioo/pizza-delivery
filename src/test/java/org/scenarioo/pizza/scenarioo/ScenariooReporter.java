package org.scenarioo.pizza.scenarioo;

import org.junit.jupiter.api.extension.*;
import org.scenarioo.model.docu.entities.Status;
import org.scenarioo.pizza.selenium.WebDriverHolder;

/**
 * Reporter to report use cases (for each test class), scenarios (for each test method)
 * and steps (for each interaction by setting up the webdriver with an event listener)
 * into scenarioo documentation data during test execution.
 */
public class ScenariooReporter implements
        BeforeAllCallback, AfterAllCallback,
        BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Class<?> testClass = context.getRequiredTestClass();
        UseCaseContext useCaseContext = new UseCaseContext(testClass);
        UseCaseContextHolder.INSTANCE.setUseCaseContext(useCaseContext);

        WebDriverHolder.INSTANCE.openBrowserAndRegisterEventListener();
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        UseCaseContextHolder.INSTANCE.getUseCaseContext().finishUseCase();
        WebDriverHolder.INSTANCE.closeBrowser();
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        UseCaseContext useCaseContext = UseCaseContextHolder.INSTANCE.getUseCaseContext();
        useCaseContext.startNewScenario(context.getRequiredTestMethod().getName());
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        UseCaseContext useCaseContext = UseCaseContextHolder.INSTANCE.getUseCaseContext();
        context.getExecutionException().ifPresent(e -> {
            useCaseContext.setStatusOfCurrentScenario(Status.FAILED);
        });
        useCaseContext.recordLastStep();
        useCaseContext.recordScenario();
    }

}

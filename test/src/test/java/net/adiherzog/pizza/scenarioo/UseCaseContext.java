package net.adiherzog.pizza.scenarioo;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import net.adiherzog.pizza.webtests.WebTest;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Status;
import org.scenarioo.model.docu.entities.UseCase;

import java.util.HashMap;
import java.util.Map;

public class UseCaseContext {

    private static Map<Class<? extends WebTest>, UseCaseContext> contextsByWebTestClass = new HashMap<>();

    private WebTest webTestClass;
    private String useCaseName;
    private int stepIndex = 0;

    public UseCaseContext(WebTest webTestClass) {
        this.webTestClass = webTestClass;
        this.useCaseName = webTestClass.getClass().getSimpleName().replace("WebTest", "");
        contextsByWebTestClass.put(webTestClass.getClass(), this);
    }

    public String getUseCaseName() {
        return useCaseName;
    }

    public String getScenarioName() {
        return webTestClass.name.getMethodName();
    }

    public Integer getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(Integer stepIndex) {
        this.stepIndex = stepIndex;
    }

    public void startNewScenario() {
        setStepIndex(0);
    }

    /**
     * Call this in the @After method of your tests, so that the final screen of the webtest
     * can be saved as a step.
     */
    public void recordLastStep() {
        new StepRecorder(this).recordStep(WebDriverHolder.INSTANCE.getWebDriver());
        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveScenario(getUseCaseName(), createScenario());
        scenarioDocuWriter.flush();
    }

    private Scenario createScenario() {
        Scenario scenario = new Scenario();
        scenario.setName(getScenarioName());
        scenario.setStatus(Status.SUCCESS);
        scenario.addLabel("good scenario");
        return scenario;
    }

    public static void finishUseCase(Class<? extends WebTest> webTestClass) {
        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveUseCase(createUseCase(webTestClass));
        scenarioDocuWriter.flush();
    }

    private static UseCase createUseCase(Class<? extends WebTest> webTestClass) {
        UseCase useCase = new UseCase();
        useCase.setName(UseCaseContext.getContextForClass(webTestClass).getUseCaseName());
        useCase.addLabel("important");
        useCase.setStatus(Status.SUCCESS);
        return useCase;
    }

    public static UseCaseContext getContextForClass(Class<? extends WebTest> webTestClass) {
        return contextsByWebTestClass.get(webTestClass);
    }

}

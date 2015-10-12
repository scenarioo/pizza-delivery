package net.adiherzog.pizza.scenarioo;

import net.adiherzog.pizza.selenium.WebDriverHolder;
import net.adiherzog.pizza.webtests.WebTest;
import org.openqa.selenium.JavascriptExecutor;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class UseCaseContext {

    private static Map<Class<? extends WebTest>, UseCaseContext> contextsByWebTestClass = new HashMap<>();

    private WebTest webTestClass;
    private String useCaseName;
    private int stepIndex = 0;
    private String pageName;

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

    public org.scenarioo.model.docu.entities.Labels getScenarioLabels() {
        Method method = getMethod();
        Labels labels = method.getAnnotation(Labels.class);
        if(labels != null) {
            org.scenarioo.model.docu.entities.Labels scenarioLabels = new org.scenarioo.model.docu.entities.Labels();
            scenarioLabels.setLabels(new HashSet<String>(Arrays.asList(labels.value())));
            return scenarioLabels;
        }
        return null;
    }

    public String getScenarioDescription() {
        Method method = getMethod();
        Description description = method.getAnnotation(Description.class);
        if(description != null) {
            return description.value();
        }
        return null;
    }

    public Integer getStepIndex() {
        return stepIndex;
    }

    public void startNewScenario() {
        stepIndex = 0;
    }

    public void incrementStepIndex() {
        this.stepIndex++;
    }

    public void recordLastStepAndScenario() {
        new StepRecorder(this).recordStep();
        new ScenarioRecorder(this).recordScenario();
    }

    public static void finishUseCase(Class<? extends WebTest> webTestClass) {
        new UseCaseRecorder(UseCaseContext.getContextForClass(webTestClass)).recordUseCase();
    }

    public static UseCaseContext getContextForClass(Class<? extends WebTest> webTestClass) {
        return contextsByWebTestClass.get(webTestClass);
    }

    public Method getMethod() {
        try {
            return webTestClass.getClass().getMethod(webTestClass.name.getMethodName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getPageName() {
        String script = "return document.getElementById('currentStep').innerHTML";
        String currentStep = (String) ((JavascriptExecutor) WebDriverHolder.INSTANCE.getWebDriver()).executeScript(script);
        if(currentStep == null) {
            return null;
        }
        return currentStep.replace("step-", "");
    }

}

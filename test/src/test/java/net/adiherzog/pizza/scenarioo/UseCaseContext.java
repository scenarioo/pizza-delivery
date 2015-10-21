package net.adiherzog.pizza.scenarioo;

import net.adiherzog.pizza.scenarioo.annotations.Description;
import net.adiherzog.pizza.scenarioo.annotations.Labels;
import net.adiherzog.pizza.scenarioo.recorders.ScenarioRecorder;
import net.adiherzog.pizza.scenarioo.recorders.StepRecorder;
import net.adiherzog.pizza.scenarioo.recorders.UseCaseRecorder;
import net.adiherzog.pizza.selenium.WebDriverHolder;
import net.adiherzog.pizza.webtests.WebTest;
import org.openqa.selenium.JavascriptExecutor;
import org.scenarioo.model.docu.entities.Status;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

public class UseCaseContext {

    private WebTest webTest;
    private String useCaseName;
    private int stepIndex = 0;
    private String useCaseDescription;

    public UseCaseContext(WebTest webTest) {
        this.webTest = webTest;
        this.useCaseName = webTest.getClass().getSimpleName().replace("WebTest", "");
    }

    public void setWebTest(WebTest webTest) {
        this.webTest = webTest;
    }

    public WebTest getWebTest() {
        return webTest;
    }

    public String getUseCaseName() {
        return useCaseName;
    }

    public String getScenarioName() {
        return webTest.name.getMethodName();
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

    public String getUseCaseDescription() {
        Description description = webTest.getClass().getAnnotation(Description.class);
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

    public void recordLastStep() {
        new StepRecorder(this).recordStep();
    }

    public void recordScenario(Status scenarioStatus) {
        new ScenarioRecorder(this).recordScenario(scenarioStatus);
    }

    public void finishUseCase() {
        new UseCaseRecorder(this).recordUseCase();
    }

    public Method getMethod() {
        try {
            return webTest.getClass().getMethod(webTest.name.getMethodName());
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

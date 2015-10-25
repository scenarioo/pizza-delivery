package net.adiherzog.pizza.scenarioo;

import net.adiherzog.pizza.scenarioo.annotations.Description;
import net.adiherzog.pizza.scenarioo.annotations.Labels;
import net.adiherzog.pizza.scenarioo.recorders.ScenarioRecorder;
import net.adiherzog.pizza.scenarioo.recorders.StepRecorder;
import net.adiherzog.pizza.scenarioo.recorders.UseCaseRecorder;
import net.adiherzog.pizza.selenium.WebDriverHolder;
import org.openqa.selenium.JavascriptExecutor;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Status;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;

public class UseCaseContext {

    private ScenarioDocuWriter writer = ScenariooWriterFactory.getNewWriter();
    private Class<?> webTestClass;
    private String useCaseName;
    private int stepIndex = 0;
    private Status statusOfCurrentScenario;
    private String currentTestMethodName;

    public UseCaseContext(Class<?> webTestClass) {
        this.webTestClass = webTestClass;
        this.useCaseName = webTestClass.getSimpleName().replace("WebTest", "");
    }

    public ScenarioDocuWriter getWriter() {
        return writer;
    }

    public String getUseCaseName() {
        return useCaseName;
    }

    public String getScenarioName() {
        return currentTestMethodName;
    }

    public org.scenarioo.model.docu.entities.Labels getScenarioLabels() {
        Method method = getMethod();
        Labels labels = method.getAnnotation(Labels.class);
        if(labels != null) {
            org.scenarioo.model.docu.entities.Labels scenarioLabels = new org.scenarioo.model.docu.entities.Labels();
            scenarioLabels.setLabels(new HashSet<>(Arrays.asList(labels.value())));
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
        Description description = webTestClass.getAnnotation(Description.class);
        if(description != null) {
            return description.value();
        }
        return null;
    }

    public Integer getStepIndex() {
        return stepIndex;
    }

    public void startNewScenario(String methodName) {
        stepIndex = 0;
        statusOfCurrentScenario = Status.SUCCESS;
        currentTestMethodName = methodName;
    }

    public void setStatusOfCurrentScenario(Status statusOfCurrentScenario) {
        this.statusOfCurrentScenario = statusOfCurrentScenario;
    }

    public void incrementStepIndex() {
        this.stepIndex++;
    }

    public void recordLastStep() {
        new StepRecorder(this).recordStep();
    }

    public void recordScenario() {
        new ScenarioRecorder(this).recordScenario(statusOfCurrentScenario);
    }

    public void finishUseCase() {
        new UseCaseRecorder(this).recordUseCase();
        writer.flush();
    }

    public Method getMethod() {
        try {
            return webTestClass.getMethod(currentTestMethodName);
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

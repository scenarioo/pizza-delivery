package org.scenarioo.pizza.scenarioo.recorders;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.*;
import org.scenarioo.pizza.scenarioo.UseCaseContext;
import org.scenarioo.pizza.selenium.WebDriverHolder;

import java.io.File;

public class StepRecorder {

    private UseCaseContext useCaseContext;

    public StepRecorder(UseCaseContext useCaseContext) {
        this.useCaseContext = useCaseContext;
    }

    public void recordStep() {
        WebDriver webDriver = WebDriverHolder.INSTANCE.getWebDriver();
        String useCaseName = useCaseContext.getUseCaseName();
        String scenarioName = useCaseContext.getScenarioName();
        int stepIndex = useCaseContext.getStepIndex();

        ScenarioDocuWriter writer = useCaseContext.getWriter();
        writer.saveScreenshotAsPng(useCaseName, scenarioName, stepIndex, getScreenshot(webDriver));
        File screenShotFileName = writer.getScreenshotFile(useCaseName, scenarioName, stepIndex);
        writer.saveStep(useCaseName, scenarioName, createStep(stepIndex, screenShotFileName));
        useCaseContext.incrementStepIndex();
    }

    private byte[] getScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    private Step createStep(Integer stepIndex, File screenShotFileName) {
        Step step = new Step();
        step.setStepDescription(createStepDescription(stepIndex, screenShotFileName));
        step.setHtml(new StepHtml());
        step.getHtml().setHtmlSource(WebDriverHolder.INSTANCE.getWebDriver().getPageSource());
        step.setPage(createPage());
        step.setScreenAnnotations(useCaseContext.getScreenAnnotations());
        useCaseContext.clearScreenAnnotations();
        return step;
    }

    private Page createPage() {
        Page page = new Page();
        page.setName(useCaseContext.getPageName());
        return page;
    }

    private StepDescription createStepDescription(Integer stepIndex, File screenShotFileName) {
        StepDescription stepDescription = new StepDescription();
        stepDescription.setIndex(stepIndex);
        stepDescription.setScreenshotFileName(screenShotFileName.getName());
        stepDescription.setStatus(Status.SUCCESS);
        return stepDescription;
    }

}

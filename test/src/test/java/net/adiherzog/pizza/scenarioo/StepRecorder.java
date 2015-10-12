package net.adiherzog.pizza.scenarioo;

import net.adiherzog.pizza.selenium.WebDriverHolder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.*;

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

        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveScreenshotAsPng(useCaseName, scenarioName, stepIndex, getScreenshot(webDriver));
        File screenShotFileName = scenarioDocuWriter.getScreenshotFile(useCaseName, scenarioName, stepIndex);
        scenarioDocuWriter.saveStep(useCaseName, scenarioName, createStep(stepIndex, screenShotFileName));
        useCaseContext.incrementStepIndex();
        scenarioDocuWriter.flush();
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

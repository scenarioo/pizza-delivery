package net.adiherzog.pizza.scenarioo;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.*;

import java.io.File;

public class StepRecorder {

    public void recordStep(WebDriver driver) {
        String useCaseName = WebDriverHolder.INSTANCE.getUseCaseName();
        String scenarioName = WebDriverHolder.INSTANCE.getScenarioName();

        Integer stepIndex = WebDriverHolder.INSTANCE.getStepIndex();

        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveScreenshotAsPng(useCaseName, scenarioName, stepIndex, getScreenshot(driver));
        File screenShotFileName = scenarioDocuWriter.getScreenshotFile(useCaseName, scenarioName, stepIndex);
        scenarioDocuWriter.saveStep(useCaseName, scenarioName, createStep(stepIndex, screenShotFileName));
        WebDriverHolder.INSTANCE.setStepIndex(stepIndex + 1);
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
        step.setPage(new Page());
        return step;
    }

    private StepDescription createStepDescription(Integer stepIndex, File screenShotFileName) {
        StepDescription stepDescription = new StepDescription();
        stepDescription.setIndex(stepIndex);
        stepDescription.setScreenshotFileName(screenShotFileName.getName());
        stepDescription.setStatus(Status.SUCCESS);
        return stepDescription;
    }

}

package net.adiherzog.pizza.scenarioo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class ScenariooEventListener extends AbstractWebDriverEventListener {

    private StepRecorder stepRecorder;

    public ScenariooEventListener(UseCaseContext useCaseContext) {
       this.stepRecorder = new StepRecorder(useCaseContext);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        super.beforeClickOn(element, driver);
        stepRecorder.recordStep();
    }

}

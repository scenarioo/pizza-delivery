package org.scenarioo.pizza.scenarioo;

import org.scenarioo.model.docu.entities.screenAnnotations.ScreenAnnotation;
import org.scenarioo.model.docu.entities.screenAnnotations.ScreenAnnotationClickAction;
import org.scenarioo.model.docu.entities.screenAnnotations.ScreenAnnotationStyle;
import org.scenarioo.pizza.scenarioo.recorders.StepRecorder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class ScenariooEventListener extends AbstractWebDriverEventListener {

    private UseCaseContext useCaseContext;
    private StepRecorder stepRecorder;

    public ScenariooEventListener(UseCaseContext useCaseContext) {
        this.useCaseContext = useCaseContext;
        this.stepRecorder = new StepRecorder(useCaseContext);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        super.beforeClickOn(element, driver);
        useCaseContext.addScreenAnnotation(createClickScreenAnnotation(element));
        stepRecorder.recordStep();
    }

    private ScreenAnnotation createClickScreenAnnotation(WebElement element) {
        ScreenAnnotation annotation = new ScreenAnnotation();
        annotation.setClickAction(ScreenAnnotationClickAction.TO_NEXT_STEP);
        annotation.setStyle(ScreenAnnotationStyle.CLICK);
        annotation.setRegion(element.getLocation().x, element.getLocation().y, element.getSize().width, element.getSize().height);
        return annotation;
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver) {
        super.beforeChangeValueOf(element, driver);
        useCaseContext.addScreenAnnotation(createChangeValueScreenAnnotation(element));
    }

    private ScreenAnnotation createChangeValueScreenAnnotation(WebElement element) {
        ScreenAnnotation annotation = new ScreenAnnotation();
        annotation.setStyle(ScreenAnnotationStyle.KEYBOARD);
        annotation.setRegion(element.getLocation().x, element.getLocation().y, element.getSize().width, element.getSize().height);
        return annotation;
    }

}

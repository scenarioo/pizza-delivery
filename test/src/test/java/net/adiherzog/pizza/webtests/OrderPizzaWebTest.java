package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import net.adiherzog.pizza.pageObjects.ConfirmationPage;
import net.adiherzog.pizza.pageObjects.SelectDrinkPage;
import net.adiherzog.pizza.pageObjects.SelectPizzaPage;
import net.adiherzog.pizza.pageObjects.SummaryPage;
import net.adiherzog.pizza.scenarioo.ScenariooWriterFactory;
import net.adiherzog.pizza.scenarioo.StepRecorder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Scenario;
import org.scenarioo.model.docu.entities.Status;
import org.scenarioo.model.docu.entities.UseCase;

public class OrderPizzaWebTest {

    private static final String USE_CASE_NAME = "Order Pizza";

    @BeforeClass
    public static void openBrowser() {
        WebDriverHolder.INSTANCE.openBrowser();
        WebDriverHolder.INSTANCE.setUseCaseName(USE_CASE_NAME);
    }

    @Test
    public void orderPizza_plusRedWine() {
        WebDriverHolder.INSTANCE.setScenarioName("order pizza plus red wine");
        WebDriverHolder.INSTANCE.setStepIndex(0);

        SelectPizzaPage.navigateToPage();
        SelectPizzaPage.selectPizzaVerdura();
        SelectPizzaPage.clickNextButton();

        SelectDrinkPage.selectDrinkRedWine();
        SelectDrinkPage.clickNext();

        SummaryPage.assertPizzaVerduraAndRedWineAreListed();

        SummaryPage.clickOrderButton();
        ConfirmationPage.assertConfirmationPageIsDisplayed();
    }

    @After
    public void recordLastStep() {
        new StepRecorder().recordStep(WebDriverHolder.INSTANCE.getWebDriver());

        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveScenario(WebDriverHolder.INSTANCE.getUseCaseName(), createScenario());
        scenarioDocuWriter.flush();
    }

    private Scenario createScenario() {
        Scenario scenario = new Scenario();
        scenario.setName(WebDriverHolder.INSTANCE.getScenarioName());
        scenario.setStatus(Status.SUCCESS);
        scenario.addLabel("good scenario");
        return scenario;
    }

    @AfterClass
    public static void closeBrowser() {
        WebDriverHolder.INSTANCE.closeBrowser();

        ScenarioDocuWriter scenarioDocuWriter = ScenariooWriterFactory.getNewWriter();
        scenarioDocuWriter.saveUseCase(createUseCase());
        scenarioDocuWriter.flush();
    }

    private static UseCase createUseCase() {
        UseCase useCase = new UseCase();
        useCase.setName(USE_CASE_NAME);
        useCase.addLabel("important");
        useCase.setStatus(Status.SUCCESS);
        return useCase;
    }

}

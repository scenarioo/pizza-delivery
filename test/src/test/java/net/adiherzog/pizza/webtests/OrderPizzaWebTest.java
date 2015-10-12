package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import net.adiherzog.pizza.pageObjects.*;
import net.adiherzog.pizza.scenarioo.UseCaseContext;
import org.junit.*;

public class OrderPizzaWebTest extends WebTest {

    @BeforeClass
    public static void openBrowser() {
        WebDriverHolder.INSTANCE.openBrowser();
    }

    @Before
    public void setupTest() {
        getUseCaseContext().startNewScenario();
    }

    @Test
    public void knownPhoneNumber_addressCorrect_doesNotAskToEnterAddress() {
        EnterPhoneNumberPage.navigateToPage();
        EnterPhoneNumberPage.enterKnownPhoneNumber();
        EnterPhoneNumberPage.clickNext();

        ConfirmAddressPage.assertPageIsShown();
        ConfirmAddressPage.clickYes();

        SelectPizzaPage.assertPageIsShown();
    }

    @Test
    public void unknownPhoneNumber_asksToEnterAddress() {
        EnterPhoneNumberPage.navigateToPage();
        EnterPhoneNumberPage.enterUnknownPhoneNumber();
        EnterPhoneNumberPage.clickNext();

        EnterAddressPage.assertPageIsShown();
        EnterAddressPage.enterAddress();
        EnterAddressPage.clickNext();

        SelectPizzaPage.assertPageIsShown();
    }

    @Test
    public void orderPizza_plusRedWine() {
        EnterPhoneNumberPage.navigateToPage();
        EnterPhoneNumberPage.enterKnownPhoneNumber();
        EnterPhoneNumberPage.clickNext();

        ConfirmAddressPage.assertPageIsShown();
        ConfirmAddressPage.clickYes();

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
        getUseCaseContext().recordLastStep();
    }

    @AfterClass
    public static void closeBrowser() {
        UseCaseContext.finishUseCase(OrderPizzaWebTest.class);
        WebDriverHolder.INSTANCE.closeBrowser();
    }

}

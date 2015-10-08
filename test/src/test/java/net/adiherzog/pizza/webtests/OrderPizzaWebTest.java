package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.infrastructure.WebDriverHolder;
import net.adiherzog.pizza.pageObjects.ConfirmationPage;
import net.adiherzog.pizza.pageObjects.SelectDrinkPage;
import net.adiherzog.pizza.pageObjects.SelectPizzaPage;
import net.adiherzog.pizza.pageObjects.SummaryPage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class OrderPizzaWebTest {

    @BeforeClass
    public static void openBrowser() {
        WebDriverHolder.INSTANCE.openBrowser();
    }

    @Test
    public void orderPizza_plusRedWine() {
        SelectPizzaPage.navigateToPage();
        SelectPizzaPage.selectPizzaVerdura();
        SelectPizzaPage.clickNextButton();

        SelectDrinkPage.selectDrinkRedWine();
        SelectDrinkPage.clickNext();

        SummaryPage.assertPizzaVerduraAndRedWineAreListed();

        SummaryPage.clickOrderButton();
        ConfirmationPage.assertConfirmationPageIsDisplayed();
    }

    @AfterClass
    public static void closeBrowser() {
        WebDriverHolder.INSTANCE.closeBrowser();
    }

}

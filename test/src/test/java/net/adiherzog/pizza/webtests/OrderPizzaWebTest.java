package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.pageObjects.*;
import net.adiherzog.pizza.scenarioo.annotations.Description;
import net.adiherzog.pizza.scenarioo.annotations.Labels;
import org.junit.Test;

@Description("Order a pizza and drinks from \"Hot Pizza Delivery\".")
public class OrderPizzaWebTest extends WebTest {

    @Test
    @Labels({"main flow"})
    @Description("Typical order process for a customer with a phone number " +
            "that is already registered with the correct address.")
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

    @Test
    @Labels({"partial flow", "enter address"})
    @Description("Phone number is already known, but the customer wants to correct the address.")
    public void knownPhoneNumber_butIncorrectPhoneNumber() {
        EnterPhoneNumberPage.navigateToPage();
        EnterPhoneNumberPage.enterKnownPhoneNumber();
        EnterPhoneNumberPage.clickNext();

        ConfirmAddressPage.assertPageIsShown();
        ConfirmAddressPage.clickNo();

        EnterAddressPage.assertPageIsShown();
        EnterAddressPage.enterAddress();
        EnterAddressPage.clickNext();

        SelectPizzaPage.assertPageIsShown();
    }

    @Test
    @Labels({"partial flow", "enter address"})
    @Description("Phone number is not known yet, therefore the customer has to enter the address.")
    public void unknownPhoneNumber_asksToEnterAddress() {
        EnterPhoneNumberPage.navigateToPage();
        EnterPhoneNumberPage.enterUnknownPhoneNumber();
        EnterPhoneNumberPage.clickNext();

        EnterAddressPage.assertPageIsShown();
        EnterAddressPage.enterAddress();
        EnterAddressPage.clickNext();

        SelectPizzaPage.assertPageIsShown();
    }

}

package org.scenarioo.pizza.webtests;

import org.junit.jupiter.api.Test;
import org.scenarioo.pizza.scenarioo.annotations.Description;
import org.scenarioo.pizza.scenarioo.annotations.Labels;
import org.scenarioo.pizza.pageObjects.*;

@Description("Order a pizza and drinks from \"Hot Pizza Delivery\".")
public class OrderPizzaWebTest extends WebTest {

    @Test
    @Labels({"main flow"})
    @Description("Typical order process for a customer with a phone number " +
            "that is already registered with the correct address.")
    public void existingCustomer_canOrderPizzaOnRegisteredAddress() {
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
    public void existingCustomer_canOrderPizzaOnDifferentAddress() {
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
    public void newCustomer_canRegisterWithAddressToOrderPizza() {
        EnterPhoneNumberPage.navigateToPage();
        EnterPhoneNumberPage.enterUnknownPhoneNumber();
        EnterPhoneNumberPage.clickNext();

        EnterAddressPage.assertPageIsShown();
        EnterAddressPage.enterAddress();
        EnterAddressPage.clickNext();

        SelectPizzaPage.assertPageIsShown();
    }

}

package org.scenarioo.pizza.webtests;

import org.scenarioo.pizza.scenarioo.annotations.Description;
import org.scenarioo.pizza.scenarioo.annotations.Labels;
import org.junit.Test;
import org.scenarioo.pizza.pageObjects.EnterPhoneNumberPage;

@Description("Look up phone number of \"Hot Pizza Delivery\".")
public class LookUpPhoneNumberWebTest extends WebTest {

    @Test
    @Labels({"main flow"})
    @Description("Find phone number on start page")
    public void phone_number_present() {
        EnterPhoneNumberPage.navigateToPage();
        EnterPhoneNumberPage.assertPhoneNumberOfHotPizzaDeliveryIsDisplayed();
    }

}

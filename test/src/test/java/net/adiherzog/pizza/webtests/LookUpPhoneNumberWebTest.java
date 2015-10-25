package net.adiherzog.pizza.webtests;

import net.adiherzog.pizza.pageObjects.*;
import net.adiherzog.pizza.scenarioo.annotations.Description;
import net.adiherzog.pizza.scenarioo.annotations.Labels;
import org.junit.Test;

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

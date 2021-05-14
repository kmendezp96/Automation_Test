package pages.components;

import base.DriverFacade;
import org.openqa.selenium.By;
import pages.BasePage;

public class AddToCartModalComponent extends BasePage {

    private final By goToCartButtonSelector = By.xpath("//*[@data-test='add-to-bag-modal']" +
            "//*[@data-test='view-my-bag']");

    public AddToCartModalComponent(DriverFacade webDriver) {
        super(webDriver);
    }

    public void goToCart() {
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(goToCartButtonSelector)
                        .click());
    }


}

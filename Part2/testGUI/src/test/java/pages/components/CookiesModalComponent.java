package pages.components;

import base.DriverFacade;
import org.awaitility.core.ConditionTimeoutException;
import org.openqa.selenium.By;
import pages.BasePage;

public class CookiesModalComponent extends BasePage {

    private final By acceptCookiesButtonSelector = By.xpath("//button[@data-test='cookie-accept-all']");

    public CookiesModalComponent (DriverFacade webDriver) {
        super(webDriver);
    }

    public void acceptCookies () {
        try{
            webDriver.waitForAction(() -> webDriver.findElement(acceptCookiesButtonSelector).click());
        } catch (ConditionTimeoutException ex) {
            System.out.println(ex.getMessage());
        }

    }
}

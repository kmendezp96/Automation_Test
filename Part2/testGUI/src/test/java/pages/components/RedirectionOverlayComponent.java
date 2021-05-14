package pages.components;

import base.DriverFacade;
import org.openqa.selenium.By;
import pages.BasePage;

public class RedirectionOverlayComponent extends BasePage {

    private final By continueStoreButtonSelector = By
            .xpath("//*[@data-test='age-gate-overlay']//button[@kind='lightTheme']");

    public RedirectionOverlayComponent (DriverFacade webDriver) {
        super(webDriver);
    }

    public void continueToStore () {
        webDriver.waitForAction(() -> webDriver
                .findElement(continueStoreButtonSelector).click());
    }
}

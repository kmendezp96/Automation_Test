package pages;

import base.DriverFacade;

public class BasePage {

    protected final DriverFacade webDriver;

    public BasePage(DriverFacade webDriver) {
        this.webDriver = webDriver;
    }
}

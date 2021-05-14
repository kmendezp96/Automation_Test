package pages.components;

import base.DriverFacade;
import org.openqa.selenium.By;
import pages.BasePage;

public class NavigationMenuComponent extends BasePage {

    private final By shopBySectionSelector = By.xpath("//*[@data-analytics-title='shop-by']");
    private final By productTypeSubsectionSelector = By.xpath( "//*[@data-analytics-title='product-type']");
    private final By cartButtonSelector = By.xpath("//*[@data-test='util-bar-cart']");

    private final String productTypeXpath = "//*[@data-analytics-title='%s']";
    public NavigationMenuComponent(DriverFacade webDriver) {
        super(webDriver);
    }

    public void buyByProductType (String productType) {
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(shopBySectionSelector)
                        .click());
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(productTypeSubsectionSelector)
                        .click());
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(By.xpath(String.format(productTypeXpath, productType)))
                        .click());
    }

    public void goToCart() {
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(cartButtonSelector)
                        .click());
    }
}

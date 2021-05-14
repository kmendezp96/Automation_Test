package pages;

import base.DriverFacade;
import pages.components.CookiesModalComponent;
import pages.components.NavigationMenuComponent;
import pages.components.RedirectionOverlayComponent;

public class HomePage extends BasePage {

    public final RedirectionOverlayComponent redirectionOverlayComponent;
    public final CookiesModalComponent cookiesModalComponent;
    public final NavigationMenuComponent navigationMenuComponent;

    public HomePage(DriverFacade webDriver){
        super(webDriver);
        redirectionOverlayComponent = new RedirectionOverlayComponent(webDriver);
        cookiesModalComponent = new CookiesModalComponent(webDriver);
        navigationMenuComponent = new NavigationMenuComponent(webDriver);
    }

    public void openHomePage(String url) {
        webDriver.openUrl(url);
    }
}

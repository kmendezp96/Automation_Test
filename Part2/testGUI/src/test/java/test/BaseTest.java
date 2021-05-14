package test;

import base.DriverFacade;
import base.DriverFactory;
import base.PropertiesHandler;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;
import pages.SearchPage;
import pages.components.AddToCartModalComponent;


public class BaseTest {

    protected DriverFacade driver;
    protected SoftAssertions softly;
    protected PropertiesHandler propertiesHandler;

    protected HomePage homePage;
    protected SearchPage searchPage;
    protected CartPage cartPage;
    protected AddToCartModalComponent addToCartModalComponent;

    @BeforeSuite(alwaysRun = true)
    public void beforeClass(){
        softly  = new SoftAssertions();
        propertiesHandler = new PropertiesHandler();
    }

    @BeforeTest(alwaysRun = true)
    public void beforeTest(){
        driver = new DriverFactory().getDriver(propertiesHandler.getProperty("browser"));
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        cartPage = new CartPage(driver);
        addToCartModalComponent = new AddToCartModalComponent(driver);

        homePage.openHomePage(propertiesHandler.getProperty("url"));
        homePage.redirectionOverlayComponent.continueToStore();
        homePage.cookiesModalComponent.acceptCookies();
    }

    @AfterTest
    public void afterTest() {
        driver.quit();
    }
}

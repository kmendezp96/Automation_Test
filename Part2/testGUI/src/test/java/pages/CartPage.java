package pages;

import base.DriverFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class CartPage extends BasePage {

    private final By productTitleSelector = By
            .xpath("//*[@data-test='cart-item']//*[@data-test='product-title']");
    private final By productPriceSelector = By
            .xpath("//*[@data-test='cart-item']//*[@data-test='product-price']");
    private final By productSalePriceSelector = By
            .xpath("//*[@data-test='cart-item']//*[@data-test='product-price-sale']");
    private final By productQuantitySelector = By
            .xpath("//*[@data-test='cart-item']//*[@data-test='quantity-value']");
    private final By orderSummaryShippingPriceSelector = By
            .xpath("//*[contains(@class, 'OrderSummary')]//*[@data-test='shipping-total-price']");
    private final By orderSummaryTotalPriceSelector = By
            .xpath("//*[contains(@class, 'Pricingstyles__TotalRow')]//*[contains(@class, 'Markup__StyledMarkup')]");

    public CartPage(DriverFacade webDriver) {
        super(webDriver);
    }

    public String getProductTitle() {
        this.webDriver.waitForCondition(() ->
                this.webDriver.findElement(productTitleSelector).isDisplayed());
        return this.webDriver.findElement(productTitleSelector).getText();
    }

    public double getProductPrice() {
        this.webDriver.waitForCondition(() ->
                this.webDriver.findElement(productPriceSelector).isDisplayed());
        String price;
        try{
            price = this.webDriver.findElement(productSalePriceSelector).getText();
        } catch (NoSuchElementException ex) {
            price = this.webDriver.findElement(productPriceSelector).getText();
        }
        return Double.parseDouble(
                price.replaceAll("[^0-9,]","")
                        .replace(",","."));
    }

    public int getProductQuantity() {
        this.webDriver.waitForCondition(() ->
                this.webDriver.findElement(productQuantitySelector).isDisplayed());
        return Integer.parseInt(
                this.webDriver.findElement(productQuantitySelector)
                        .getAttribute("value")
                        .replaceAll("[^0-9,]",""));
    }

    public double getProductShippingPrice() {
        this.webDriver.waitForCondition(() ->
                this.webDriver.findElement(orderSummaryShippingPriceSelector).isDisplayed());
        return Double.parseDouble(
                this.webDriver.findElement(orderSummaryShippingPriceSelector)
                        .getText()
                        .replaceAll("[^0-9,]","")
                        .replace(",","."));
    }

    public double getProductTotalPrice() {
        this.webDriver.waitForCondition(() ->
                this.webDriver.findElement(orderSummaryTotalPriceSelector).isDisplayed());
        return Double.parseDouble(
                this.webDriver.findElement(orderSummaryTotalPriceSelector)
                        .getText()
                        .replaceAll("[^0-9,]","")
                        .replace(",","."));
    }

}

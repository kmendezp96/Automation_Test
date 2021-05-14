package pages;

import base.DriverFacade;
import models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SearchPage extends BasePage {

    private final By totalResultsNumberSelector = By.xpath("//*[@data-test='listing-summary']");
    private final By productPriceSelector = By.xpath("//*[@data-test='product-item']" +
            "//*[@data-test='product-price']");
    private final By productPriceShortSelector = By.xpath("./..//*[@data-test='product-price']");
    private final By productSalePriceShortSelector = By.xpath("./..//*[@data-test='product-price-sale']");
    private final By productTitleSelector = By.xpath("//*[@data-test='product-item']" +
            "//*[@data-test='product-leaf-title-link']");
    private final By productAddButtonSelector = By.xpath("//*[@data-test='product-item']" +
            "//button[@kind='product']");
    private final By pagesListSelector = By.xpath("//*[@data-test='pagination']" +
            "//a[contains(@data-test, 'pagination-page-')]");
    private final By nextPageSelector = By.xpath("//*[@data-test='pagination-next']");

    private final String filtersPriceXpath = "//*[contains(@id,'product-facet-%s')]" +
            "//span[contains(text(),'%s') and contains(text(),'%s') and @data-test='checkbox-text']";
    private final String filtersXpath = filtersPriceXpath.replace(" and contains(text(),'%s')","");

    public SearchPage (DriverFacade webDriver) {
        super(webDriver);
    }

    public void filterByPrice (String priceRange) {
        List<String> params = new ArrayList<>(Arrays.asList(priceRange.split("-")));
        params.add(0,"prices");
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(By.xpath(String.format(filtersPriceXpath, params.toArray())))
                        .click());
    }

    public void filterByAge (String ageRange) {
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(By.xpath(String.format(filtersXpath, "age", ageRange)))
                        .click());
    }

    public int getTotalResultsNumber() {
        String[] results = this.webDriver.findElement(totalResultsNumberSelector)
                .getText().replaceAll("[^0-9 ]","")
                .trim()
                .replace("  "," ")
                .split(" ");

        return Integer.parseInt(results[2]);
    }

    public List<Double> getPageProductPrices() {
        this.webDriver.waitForCondition(() -> this.webDriver.findElements(productTitleSelector).size()>0);
        return this.webDriver.findElements(productPriceSelector)
                .stream()
                .map( e -> Double.parseDouble(e.getText()
                        .replaceAll("[^0-9,.]","")
                        .replace(",",".")))
                .collect(Collectors.toList());
    }

    public List<String> getPageProductTitles() {
        this.webDriver.waitForCondition(() -> this.webDriver.findElements(productTitleSelector).size()>0);
        return this.webDriver.findElements(productTitleSelector)
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public int getPagesNumber() {
        this.webDriver.waitForCondition(() -> this.webDriver.findElements(pagesListSelector).size()>0);
        return this.webDriver.findElements(pagesListSelector).size();
    }

    public int getTotalItemsInPage(){
        this.webDriver.waitForCondition(() -> this.webDriver.findElements(productTitleSelector).size()>0);
        return this.webDriver.findElements(productTitleSelector).size();
    }

    public void moveToNextPage() {
        this.webDriver.waitForAction(() ->
                this.webDriver.findElement(nextPageSelector).click());
    }

    public void moveToPage(int pageNumber) {
        this.webDriver.waitForAction(() ->
                this.webDriver.findElements(pagesListSelector).get(pageNumber-1).click());
    }

    public Product AddRandomProductToCart() {
        this.webDriver.waitForCondition(() -> this.webDriver.findElements(productTitleSelector).size()>0);
        List<WebElement> productsTitles =this.webDriver.findElements(productTitleSelector);
        int randomIndex = new Random().nextInt(productsTitles.size());
        String price;
        try{
            price = this.webDriver.findElements(productTitleSelector)
                    .get(randomIndex)
                    .findElement(productSalePriceShortSelector)
                    .getText();
        } catch (NoSuchElementException ex) {
            price = this.webDriver.findElements(productTitleSelector)
                    .get(randomIndex)
                    .findElement(productPriceShortSelector)
                    .getText();
        }
        Product product = new Product(getPageProductTitles().get(randomIndex),
                Double.parseDouble(
                        price.replaceAll("[^0-9,.]","")
                                .replace(",",".")));
        this.webDriver.waitForAction(() ->
                this.webDriver.findElements(productAddButtonSelector).get(randomIndex).click());

        return product;
    }
}

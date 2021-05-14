package test;

import models.Product;
import org.awaitility.core.ConditionTimeoutException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.Iterator;

public class CheckoutTest extends BaseTest {

    @Test
    public void searchAndProductCheckout(){
        homePage.navigationMenuComponent.buyByProductType("keyrings");
        searchPage.filterByPrice("0-20");
        searchPage.filterByAge("6+");
        assertSearch(searchPage.getTotalResultsNumber());

        searchPage.moveToPage(1);
        Product productBought = searchPage.AddRandomProductToCart();
        try{
            homePage.navigationMenuComponent.goToCart();
        } catch (ConditionTimeoutException ex) {
            addToCartModalComponent.goToCart();
        }

        assertCart(productBought);
        softly.assertAll();
    }

    private void assertSearch(int expectedProducts){
        int actualProducts = 0;
        for (int i=0; i<searchPage.getPagesNumber();i++) {
            Iterator<String> itProductTitles = searchPage.getPageProductTitles().iterator();
            Iterator<Double> itProductPrices = searchPage.getPageProductPrices().iterator();

            while (itProductTitles.hasNext() && itProductPrices.hasNext()) {
                softly.assertThat(itProductPrices.next()).isGreaterThan(0.0).isLessThan(20.0);
                softly.assertThat(itProductTitles.next().toLowerCase()).contains("llavero");
            }
            actualProducts += searchPage.getTotalItemsInPage();
            if (i<searchPage.getPagesNumber()-1) searchPage.moveToNextPage();
        }
        Assert.assertEquals(actualProducts, expectedProducts,
                String.format("expected number of products: %s, " + ", actual number of products: %s",
                        expectedProducts,
                        actualProducts));
    }

    private void assertCart(Product productBought){
        Assert.assertEquals(productBought.getPrice(), cartPage.getProductPrice(),
                String.format("expected product price: %s, " + ", actual product price: %s",
                        productBought.getPrice(),
                        cartPage.getProductPrice()));

        Assert.assertTrue(productBought.getTitle().equals(cartPage.getProductTitle()),
                String.format("expected product name: %s, " + ", actual product name: %s",
                        productBought.getTitle(),
                        cartPage.getProductTitle()));

        Assert.assertEquals(1, cartPage.getProductQuantity(),
                String.format("expected product quantity: %s, " + ", actual product quantity: %s",
                        1,
                        cartPage.getProductQuantity()));

        double expectedPrice = BigDecimal.valueOf(cartPage.getProductShippingPrice())
                .add(BigDecimal.valueOf(productBought.getPrice())).doubleValue();
        Assert.assertEquals(expectedPrice,
                cartPage.getProductTotalPrice(),
                String.format("expected final product price: %s, " + ", actual final product price: %s",
                        expectedPrice,
                        cartPage.getProductTotalPrice()));
    }
}

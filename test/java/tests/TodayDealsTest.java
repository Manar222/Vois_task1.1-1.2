package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;

public class TodayDealsTest extends TestBase {
    HomePage homeObject;
    TodayDealsPage todayDealsObject;
    ProductPage productObject;
    CartPage cartObject;

    @BeforeMethod
    public void setUp() {
        homeObject = new HomePage(driver);
        todayDealsObject = new TodayDealsPage(driver);
        productObject = new ProductPage(driver);
        cartObject = new CartPage(driver);
    }
     @Story("Navigate to Today Deals")
    @Description("Verify that user can navigate to Today Deals page successfully")
    @Test
    public void userCanNavigateToTodayDealsSuccessfully() {
        homeObject.navigateToTodayDealsPage();
    }

     @Story("Apply Filters and Add Item to Cart")
    @Description("Verify that user can apply filters and add an item to the cart from Today Deals")
    @Test
    public void userCanApplyFiltersAndAddItemToCart() throws InterruptedException {
        homeObject.navigateToTodayDealsPage();

        todayDealsObject.applyFilters();
        productObject.addItemToCart();

        Assert.assertTrue(productObject.getProductAddedAlertAssertion().contains("Added to Cart"));
    }
}

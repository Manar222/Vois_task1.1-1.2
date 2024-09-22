package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;

public class AddToCartTest extends TestBase {

    HomePage homeObject;
    SearchResultsPage searchResultsObject;
    ProductPage productObject;
    CartPage cartObject;

    @BeforeMethod
    public void setUp() {
        homeObject = new HomePage(driver);
        searchResultsObject = new SearchResultsPage(driver);
        productObject = new ProductPage(driver);
        cartObject = new CartPage(driver);
    }


    @Story("Empty Cart")
    @Description("User verifies that the cart is empty")
    @Test(description = "Verify empty cart message")
    public void verifyEmptyCartMessage() {
        homeObject.goToCart();
        Assert.assertTrue(cartObject.getEmptyCartMessage().contains("عربة تسوق Amazon الخاصة بك فارغة"), "Cart is not empty as expected.");
    }

    @Story("Search Product")
    @Description("User searches for a product")
    @Test(description = "User can search for product")
    public void userCanSearchForProduct() {
        homeObject.searchForProduct("car accessories");
        Assert.assertTrue(searchResultsObject.getSearchedKeywordTxt().contains("car accessories"));
        searchResultsObject.clickFirstItem();
        Assert.assertTrue(productObject.isProductTitlePresent());
    }

    @Story("Add To Cart")
    @Description("User adds an item to the cart")
    @Test(description = "User can add item to cart")
    public void userCanAddItemToCart() {
        userCanSearchForProduct() ;
        productObject.addItemToCart();
        Assert.assertTrue(productObject.getProductAddedAlertAssertion().contains("تمت الإضافة إلى عربة التسوق"));
        homeObject.goToCart();
        Assert.assertTrue(cartObject.isCheckoutElementPresent());
        Assert.assertTrue(cartObject.isSubtotalElementPresent());
    }

    @Story("Add Multiple Quantities")
    @Description("User adds multiple quantities of an item to the cart")
    @Test(description = "User can add multiple quantities")
    public void userCanAddMultipleQuantities() {

        userCanSearchForProduct() ;
        productObject.selectQuantity(4);
        productObject.addItemToCart();
        homeObject.goToCart();
        Assert.assertTrue(cartObject.getCartQuantity().contains("4"));

    }

    @Story("Remove Product")
    @Description("User removes a product from the cart")
    @Test(description = "User can remove product from cart")
    public void userCanRemoveProductFromCart() {

        userCanSearchForProduct() ;
        productObject.addItemToCart();
        homeObject.goToCart();
        cartObject.removeProductFromCart();
        homeObject.goToCart();
        Assert.assertTrue(cartObject.getEmptyCartMessage().contains("عربة تسوق Amazon الخاصة بك فارغة"), "Cart is not empty as expected.");

    }

    @Story("Add Multiple Items")
    @Description("User adds multiple items to the cart")
    @Test(description = "User can add multiple items")
    public void userCanAddMultipleItems() {

        userCanSearchForProduct() ;

        productObject.addItemToCart();
        homeObject.searchForProduct("car accessories");
        searchResultsObject.clickSecondItem();
        productObject.addItemToCart();
        homeObject.goToCart();
        Assert.assertEquals(homeObject.getCartCount(), "2");
    }


    @Story("Empty Search")
    @Description("User verifies behavior for empty search term")
    @Test(description = "Verify empty results for missing search term")
    public void verifyEmptyResultsForMissingSearchTerm() {
        String initialUrl = driver.getCurrentUrl();
        homeObject.searchForProduct("");

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, initialUrl, "URL changed after performing empty search.");
    }

    @Story("Product Details")
    @Description("User verifies that product details are displayed")
    @Test(description = "Verify product details are displayed")
    public void verifyProductDetailsAreDisplayed() {
        userCanSearchForProduct() ;

        Assert.assertTrue(productObject.isProductPricePresent());
        Assert.assertTrue(productObject.isProductTitlePresent());
     }

 }

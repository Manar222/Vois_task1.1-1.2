package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {

    private final WebDriver driver;

    private final By addToCartBtn = By.id("add-to-cart-button");

    private final By selectedQuantity = By.id("quantity");

    private final By productTitleTxtAssertion = By.xpath("//h1[@id='title']/span[@id='productTitle']");

    private final By productPriceAssertion = By.className("a-price-whole") ;


    private final By productAddedAlertAssertion = By.cssSelector(".sw-atc-text");


    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }


    public void selectQuantity(int quantity) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(selectedQuantity));
        selectElementByTxt(driver.findElement(selectedQuantity), String.valueOf(quantity));
    }


    public void addItemToCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn));

        clickButton(driver.findElement(addToCartBtn));
    }


    public String getProductAddedAlertAssertion() {

        return driver.findElement(productAddedAlertAssertion).getText();
    }


    public boolean isProductTitlePresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(productTitleTxtAssertion));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isProductPricePresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(productPriceAssertion));
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}

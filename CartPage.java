package pages;

import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class CartPage extends BasePage {

    private final WebDriver driver;

    private final By removeBtn = By.xpath("//input[@data-action='delete']");
    private final By subtotalAssertion = By.id("sc-subtotal-label-activecart");

    private final By checkoutAssertion = By.name("proceedToRetailCheckout");

    private final By cartEmptyMessage = By.cssSelector(".sc-your-amazon-cart-is-empty h2");

    private final By quantityAssertion = By.id("sc-subtotal-label-activecart");

    private final By cartEmptyAfterRemovingProduct = By.cssSelector("a-spacing-mini a-spacing-top-base");


    public CartPage(WebDriver driver) {
        this.driver = driver;
    }


    public void removeProductFromCart() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.elementToBeClickable(removeBtn));

        clickButton(driver.findElement(removeBtn));
    }


    public boolean isCheckoutElementPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutAssertion));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isSubtotalElementPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(subtotalAssertion));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmptyCartMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emptyCartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartEmptyMessage));
        return emptyCartElement.getText().trim();
    }

    public String getCartQuantity() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement cartQuantity = wait.until(ExpectedConditions.visibilityOfElementLocated(quantityAssertion));
        return cartQuantity.getText().trim();
    }


    public String getEmptyCartMessageAfterRemovingProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement emptyCartElement = wait.until(ExpectedConditions.visibilityOfElementLocated(cartEmptyAfterRemovingProduct));
        return emptyCartElement.getText().trim();
    }
}

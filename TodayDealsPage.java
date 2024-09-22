package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TodayDealsPage extends BasePage {
    private final WebDriver driver;

    private final By seeMoreBtn = By.xpath("//a[@aria-labelledby='see-more-departments-label']");
    private final By groceryCheck = By.xpath("//span[contains(text(),'Grocery & Gourmet Food')]");
    private final By discountBtn = By.xpath("//span[@class='a-label a-radio-label']//span[contains(text(), '10% off or more')]");
    private final By productElements = By.xpath("//a[@data-testid='product-card-link']");

    public TodayDealsPage(WebDriver driver) {
        this.driver = driver;
    }

    public void applyFilters() throws InterruptedException {
        clickButton(driver.findElement(seeMoreBtn));
        clickButton(driver.findElement(groceryCheck));

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(discountBtn));

        wait.until(ExpectedConditions.elementToBeClickable(discountBtn));
        clickButton(driver.findElement(discountBtn));

        // Wait for elements to appear before finding them
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(productElements));

        List<WebElement> elements = driver.findElements(productElements);

        if (elements.size() >= 7) {
            WebElement seventhElement = elements.get(5);
            js.executeScript("arguments[0].scrollIntoView(true);", seventhElement);
            clickButton(seventhElement);
        } else {
            System.out.println("Less than 7 elements found");
        }
    }
}

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchResultsPage extends BasePage {
    private final WebDriver driver;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }


    private final By items = By.cssSelector(".s-product-image-container a.a-link-normal");

    private final By searchedKeywordTxtAssertion = By.cssSelector("span.a-color-state.a-text-bold");


    public void clickFirstItem() {
        driver.findElements(items).getFirst().click();
    }

    public void clickSecondItem() {
        driver.findElements(items).get(4).click();
    }


    public String getSearchedKeywordTxt() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(searchedKeywordTxtAssertion)));
        return driver.findElement(searchedKeywordTxtAssertion).getText();
    }


}

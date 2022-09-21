package methods;

import com.thoughtworks.gauge.Step;
import driver.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Methods extends BaseTest {
    FluentWait<WebDriver> wait;
    JavascriptExecutor jsdriver;

    public Methods(){
        wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(30)).
                pollingEvery(Duration.ofMillis(300)).
                ignoring(NoSuchElementException.class);
        jsdriver = (JavascriptExecutor) driver;
    }

    public WebElement findElement(By by){return wait.until(ExpectedConditions.presenceOfElementLocated(by));}

    @Step("<key> Xpath'li elemente tıkla")
    public void clickByXpath(String Key) {findElement(By.xpath(Key)).click();}

    public void click(By by){
        findElement(by).click();
    }

    @Step("<key> CssSelector'lu elemente tıkla")
    public void clickByCssSelector(String Key) {findElement(By.cssSelector(Key)).click();}

    @Step("<seconds> saniye kadar bekle")
    public void waitBySeconds(long seconds) throws InterruptedException {
        try{
            Thread.sleep(seconds * 1000);
        }catch (Exception e){e.printStackTrace();}
    }

    @Step("<by> Xpath'e tıkla ve <text>'i gir")
    public void sendKeysByXpath(String key, String text){findElement(By.xpath(key)).sendKeys(text);}

    @Step("<key> Xpath'li element görünür mü?")
    public boolean isElementVisibleXpath(String key){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(key)));
            return true;
        }catch (Exception e){
            System.out.println("Element bulunamadı");
            return false;}
    }

    @Step("<key> CssSelector'lu element görünür mü?")
    public boolean isElementVisibleCssSelector(String key){
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(key)));
            return true;
        }catch (Exception e){
            System.out.println("Element bulunamadı");
            return false;}
    }

    public Select getSelect(By by){
        return new Select(findElement(by));
    }

    @Step("<by> Css Selector'den <text> text'ini seç")
    public void selectByText(String key, String text){
        getSelect(By.cssSelector(key)).selectByVisibleText(text);
    }

}
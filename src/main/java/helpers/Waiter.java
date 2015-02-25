package helpers;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Waiter {

    public static void waitForPageToLoad(){
        (new WebDriverWait(WebDriverRunner.getWebDriver(), 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return ((String) js.executeScript("return document.readyState")).equalsIgnoreCase("complete");
            }
        });
    }

    public static void waitForJquery(){
        (new WebDriverWait(WebDriverRunner.getWebDriver(), 10)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                return (Boolean) js.executeScript("return jQuery.active == 0");
            }
        });
    }

}

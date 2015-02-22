package core;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class TestBase {

    @BeforeMethod
    public void setup() throws IOException {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://the-internet.herokuapp.com";
        Configuration.timeout = 10000;
        open("/");
    }

    @AfterMethod
    public void teardown() {
        getWebDriver().quit();
    }
}

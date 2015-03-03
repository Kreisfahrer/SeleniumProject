import core.basetests.TestBase;
import org.testng.annotations.Test;
import pages.factory.HomePage;
import pages.factory.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class HowToStartTest extends TestBase {

    @Test
    public void loginTest() {
        open("", HomePage.class)
                .goTo("Form Authentication", LoginPage.class)
                .login("tomsmith", "SuperSecretPassword!")
                .shouldLogin();
    }

    @Test
    public void negativeLoginTest() {
        open("", HomePage.class)
                .goTo("Form Authentication", LoginPage.class)
                .setUserName("user")
                .setPassword("pass")
                .submit()
                .shouldNotLogin();
    }
}

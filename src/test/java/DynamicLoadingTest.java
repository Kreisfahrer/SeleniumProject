import core.basetests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.stat.DynamicLoadingPage;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DynamicLoadingTest extends TestBase {

    @BeforeMethod
    public void setup() throws Exception {
        open("/dynamic_loading");
    }

    @Test
    public void loadingTest() {
        $$(DynamicLoadingPage.EXAMPLES_LINKS).get(0).click();
        $(byText("Loading...")).shouldNot(exist);
        $(DynamicLoadingPage.START_BUTTON).click();
        $(byText("Loading...")).should(appear);
        $(DynamicLoadingPage.FINISH_MESSAGE).waitUntil(visible, 30000);
        $(DynamicLoadingPage.FINISH_MESSAGE).shouldHave(text("Hello World!"));
        open("/dynamic_loading");
        $$(DynamicLoadingPage.EXAMPLES_LINKS).get(1).click();
        $(byText("Loading...")).should(disappear);
        $(DynamicLoadingPage.START_BUTTON).click();
        $(byText("Loading...")).should(appear);
        $(DynamicLoadingPage.FINISH_MESSAGE).waitUntil(visible, 30000);
        $(DynamicLoadingPage.FINISH_MESSAGE).shouldHave(text("Hello World!"));
    }
}

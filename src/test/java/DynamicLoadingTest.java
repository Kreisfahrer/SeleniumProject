import com.codeborne.selenide.Condition;
import core.basetests.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.stat.DynamicLoadingPage;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class DynamicLoadingTest extends TestBase {

    @BeforeMethod
    public void setup() throws Exception {
        super.setup();
        open("/dynamic_loading");
    }

    @Test
    public void loadingTest() {
        $$(DynamicLoadingPage.EXAMPLES_LINKS).get(0).click();
        $(byText("Loading...")).shouldNot(Condition.exist);
        $(DynamicLoadingPage.START_BUTTON).click();
        $(byText("Loading...")).should(Condition.exist);
        $(DynamicLoadingPage.FINISH_MESSAGE).waitUntil(Condition.visible, 30000);
        $(DynamicLoadingPage.FINISH_MESSAGE).shouldHave(Condition.text("Hello World!"));
        open("/dynamic_loading");
        $$(DynamicLoadingPage.EXAMPLES_LINKS).get(1).click();
        $(byText("Loading...")).shouldNot(Condition.exist);
        $(DynamicLoadingPage.START_BUTTON).click();
        $(byText("Loading...")).should(Condition.exist);
        $(DynamicLoadingPage.FINISH_MESSAGE).waitUntil(Condition.visible, 30000);
        $(DynamicLoadingPage.FINISH_MESSAGE).shouldHave(Condition.text("Hello World!"));
    }
}

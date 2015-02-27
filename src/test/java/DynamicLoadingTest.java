import com.codeborne.selenide.Condition;
import core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.stat.DynamicLoadingPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class DynamicLoadingTest extends TestBase {

    @BeforeMethod
    public void setup() throws IOException {
        super.setup();
        open("/dynamic_loading");
    }

    @Test
    public void loadingTest() {
        $$(DynamicLoadingPage.EXAMPLES_LINKS).get(0).click();
        $(DynamicLoadingPage.START_BUTTON).click();
        $(DynamicLoadingPage.FINISH_MESSAGE).waitUntil(Condition.visible, 30000);
        open("/dynamic_loading");
        $$(DynamicLoadingPage.EXAMPLES_LINKS).get(1).click();
        $(DynamicLoadingPage.START_BUTTON).click();
        $(DynamicLoadingPage.FINISH_MESSAGE).waitUntil(Condition.visible, 30000);
    }
}

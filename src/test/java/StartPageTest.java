import core.basetests.TestBase;
import helpers.Actions;
import org.testng.annotations.Test;
import pages.stat.StartPage;

import java.io.IOException;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selenide.$$;

public class StartPageTest extends TestBase {
    private static final String LINK_TEXT_FILE_NAME = "LinkText.txt";

    @Test
    public void linkTest() throws IOException {
        StartPage.shouldAppearAndExactTextShouldBeVisible("Welcome to the Internet");
        $$(StartPage.LINKS).shouldHave(size(35), exactTexts(Actions.getStringsFromResourceFile(LINK_TEXT_FILE_NAME)));
    }
}

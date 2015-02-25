import com.codeborne.selenide.Condition;
import core.TestBase;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.stat.DropdownPage;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DropdownTest extends TestBase {

    @BeforeMethod
    public void setup() throws IOException {
        super.setup();
        open("/dropdown");
    }

    @Test
    public void UIDropdownTest() {
        $(DropdownPage.DROPDOWN).selectOption("Option 1");
        $(DropdownPage.DROPDOWN).shouldHave(Condition.value("1"));
        $(DropdownPage.DROPDOWN).selectOption("Option 2");
        $(DropdownPage.DROPDOWN).shouldHave(Condition.value("2"));
    }
}

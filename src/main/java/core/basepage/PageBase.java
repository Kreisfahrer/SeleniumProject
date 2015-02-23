package core.basepage;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class PageBase {
    public static void shouldAppearAndExactTextShouldBeVisible(String textOnPage) {
        $(byText(textOnPage)).shouldBe(Condition.visible);
    }
}

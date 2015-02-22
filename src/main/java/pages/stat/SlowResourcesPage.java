package pages.stat;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static helpers.Locators.get;

public class SlowResourcesPage {
    public static final By SLOW_RESOURCES_TITLE = get("slowResources.title");

    public static boolean isSlowResourcesPage() {
        return $(SLOW_RESOURCES_TITLE).isDisplayed();
    }
}

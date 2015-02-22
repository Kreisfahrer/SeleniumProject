package pages.stat;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$$;

public class SecureDownloadPage {
    private static ElementsCollection references = $$(By.xpath("//div[@class='example']/a"));

    public static void downloadFile(String fileRef) {
        for (SelenideElement element : references) {
            if (element.text().equals(fileRef)) {
                element.click();
                break;
            }
        }
    }
}

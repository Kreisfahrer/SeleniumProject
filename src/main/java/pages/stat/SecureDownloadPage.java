package pages.stat;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class SecureDownloadPage {

    public static void downloadFile(String fileRef) {
        $(byText(fileRef)).click();
    }
}

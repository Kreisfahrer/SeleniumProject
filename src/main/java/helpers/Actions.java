package helpers;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Actions {

    public static String [] getStringsFromFile(String file) throws IOException {
        List<String> strings = FileUtils.readLines(new File(file));
        return strings.toArray(new String[strings.size()]);
    }

    public static String [] getStringsFromResourceFile(String fileName) throws IOException {
        String path = Actions.class.getResource("/" + fileName).getPath();
        return getStringsFromFile(path);
    }

    @Attachment(value = "{0}", type = "image/png")
    public static byte[] createAttachment(String name) {
        TakesScreenshot screenshot = (TakesScreenshot) getWebDriver();
        return screenshot.getScreenshotAs(OutputType.BYTES);
    }
}

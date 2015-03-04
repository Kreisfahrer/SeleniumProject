package helpers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class EnvironmentWriter {
    private static  Properties envProp;

    public static void collectEnvironmentProperties() {
        Capabilities caps = ((RemoteWebDriver)getWebDriver()).getCapabilities();
        envProp = new Properties();

        envProp.setProperty("OS:", caps.getPlatform().name());
        envProp.setProperty("Browser Name", caps.getBrowserName());
        envProp.setProperty("Browser Version:", caps.getVersion());
        envProp.setProperty("Base URL:", Configuration.baseUrl);
    }

    public static void writeEnvironmentProperties() {
        String path = System.getProperty("user.dir") + "/target/allure-results/";

        try (OutputStream outputStream = new FileOutputStream(path + "environment.properties")) {
            envProp.store(outputStream, "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

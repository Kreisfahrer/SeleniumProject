import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import core.TestBase;
import helpers.FileDownloader;
import helpers.FileUtils;
import helpers.WebDriverSingleton;
import net.lightbody.bmp.proxy.ProxyServer;
import org.apache.http.HttpResponseInterceptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.stat.SecureDownloadPage;
import pages.stat.StartPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

import java.io.File;
import java.net.UnknownHostException;

import static com.codeborne.selenide.Selenide.open;


public class SecureFileDownloadTest extends TestBase {

    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final String CONTENT_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String CONTENT_TYPE_APPLICATION_PDF = "application/pdf";
    public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";
    public static final String FILE_TO_DOWNLOAD = "avatar.jpg";
    private ProxyServer bmp;
    private WebDriver driver;

    @BeforeMethod
    @Override
    public void setup() {

        Configuration.baseUrl = "http://the-internet.herokuapp.com";
        Configuration.timeout = 10000;

        String browser = System.getProperty("browser", "chrome");

        bmp = new ProxyServer(8071);
        try {
            bmp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bmp.autoBasicAuthorization("", "admin", "admin");


        HttpResponseInterceptor downloader = new FileDownloader()
                .addContentType(CONTENT_TYPE_IMAGE_JPEG)
                .addContentType(CONTENT_TYPE_APPLICATION_OCTET_STREAM)
                .addContentType(CONTENT_TYPE_APPLICATION_PDF)
                .addContentType(CONTENT_TYPE_IMAGE_PNG);
        bmp.addResponseInterceptor(downloader);


        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            caps.setCapability(CapabilityType.PROXY, bmp.seleniumProxy());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        driver = WebDriverSingleton.initDriver(browser, caps);
        WebDriverRunner.setWebDriver(driver);
        open("/");

    }

    @AfterMethod
    public void afterMethod() throws Exception {
        WebDriverRunner.closeWebDriver();
        bmp.stop();
    }


    @Test
    public void secureDownloadTest() throws Exception {

        StartPage.open("Secure File Download");

        $(byText("Secure File Downloader")).shouldBe(visible);
        SecureDownloadPage.downloadFile(FILE_TO_DOWNLOAD);


        File downloadedFile = new File(driver.findElement(By.tagName("body")).getText());
        System.out.println(downloadedFile);
        System.out.println(FileUtils.generateMD5(downloadedFile));
        Assert.assertTrue(downloadedFile.exists());
        Assert.assertEquals(FileUtils.generateMD5(downloadedFile), "d56900c1544ce6a413d1e0dee32e98fc");

    }
}

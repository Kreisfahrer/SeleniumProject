import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import core.TestBase;
import helpers.FileDownloader;
import helpers.FileUtils;
import net.lightbody.bmp.proxy.ProxyServer;
import org.apache.http.HttpResponseInterceptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.stat.SecureDownloadPage;
import pages.stat.StartPage;

import java.io.File;
import java.net.UnknownHostException;

import static com.codeborne.selenide.Selenide.open;


public class SecureFileDownloadTest extends TestBase {

    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final String CONTENT_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    private ProxyServer bmp;
    private WebDriver driver;

    @BeforeMethod
    @Override
    public void setup() {
//        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://the-internet.herokuapp.com";
        Configuration.timeout = 10000;

        bmp = new ProxyServer(8071);
        try {
            bmp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        bmp.autoBasicAuthorization("", "admin", "admin");
//        bmp.setConnectionTimeout(100000);

        HttpResponseInterceptor downloader = new FileDownloader()
                .addContentType(CONTENT_TYPE_IMAGE_JPEG)
                .addContentType(CONTENT_TYPE_APPLICATION_OCTET_STREAM);
        bmp.addResponseInterceptor(downloader);


        DesiredCapabilities caps = new DesiredCapabilities();
        try {
            caps.setCapability(CapabilityType.PROXY, bmp.seleniumProxy());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        driver = new ChromeDriver(caps);
        WebDriverRunner.setWebDriver(driver);
        open("/");

    }

    @AfterMethod
    public void afterMethod() throws Exception {
        driver.quit();
        bmp.stop();
    }

    @Test
    public void secureDownloadTest() throws Exception {

        StartPage.open("Secure File Download");

        Assert.assertTrue(driver.getPageSource().contains("Secure File Downloader"));
        SecureDownloadPage.downloadFile("avatar.jpg");


        File downloadedFile = new File(driver.findElement(By.tagName("body")).getText());
        System.out.println(downloadedFile);
        System.out.println(FileUtils.generateMD5(downloadedFile));
        Assert.assertTrue(downloadedFile.exists());
        Assert.assertEquals(FileUtils.generateMD5(downloadedFile), "d56900c1544ce6a413d1e0dee32e98fc");
    }
}

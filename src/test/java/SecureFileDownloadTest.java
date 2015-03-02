import core.basetests.ProxyTestBase;
import helpers.FileDownloader;
import helpers.FileUtils;
import org.apache.http.HttpResponseInterceptor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.stat.SecureDownloadPage;
import pages.stat.StartPage;

import java.io.File;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class SecureFileDownloadTest extends ProxyTestBase {

    public static final String CONTENT_TYPE_IMAGE_JPEG = "image/jpeg";
    public static final String CONTENT_TYPE_APPLICATION_OCTET_STREAM = "application/octet-stream";
    public static final String CONTENT_TYPE_APPLICATION_PDF = "application/pdf";
    public static final String CONTENT_TYPE_IMAGE_PNG = "image/png";
    public static final String FILE_TO_DOWNLOAD = "avatar.jpg";
    private WebDriver driver;

    @BeforeMethod
    @Override
    public void setup() throws Exception {

        getProxy().autoBasicAuthorization("", "admin", "admin");
        getProxy().setCaptureContent(true);
        getProxy().setCaptureHeaders(true);
        getProxy().setCaptureBinaryContent(true);

        HttpResponseInterceptor downloader = new FileDownloader()
                .addContentType(CONTENT_TYPE_IMAGE_JPEG);
        getProxy().addResponseInterceptor(downloader);

        super.setup();
    }


    @Test
    public void secureDownloadTest() throws Exception {

        StartPage.open("Secure File Download");

        $(byText("Secure File Downloader")).shouldBe(visible);
        SecureDownloadPage.downloadFile(FILE_TO_DOWNLOAD);


        File downloadedFile = new File($(By.tagName("body")).getText());
        Assert.assertTrue(downloadedFile.exists());
        Assert.assertEquals(FileUtils.generateMD5(downloadedFile), "d56900c1544ce6a413d1e0dee32e98fc");

    }
}

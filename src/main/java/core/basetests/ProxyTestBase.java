package core.basetests;

import com.codeborne.selenide.WebDriverRunner;
import net.lightbody.bmp.core.har.Har;
import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.openqa.selenium.net.PortProber.findFreePort;

public class ProxyTestBase extends TestBase{
    protected ProxyServer proxyServer;
    protected Har har;

    @Override
    @BeforeMethod
    public void setup() throws Exception {
        if(!getWebDriver().toString().contains("(null)")) {
            getWebDriver().quit();
        }
        proxyServer = new ProxyServer(findFreePort());
        proxyServer.start();
        WebDriverRunner.setProxy(proxyServer.seleniumProxy());
        super.setup();
    }


    @AfterMethod
    public void stopBrowserMobProxyServer() throws Exception {
        WebDriverRunner.setProxy(null);
        proxyServer.stop();
        getWebDriver().quit();
    }
}

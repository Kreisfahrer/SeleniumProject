package core.basetests;

import com.codeborne.selenide.WebDriverRunner;
import net.lightbody.bmp.proxy.ProxyServer;
import org.testng.annotations.BeforeMethod;

public class ProxyTestBase extends TestBase {
    private static ProxyServer proxyServer;

    @Override
    @BeforeMethod
    public void setup() throws Exception {
        WebDriverRunner.setProxy(getProxy().seleniumProxy());
        super.setup();
    }

    protected static ProxyServer getProxy() throws Exception {
        if (proxyServer == null) {
            proxyServer = new ProxyServer(8081);
            proxyServer.start();
        }
        return proxyServer;
    }
}

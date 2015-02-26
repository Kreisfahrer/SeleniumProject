import core.basetests.ProxyTestBase;
import helpers.CustomListener;
import net.lightbody.bmp.core.har.HarEntry;
import net.lightbody.bmp.core.har.HarRequest;
import net.lightbody.bmp.core.har.HarResponse;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.stat.SlowResourcesPage;
import pages.stat.StartPage;

import static com.codeborne.selenide.Selenide.sleep;

@Listeners(CustomListener.class)
public class ResponseCodeTest extends ProxyTestBase {

    @Test
    public void responseCodeTest() throws Exception {
        getProxy().newHar("slowResources");
        StartPage.open("Slow Resources");
        SlowResourcesPage.shouldAppearAndExactTextShouldBeVisible("Slow Resources");
        sleep(30000);
        for(HarEntry entry : getProxy().getHar().getLog().getEntries()) {
            HarRequest request = entry.getRequest();
            HarResponse response = entry.getResponse();
            Assert.assertEquals(response.getStatus(), 200,
                    "URL: " + request.getUrl() + " has bad response status");
        }
    }
}

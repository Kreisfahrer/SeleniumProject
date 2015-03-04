package core.basetests;

import com.codeborne.selenide.Configuration;
import helpers.CustomListener;
import helpers.EnvironmentWriter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import static com.codeborne.selenide.Selenide.open;

@Listeners({CustomListener.class})
public class TestBase {
    
    protected static final String BASE_URL = "http://the-internet.herokuapp.com";
    
    @BeforeTest
    public void configure() {
        Configuration.baseUrl = System.getProperty("baseUrl", BASE_URL);
        Configuration.timeout = 10000;
        EnvironmentWriter.collectEnvironmentProperties();
    }

    @BeforeMethod
    public void setup() throws Exception {
        open("/");
    }

    @AfterTest
    public void writeProperties() {
        EnvironmentWriter.writeEnvironmentProperties();
    }
}

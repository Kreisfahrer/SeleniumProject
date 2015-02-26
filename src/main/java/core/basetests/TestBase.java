package core.basetests;

import com.codeborne.selenide.Configuration;
import helpers.EnvironmentWriter;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class TestBase {

    @BeforeMethod
    public void setup() throws Exception {
        Configuration.browser = "chrome";
        Configuration.baseUrl = "http://the-internet.herokuapp.com";
        Configuration.timeout = 10000;
        open("/");
    }

    @BeforeGroups
    public void writeProperties() {
        EnvironmentWriter.writeEnvironmentProperties();
    }
}

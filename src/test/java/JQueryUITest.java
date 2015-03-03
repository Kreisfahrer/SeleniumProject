import com.codeborne.selenide.Condition;
import core.basetests.TestBase;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class JQueryUITest extends TestBase {

    @Test
    public static void startTest(){
        open("/jqueryui/menu");
        $("#ui-id-2").click();
        $("#ui-id-4").waitUntil(Condition.appears, 1000).click();
        $("#ui-id-6").waitUntil(Condition.appears, 1000).click();
        sleep(5000);
    }


}

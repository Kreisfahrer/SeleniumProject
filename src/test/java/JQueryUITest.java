import core.basetests.TestBase;
import org.testng.annotations.Test;
import static pages.stat.JQueryUIPage.*;

public class JQueryUITest extends TestBase{

    @Test
    public static void startTest(){
        start();
        downloadsMenu();
        downloadPDF();
    }


}

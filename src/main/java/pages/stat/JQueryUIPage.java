package pages.stat;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class JQueryUIPage {

    public static void start() {
        open("/jqueryui/menu");

    }

    public static void downloadsMenu(){
        $("#ui-id-2").click();
        $("#ui-id-4").waitUntil(Condition.appears, 1000).click();
    }

    public static void downloadPDF(){
        $("#ui-id-6").waitUntil(Condition.appears, 1000).click();
        sleep(5000);
    }

}

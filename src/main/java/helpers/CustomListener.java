package helpers;

import com.codeborne.selenide.testng.BrowserPerTest;
import org.testng.ITestResult;

public class CustomListener extends BrowserPerTest {
    @Override
    public void onTestFailure(ITestResult result) {
        Actions.createAttachment(result.getMethod().getMethodName());
        super.onTestFailure(result);
    }
}

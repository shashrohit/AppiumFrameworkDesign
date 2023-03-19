package TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.shashank.android.utils.AppiumUtils;
import io.appium.java_client.AppiumDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.IOException;

public class Listeners implements ITestListener {

    ExtentReports report = ExtentReporterNG.getReporterObject();
    ExtentTest test;

    @Override
    public void onTestStart(ITestResult iTestResult) {
        test = report.createTest(iTestResult.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        test.log(Status.PASS, "Passed");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        test.log(Status.FAIL, "Failed");
        AppiumDriver driver;
        try {
            driver = (AppiumDriver) iTestResult.getTestClass().getRealClass().getField("driver").get(iTestResult.getInstance());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        try {
            String screenshotPath = AppiumUtils.takeScreenshot(iTestResult.getMethod().getMethodName(), driver);
            System.out.println(screenshotPath);
            test.addScreenCaptureFromPath(screenshotPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        report.flush();
    }
}

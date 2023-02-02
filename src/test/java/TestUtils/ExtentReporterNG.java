package TestUtils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReporterObject(){
        String reportPath = System.getProperty("user.dir") + "/reports/index.html";

        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setReportName("Appium Automation Run Results");
        reporter.config().setDocumentTitle("General Store App Automation Results");

        ExtentReports reports = new ExtentReports();
        reports.attachReporter(reporter);
        reports.setSystemInfo("Tester", "Shashank");
        return reports;
    }

}

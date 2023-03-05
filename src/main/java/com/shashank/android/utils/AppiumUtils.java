package com.shashank.android.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import static com.shashank.android.utils.Constants.REPORT_PATH;

public class AppiumUtils {

    public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
        String content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath),
                new TypeReference<List<HashMap<String, String>>>() {});
    }

    public AppiumDriverLocalService startAppiumService(String ipAddress, int port){
        AppiumDriverLocalService service = new AppiumServiceBuilder()
                .withAppiumJS(new File("C:\\Users\\DS\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress(ipAddress).usingPort(port).build();
//        service.start();
        return service;
    }

    public String getProperty(String propertyFilePath, String propertyName) throws IOException {
        Properties prop = new Properties();
        FileInputStream fip = new FileInputStream(propertyFilePath);
        prop.load(fip);
        return prop.getProperty(propertyName);
    }

    public String takeScreenshot(String testName, AppiumDriver driver) throws IOException {
        String screenshotPath = REPORT_PATH + testName + ".png";
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(source, new File(screenshotPath));
        return screenshotPath;
    }
}

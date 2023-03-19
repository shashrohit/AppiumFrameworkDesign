package com.shashank.android.bdd.glue;

import com.shashank.android.poms.login.impl.LogInPage;
import com.shashank.android.utils.AppiumUtils;
import com.shashank.android.utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.Before;
import java.io.IOException;
import java.net.URL;

public class Hooks {
    private AndroidDriver driver;
    private AppiumDriverLocalService service;
    private static LogInPage logInPage;

    private String appiumServiceIP;
    private int appiumServicePort;

    @Before
    public void setUp() throws IOException, InterruptedException {
        startAppiumLocalService();
        startAndroidDriver();
        logInPage = new LogInPage(driver);
        waitForApkToOpen();
    }

    private void startAppiumLocalService() throws IOException {
        appiumServiceIP = AppiumUtils.getProperty(Constants.PROPERTY_FILE_PATH, "ipAddress");
        appiumServicePort= Integer.parseInt(AppiumUtils.getProperty(Constants.PROPERTY_FILE_PATH, "port"));
        service = AppiumUtils.startAppiumService(appiumServiceIP, appiumServicePort);
    }

    private void startAndroidDriver() throws IOException {
        String deviceName = AppiumUtils.getProperty(Constants.PROPERTY_FILE_PATH, "androidDeviceName");
        UiAutomator2Options options = new UiAutomator2Options();
        options.setDeviceName(deviceName);
        options.setApp(Constants.APK_PATH);
        driver = new AndroidDriver(new URL(getAppiumUrl(appiumServiceIP, appiumServicePort)), options);
    }

    private String getAppiumUrl(String ip, int port){
        return "http://" + ip + ":" + port;
    }

    public static LogInPage getLogInPageObject(){
        return logInPage;
    }

    private void waitForApkToOpen() throws InterruptedException {
        Thread.sleep(5000);
    }
}

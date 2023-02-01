package com.shashank.android.utils;

import io.appium.java_client.AppiumDriver;

public class AppiumUtils {
    protected AppiumDriver driver;
    public AppiumUtils(AppiumDriver driver){
        this.driver = driver;
    }
}

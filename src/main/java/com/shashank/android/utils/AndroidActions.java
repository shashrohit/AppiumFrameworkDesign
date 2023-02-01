package com.shashank.android.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils {

    public AndroidActions(AppiumDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void longPress(WebElement element){
        if(WrapsElement.class.isAssignableFrom(element.getClass()))
            element = ((WrapsElement)element).getWrappedElement();
        ((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
                "elementId", ((RemoteWebElement) element).getId(), "duration", 2000
        ));
    }

    public void scrollIntoView(String text){
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+ text +"\"));"));
    }

    public void swipe(WebElement element, String direction){
        ((JavascriptExecutor)driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
                ((RemoteWebElement)element).getId(), "direction", direction,
                "percent", 0.75));
    }
}

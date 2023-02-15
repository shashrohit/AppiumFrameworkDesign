package com.shashank.android.utils;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class AndroidActions extends AppiumUtils {

    protected AndroidDriver driver;
    public AndroidActions(AndroidDriver driver){
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

    public void navigateBack(){
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
    }
}

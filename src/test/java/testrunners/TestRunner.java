package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

@CucumberOptions(monochrome = true, features = "src/test/java/features", glue = {"com.shashank.android.bdd"}, tags="@p0")
public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }
}

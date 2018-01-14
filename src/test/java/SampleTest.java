/**
 * Created by chenli on 14/01/2018.
 */

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SampleTest {

    private IOSDriver driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "IOS");
        desiredCapabilities.setCapability("deviceName", "iPhone 8");
        desiredCapabilities.setCapability("app", "/Users/chenli/Documents/Projects/IOS/shoppingIOS.app");
        desiredCapabilities.setCapability("automationName", "XCUITest");
        desiredCapabilities.setCapability("udid", "3A29CEB0-E948-45FC-9A43-7E2C6ECAB732");

        URL remoteUrl = new URL("http://localhost:4723/wd/hub");

        driver = new IOSDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void sampleTest() {
        MobileElement el1 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"shoppingIOS\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeTextField[1]");
        el1.sendKeys("test");
        MobileElement el2 = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"shoppingIOS\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeTextField[2]");
        el2.sendKeys("test");
        MobileElement el3 = (MobileElement) driver.findElementByAccessibilityId("Toolbar Done Button");
        el3.click();
        MobileElement el4 = (MobileElement) driver.findElementByAccessibilityId("登录");
        el4.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

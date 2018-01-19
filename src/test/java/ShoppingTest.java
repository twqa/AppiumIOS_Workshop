import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

/**
 * Created by chenli on 11/12/2017.
 */
public class ShoppingTest {
    AppiumDriver driver;
    DesiredCapabilities capabilities = new DesiredCapabilities();

    @BeforeTest
    @Parameters({"port", "Device_name","wda"})
    public void setUp(String port, String Device_name, String wda) throws Exception {

        capabilities.setCapability("app", "/Users/chenli/Documents/Projects/IOS/shoppingIOS.app");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "11.2");
        capabilities.setCapability("deviceName", Device_name);
        capabilities.setCapability("wdaLocalPort", wda);
        driver = new IOSDriver(new URL("http://localhost:" + port + "/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(25, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void searchFood() throws InterruptedException {

        //Login
        WebElement AccountTextField = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeApplication[@name=\"shoppingIOS\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeTextField[1]")));
        AccountTextField.sendKeys("test");

        WebElement PasswordTextField;
        PasswordTextField = driver.findElement(By.xpath("//XCUIElementTypeApplication[@name=\"shoppingIOS\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeTextField[2]"));
        PasswordTextField.sendKeys("test");

        WebElement DoneButton;
        DoneButton = driver.findElement(By.id("Toolbar Done Button"));
        DoneButton.click();

        WebElement LoginButton;
        LoginButton = driver.findElement(By.id("登录"));
        LoginButton.click();
        WebElement textMessage = (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//XCUIElementTypeOther[@name=\"附近美食\"]")));
        assertEquals("附近美食", textMessage.getText());

        //Search and select
        WebElement SearchBox;
        SearchBox = driver.findElement(By.id("搜索美食"));
        SearchBox.sendKeys("乌冬面");
        WebElement food = driver.findElement(By.xpath("(//XCUIElementTypeStaticText[@name=\"乌冬面\"])[2]"));
        assertEquals("乌冬面", food.getText());

        //Buy
        WebElement SelectedItem;
        SelectedItem = driver.findElement(By.xpath("//XCUIElementTypeTable[@name=\"搜索结果\"]/XCUIElementTypeCell"));
        SelectedItem.click();
        WebElement Type;
        Type = driver.findElement(By.id("套餐一"));
        Type.click();
        WebElement Taste;
        Taste = driver.findElement(By.id("特辣"));
        Taste.click();
        WebElement buyButton;
        buyButton = driver.findElement(By.id("立即购买"));
        buyButton.click();

    }


}

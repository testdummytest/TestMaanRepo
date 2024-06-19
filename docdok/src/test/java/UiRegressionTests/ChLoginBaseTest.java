package UiRegressionTests;

import io.appium.java_client.android.AndroidDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class ChLoginBaseTest {
    public WebDriver driver ;
    public AndroidDriver androidDriver ;

    @BeforeMethod
    public void setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        options.addArguments("--verbose");
        options.addArguments("--window-size=1280,800");
        options.addArguments("webdriver.chrome.whitelistedIps= ");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://qa.dev.docdok.ch");
        Thread.sleep(1000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();

    }
}

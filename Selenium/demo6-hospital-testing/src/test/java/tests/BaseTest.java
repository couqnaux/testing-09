package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // B1: Cấu hình ChromeDriver
        WebDriverManager.chromedriver().setup();

        // B2: Cấu hình các tùy chọn
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");

        // B3: Khởi tạo ChromeDriver
        driver = new ChromeDriver(options);

        // B4: Đợi 10s
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

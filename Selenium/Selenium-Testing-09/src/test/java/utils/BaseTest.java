package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;

    //Hàm khởi tạo môi trường test
    @BeforeMethod
    public void setup() {
        //B1: Cấu hình ChromeDriver
        WebDriverManager.chromedriver().setup();

        //B2: Cấu hình các tùy chọn
        ChromeOptions options = new ChromeOptions();
        //Mở chrome ở chế độ fullscreen
        options.addArguments("--start-maximized");

        //B3: Khởi tạo ChromeDriver
        driver = new ChromeDriver(options);

        //B4: setting thời gian đợi khởi tạo chrome
        //Nếu chrome tạo sớm hơn 10s => tiếp tục ngay
        //Nếu quá 10s thì báo lỗi
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Hàm clear môi trường sau khi test xong 1 case
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

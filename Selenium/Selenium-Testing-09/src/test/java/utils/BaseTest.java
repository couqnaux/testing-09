package utils;

import com.aventstack.extentreports.ExtentTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected ExtentTest extentTest;

    //Hàm khởi tạo extent report trước khi chạy tất cả test
    @BeforeSuite
    public void setupSuite() {
        ExtendReportManager.getInstance();

    }

    @AfterSuite
    public void tearDownSuite() {
        ExtendReportManager.flushReport();
    }

    //Hàm khởi tạo môi trường test
    @BeforeMethod
    public void setup(ITestResult result) {

        //Tạo testcase trong report
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();

        if (testDescription == null || testDescription.isEmpty()) {
            testDescription = "test case " + testName;
        }

        extentTest = ExtendReportManager.createTest(testName, testDescription);
        extentTest.info("Bat dau test case: " + testName);
        //B1: Cấu hình ChromeDriver
        WebDriverManager.chromedriver().setup();
        extentTest.info("Chrome driver setup");

        //B2: Cấu hình các tùy chọn
        extentTest.info("Cau hinh chrome full screen");
        ChromeOptions options = new ChromeOptions();
        //Mở chrome ở chế độ fullscreen
        options.addArguments("--start-maximized");

        //B3: Khởi tạo ChromeDriver
        extentTest.info("Khoi tao ChromeDriver");
        driver = new ChromeDriver(options);

        //B4: setting thời gian đợi khởi tạo chrome
        //Nếu chrome tạo sớm hơn 10s => tiếp tục ngay
        //Nếu quá 10s thì báo lỗi
        extentTest.info("Setup implicit wait 10s");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    //Hàm clear môi trường sau khi test xong 1 case
    @AfterMethod
    public void tearDown(ITestResult result) {
        //Kiểm tra kết quả và log vào report
        if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.pass("Testcase passed: " + result.getMethod().getMethodName());
        }

        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.fail("Testcase failed: " + result.getMethod().getMethodName());
            if (result.getThrowable() != null) {
                extentTest.fail(result.getThrowable());
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}

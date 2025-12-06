package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BookingPage {
    // Viết testcase cho flow booking lịch khám
    // B1: login cho acc patient
    // B2: Tìm menu "Đặt lịch khám" trong HomePage và click vào đó
    // B3: select chi nhánh muốn khám
    // B4: select doctor ở chi nhánh đã chọn
    // B5: pick ngày khám - November 30, 2025
    // B6: pick  giờ khám - 06:00 - 17:00
    // B7: chọn gói khám bệnh
    // B8: booking lịch khám

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;

    // Tìm element
    // Menu Đặt lịch khám
    //<a class="nav-link active text-info" href="/booking" data-discover="true" aria-current="page">Đặt lịch khám</a>
    private By bookingMenuLink = By.xpath("//a[@href='/booking' and text()='Đặt lịch khám']");

    // Chọn chi nhánh
//    <div class="mb-3">
//        <label class="form-label">Chi nhánh</label>
//        <select class="form-select">
//            <option value="">Chọn chi nhánh</option>
//            <option value="1">Chi nhánh trung tâm</option>
//        </select>
//    </div>
    private By branchSelect = By.xpath("//label[text()='Chi nhánh']/../select");

    private By doctorSelect = By.xpath("//label[text()='Bác sĩ']/../select");

    private static final String dateAppointment = "//button[.//abbr[@aria-label='%s' and text()='%s']]";
//    private By dateAppointment = By.xpath("//button[.//abbr[@aria-label='%s' and text()='%s']]");

    public BookingPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.js = (JavascriptExecutor) driver;
    }

    // B1: login (dùng lại từ login page)

    // B2: openBookingFromMenu
    public void openBookingFromMenu() throws Exception{
        Thread.sleep(2000);
//        WebElement bookingMenu = driver.findElement(bookingMenuLink);
//        bookingMenu.click();

        //Cách 2:
        WebElement bookingMenuEx = wait.until(ExpectedConditions.elementToBeClickable(bookingMenuLink));
        bookingMenuEx.click();
    }

    public void selectBranch(String branchName) throws Exception {
        WebElement branchElement = wait.until(ExpectedConditions.presenceOfElementLocated(branchSelect));

        //Scroll xuống element muốn select
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", branchElement);

        Select select = new Select(branchElement);
        select.selectByVisibleText(branchName);
    }

    public void selectDoctor(String doctorName) throws Exception {
        WebElement doctorElement = wait.until(ExpectedConditions.presenceOfElementLocated(doctorSelect));

        Select select = new Select(doctorElement);
        select.selectByVisibleText(doctorName);
    }

    public void pickDateAppointment(String date) throws Exception {
        // B1: Mapping date vào xpath
        String[] parts = date.split(" ");
        String day = parts[1].replace(",","");
        String appointmentXpath = String.format(dateAppointment, date, day);

        WebElement dateButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(appointmentXpath)));
        dateButton.click();
    }
}

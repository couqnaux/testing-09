package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenShotHelper;

import java.time.Duration;
import java.util.List;

public class AdminPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ScreenShotHelper screenShotHelper;

    //find element

    private final By userInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");

    private final By userRoleDropDown = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[1]");

    private final By adminRoleOption = By.xpath("//div[@role='option']//span[text()='Admin']");

    private final By statusDropdown = By.xpath("(//div[@class='oxd-select-text oxd-select-text--active'])[2]");

    private final By enableOption = By.xpath("(//div[@role='option'])[2]");

    private final By searchButton = By.xpath("//button[text()=' Search ']");

    private final By dataRows = By.xpath("//div[@class='oxd-table-card']");

    public AdminPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.screenShotHelper = new ScreenShotHelper(driver, "AdminPage");
    }

    public void enterAdminName() throws Exception {
        WebElement input = driver.findElement(userInput);
        screenShotHelper.captureHighlight(input, "Enter_Admin_Name");
        input.clear();
        input.sendKeys("Admin");
    }

    public void selectUserRole() throws Exception {
        WebElement dropdown = driver.findElement(userRoleDropDown);
        screenShotHelper.captureHighlight(dropdown, "Select_User_Role");
        dropdown.click();
        Thread.sleep(1000);

        WebElement adminOption = driver.findElement(adminRoleOption);
        adminOption.click();
    }

    public void selectStatus() throws Exception {
        WebElement dropdown = driver.findElement(statusDropdown);
        screenShotHelper.captureHighlight(dropdown, "Select_Status");
        dropdown.click();
        Thread.sleep(1000);

        WebElement enableStatus = driver.findElement(enableOption);
        enableStatus.click();
    }

    public void clickSearchButton() throws Exception {
        WebElement button = driver.findElement(searchButton);
        screenShotHelper.captureHighlight(button, "Click_Search_Button");
        button.click();
    }

    public boolean hasTableData() {
        List<WebElement> rows = driver.findElements(dataRows);
        return rows.size() > 0;
    }
}

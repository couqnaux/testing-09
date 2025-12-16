package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ScreenShotHelper;
import java.time.Duration;

public class MyInfoPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private ScreenShotHelper screenShotHelper;

    //find element
    private final By avatarWrapper = By.cssSelector(".orangehrm-edit-employee-image-wrapper");
    private final By uploadBtn = By.cssSelector("button.employee-image-action");
    private final By fileInput = By.cssSelector("input[type='file']");


    public MyInfoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.screenShotHelper = new ScreenShotHelper(driver, "MyInfoPage");
    }

    public void clickEmployeeImage() throws Exception {
        WebElement employeeImage = driver.findElement(avatarWrapper);
        screenShotHelper.captureHighlight(employeeImage, "Click_Employee_Image");
        employeeImage.click();
    }

    public void clickUploadBtn() throws Exception {
        WebElement btn = driver.findElement(uploadBtn);
        screenShotHelper.captureHighlight(btn, "Click_Upload_Button");
        btn.click();
    }

    public void chooseImage(String filePath) throws Exception {
        WebElement uploadInput = driver.findElement(fileInput);
//        js.executeScript(
//                "arguments[0].style.display='block';" +
//                        "arguments[0].style.visibility='visible';",
//                uploadInput
//        );
        Thread.sleep(500);
        screenShotHelper.captureHighlight(uploadInput, "Choose_Image");
        uploadInput.sendKeys(filePath);
        Thread.sleep(3000);
//        File afterUpload = new File(screenshotDir + "/Step_" + stepCount++ + "_After_Upload.png");
//        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(afterUpload);
    }
}

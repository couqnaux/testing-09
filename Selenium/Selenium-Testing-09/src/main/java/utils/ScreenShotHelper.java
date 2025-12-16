package utils;

import org.openqa.selenium.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotHelper {
    private WebDriver driver;
    private int stepCount;
    private String screenshotDir;
    private JavascriptExecutor js;

    public ScreenShotHelper(WebDriver driver, String pageName) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        stepCount = 1;
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/" + pageName + timestamp;
        new File(screenshotDir).mkdir();
    }

    public WebElement highlightElement(WebElement element) throws Exception {
        js.executeScript(
                "arguments[0].style.border='3px solid red';" +
                        "arguments[0].style.backgroundColor='yellow';"
                , element
        );
        Thread.sleep(500);
        return element;
    }

    public WebElement removeHighlight(WebElement element) throws Exception {
        js.executeScript(
                "arguments[0].removeAttribute('style');",
                element
        );
        return element;
    }

    public void captureHighlight(WebElement element, String stepName) throws Exception {
        highlightElement(element);
        File fileName = new File(screenshotDir + File.separator + "Step_" + stepCount++ + "_" + stepName + ".png");
        ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).renameTo(fileName);
        removeHighlight(element);
    }
}

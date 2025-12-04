package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By sidebarMenuItems = By.cssSelector(".oxd-main-menu-item-wrapper a.oxd-main-menu-item");
    private final By sidebarMenuNames = By.cssSelector(".oxd-main-menu-item-wrapper span.oxd-main-menu-item--name");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    // highlight item trên html
    // import code js vào html thông qua selenium
    public void highlightMenuByName(String menuName, long delay) {
        List<WebElement> menuElements = driver.findElements(sidebarMenuNames);

        // Tạo biến để lưu các menu
        List<String> menuTexts = new ArrayList<>();

        for (WebElement menuElement : menuElements) {
            // get text trong element đó
            String text = menuElement.getText();
            menuTexts.add(text);
        }

        // Tạo object js để tương tác với element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "arguments[0].style.border='3px solid red'; argument[0].style.backgroundColor='#fb26eb';",
                menuTexts
        );
    }

}

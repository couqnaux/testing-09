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
    private final By hamburgerMenu = By.cssSelector(".oxd-topbar-header-hamburger");
    private final By dashboardTitle = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
    private final By sidebarPanel = By.cssSelector(".oxd-sidepanel");
    private final By headerMenu = By.cssSelector(".oxd-topbar-header");

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

        JavascriptExecutor js = (JavascriptExecutor) driver;

        //Highlight
        //B1: Duyệt từng menu trong list menu từ html
        for (WebElement menuElement : menuElements) {
            String text = menuElement.getText().trim();
            if (text.equals(menuName)) {

                //B2: Lưu style cũ của menu để sau khi highlight xong thì trả về như cũ
                String originalStyle = (String) js.executeScript("return arguments[0].getAttribute('style')", menuElement);

                //B3: Highlight và chờ khoảng 0.5s
                js.executeScript("arguments[0].style.border='3px solid red';" + "arguments[0].style.backgroundColor='yellow';", menuElement);
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                //B4: Trả về style cũ
                js.executeScript("arguments[0].setAttribute('style', arguments[1]);", menuElement, originalStyle);
                break;
            }
        }
    }

    //Hàm lấy danh sách tên các menu trên slidebar
    public List<String> getSideBarMenuItems() {
        //get list menu từ HTML
        List<WebElement> menuItems = driver.findElements(sidebarMenuNames);
        List<String> menuNames = new ArrayList<>();
        for (WebElement menuElement : menuItems) {
            String text = menuElement.getText().trim();
            menuNames.add(text);
        }
        return menuNames;
    }

    //Ham click vao menu admin
    public void clickAdminMenu() {
        //B1: Lay list cac menu item trong home page
        List<WebElement> menuItems = driver.findElements(sidebarMenuNames);
        for (WebElement menuItem : menuItems) {
            if (menuItem.getText().trim().equals("Admin")) {
                WebElement adminLink = menuItem.findElement(By.xpath("./ancestor::a"));
                adminLink.click();
                break;
            }
        }
    }

    public void clickMyInfoMenu() {
        //B1: Lay list cac menu item trong home page
        List<WebElement> menuItems = driver.findElements(sidebarMenuNames);
        for (WebElement menuItem : menuItems) {
            if (menuItem.getText().trim().equals("My Info")) {
                WebElement adminLink = menuItem.findElement(By.xpath("./ancestor::a"));
                adminLink.click();
                break;
            }
        }
    }

    public void clickHamburgerMenu() throws Exception {
        WebElement hamburger = driver.findElement(hamburgerMenu);
        hamburger.click();
        Thread.sleep(1000);
    }

    public boolean isVisibleMenu() {
        WebElement menu = driver.findElement(sidebarPanel);
        if (!menu.isDisplayed()) {
            return false;
        }

        List<WebElement> menuItems = driver.findElements(sidebarMenuItems);
        return menuItems.size() > 0;
    }

    public boolean isScrollHorizontal() {
        WebElement menuHeader = driver.findElement(headerMenu);
        JavascriptExecutor js = (JavascriptExecutor) js;
        Long rightMenu = (Long) js.executeScript(
                "return Math.ceil(arguments[0].getBoundingClientReact().right);",
                menuHeader
        );
        Long viewportWidth = (Long) js.executeScript(
                "return window.innerWidth;"
        );
        return rightMenu > viewportWidth;
    }

}

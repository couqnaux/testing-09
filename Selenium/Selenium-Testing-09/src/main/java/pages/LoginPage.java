package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    //define attribute
    //selenium => Webdriver
    private WebDriver driver;

    //define cac element trong LoginPage
    //form username
    private By usernameField = By.name("username");
    //form password
    private By passwordField = By.name("password");
    //button login
    private By loginButton = By.xpath("//button[@type='submit' or text()='Login']");
    //error message co tren web
    private By errorMessage = By.xpath("//div[@role='alert']");
    //end point cua page Login
    private String loginUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    //Ham khoi tao
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Ham mo trang login
    public void openLoginPage() {
        driver.get(loginUrl);
    }

    //Ham nhap username vao form input
    public void enterUsername(String username) {
        //B1: Tim element input username
        WebElement usernameElement = driver.findElement(usernameField);
        //B2: Xoa du lieu cu tren form neu co
        usernameElement.clear();
        //B3: Nhap username vao form
        usernameElement.sendKeys(username);
    }

    //Ham nhap password vao form
    public void enterPassword(String password) {
        WebElement passwordElement = driver.findElement(passwordField);
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    //Ham click vao button Login
    public void clickLoginButton() {
        WebElement loginButtonElement = driver.findElement(loginButton);
        loginButtonElement.click();
    }

    //Ham login de tong hop cac buoc login
    public void login(String username, String password) {
        openLoginPage();
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    //Ham kiem tra co loi hay khong
    public boolean isErrorDisplayed() {
        try {
            WebElement errorElement = driver.findElement(errorMessage);
            return errorElement.isDisplayed();
        }catch (Exception e) {
            return false;
        }
    }

    //Ham get endpoint
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

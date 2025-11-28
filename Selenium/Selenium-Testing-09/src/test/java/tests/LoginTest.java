package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
    private static final String VALID_USERNAME = "Admin";
    private static final String VALID_PASSWORD = "admin123";
    private static final String INVALID_USERNAME = "wrongusername";
    private static final String INVALID_PASSWORD = "wrongpassword";

    //Viết testcase

    @Test
    public void testLoginSuccess() {
        //B1: Tạo đối tượng LoginPage
        LoginPage loginPage = new LoginPage(driver);

        //B2: Gọi hàm login để thực hiện các step login
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        //B3: Đợi server kiểm tra thông tin user
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //B4: Kiểm tra kết quả với expect
        String currentUrl = loginPage.getCurrentUrl();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(currentUrl.contains("dashboard"));
    }

    @Test
    public void testLoginWithWrongUser() {
        //B1: Tạo đối tượng LoginPage
        LoginPage loginPage = new LoginPage(driver);

        //B2: Gọi hàm login để thực hiện các step login
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);

        //B3: Đợi server kiểm tra thông tin user
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //B4: Kiểm tra kết quả với expect
        String currentUrl = loginPage.getCurrentUrl();

        Assert.assertTrue(loginPage.isErrorDisplayed());
//        Assert.assertTrue(currentUrl.contains("login"));

    }
}

package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.MyInfoPage;
import utils.BaseTest;
import utils.ConfigReader;

public class MyInfoTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPass();

    @Test
    public void testUploadAvatar() throws Exception {
        extentTest.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Thread.sleep(1000);

        extentTest.info("Click menu My Info");
        HomePage homePage = new HomePage(driver);
        homePage.clickMyInfoMenu();
        Thread.sleep(1000);

        extentTest.info("Click employee image");
        MyInfoPage myInfoPage = new MyInfoPage(driver);
        myInfoPage.clickEmployeeImage();
        Thread.sleep(1000);

        extentTest.info("Click button upload");
        myInfoPage.clickUploadBtn();
        Thread.sleep(1000);

        extentTest.info("Choose image");
        myInfoPage.chooseImage("C:\\Users\\PC\\Desktop\\avatar.png");
        Thread.sleep(3000);
    }
}

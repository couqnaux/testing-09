package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

import java.util.List;

public class HomeTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPass();

    @Test
    public void testHighlightMenuItems() throws Exception {
        extentTest.info("B1: Login vao he thong");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        extentTest.info("B2: Cho 3s sau khi login");
        Thread.sleep(3000);

        extentTest.info("Khoi tao object HomePage");
        HomePage homePage = new HomePage(driver);

        extentTest.info("TC1: Kiem tra url sau khi login thanh cong");
        String currentUrl = homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"));

        extentTest.info("TC2: Kiem tra menu trong trang home");
        List<String> actualMenuItems = homePage.getSideBarMenuItems();

        List<String> expectedMenuItems = List.of("Admin", "PIM", "Leave", "Time");

        for (String expectedMenu : expectedMenuItems) {
            homePage.highlightMenuByName(expectedMenu, 500);
            Assert.assertTrue(actualMenuItems.contains(expectedMenu));
        }

        Thread.sleep(2000);
    }

    @Test
    public void testSearchAdmin() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Thread.sleep(3000);

        HomePage homePage = new HomePage(driver);
        homePage.clickAdminMenu();
        Thread.sleep(3000);

        AdminPage adminPage = new AdminPage(driver);
        adminPage.enterAdminName();
        Thread.sleep(3000);
    }
}


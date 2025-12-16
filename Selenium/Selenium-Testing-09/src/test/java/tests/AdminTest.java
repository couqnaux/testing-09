package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

public class AdminTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPass();

    @Test
    public void testSearchAdmin() throws Exception {
        extentTest.info("Login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Thread.sleep(1000);

        extentTest.info("Click menu Admin");
        HomePage homePage = new HomePage(driver);
        homePage.clickAdminMenu();
        Thread.sleep(1000);

        extentTest.info("Enter admin name");
        AdminPage adminPage = new AdminPage(driver);
        adminPage.enterAdminName();
        Thread.sleep(1000);

        extentTest.info("Select user role");
        adminPage.selectUserRole();
        Thread.sleep(1000);

        extentTest.info("Select enable status");
        adminPage.selectStatus();
        Thread.sleep(1000);

        extentTest.info("Click button search");
        adminPage.clickSearchButton();
        Thread.sleep(1000);

        //Kiem tra ket qua
        boolean hasData = adminPage.hasTableData();
        if (hasData) {
            extentTest.pass("Co data");
            Assert.assertTrue(hasData);
        } else {
            extentTest.fail("Khong co data");
            Assert.fail("Khong co data");
        }
    }

}


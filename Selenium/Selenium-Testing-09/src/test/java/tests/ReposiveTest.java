package tests;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

public class ReposiveTest extends BaseTest {
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPass();

    @Test
    public void testResponsive() throws Exception {
        extentTest.info("b1: setup kich thuoc iphone");
        driver.manage().window().setSize(new Dimension(375, 667));
        Thread.sleep(1000);

        extentTest.info("B2: login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        Thread.sleep(3000);

        extentTest.info("B3: Kiem tra responsive tren mobile");
        HomePage homePage = new HomePage(driver);
        homePage.clickHamburgerMenu();
        Thread.sleep(1000);

        boolean isDisplayedMenu = homePage.isVisibleMenu();
        Assert.assertTrue(isDisplayedMenu);

        boolean isHorizontal = homePage.isScrollHorizontal();
        Assert.assertFalse(isHorizontal);
    }
}

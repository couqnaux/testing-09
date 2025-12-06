package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
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
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Thread.sleep(3000);

        HomePage homePage = new HomePage(driver);

        String currentUrl = homePage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/dashboard"));

        List<String> actualMenuItems = homePage.getSideBarMenuItems();

        List<String> expectedMenuItems = List.of("Admin", "PIM", "Leave", "Time");

        for (String expectedMenu : expectedMenuItems) {
            homePage.highlightMenuByName(expectedMenu, 500);
            Assert.assertTrue(actualMenuItems.contains(expectedMenu));
        }

        Thread.sleep(2000);
    }
}

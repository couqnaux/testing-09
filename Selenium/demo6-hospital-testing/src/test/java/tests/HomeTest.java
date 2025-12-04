package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class HomeTest extends BaseTest {
    private final String HOME_URL = "https://demo6.cybersoft.edu.vn/";

    @Test
    public void testScrollToBottom() throws Exception {
        HomePage homePage = new HomePage(driver);
        driver.get(HOME_URL);

        homePage.scrollToBottom();
        Thread.sleep(3000);

        Assert.assertTrue(true);
    }

    @Test
    public void testScrollToTop() throws Exception {
        HomePage homePage = new HomePage(driver);
        driver.get(HOME_URL);

        homePage.scrollToBottom();
        Thread.sleep(3000);

        homePage.scrollToTop();
        Thread.sleep(3000);

        Assert.assertTrue(true);
    }

    @Test
    public void testScrollToElement() throws Exception {
        HomePage homePage = new HomePage(driver);
        driver.get(HOME_URL);

        WebElement emailField = homePage.getEmailField();
        homePage.scrollToElement(emailField);
        Thread.sleep(3000);

        Assert.assertTrue(true);
    }
}

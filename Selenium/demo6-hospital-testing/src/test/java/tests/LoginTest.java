package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {
    private static final String VALID_USERNAME = "string";
    private static final String VALID_PASSWORD = "123456";

    @Test
    public void testLoginSuccess() throws Exception {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login(VALID_USERNAME, VALID_PASSWORD);

        Thread.sleep(3000);

        String currentUrl = driver.getCurrentUrl();

        Assert.assertFalse(currentUrl.contains("login"));
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;
import utils.CsvReader;
import utils.LoginData;

import java.util.List;

public class LoginTest extends BaseTest {
    //    static final: tạo hằng số, không thay doi duoc, dung chung cho cac test case
    private static final String VALID_USERNAME = ConfigReader.getAdminUsername();
    private static final String VALID_PASSWORD = ConfigReader.getAdminPass();
    private static final String INVALID_USERNAME = "wronguser";
    private static final String INVALID_PASSWORD = "wrongpassword";

    //    viet test case
    @Test
    public void testLoginSuccess() {
//        B1: tao doi tuong LoginPage
        LoginPage loginPage = new LoginPage(driver);
//        B2: goi ham login de thuc hien cac step login
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
//        B3: doi cho server kiem tra thong tin user
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
//        B4: kiem tra ket qua voi expect
//        chien luoc kiem tra
//        kiem tra endpoint url
//        neu endpoint moi != endpoint login => pass
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
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(INVALID_USERNAME, INVALID_PASSWORD);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        chien luoc 1: dung url de kiem tra
        String currentUrl = loginPage.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("/login"));
//        Assert.assertFalse(currentUrl.contains("dashboard"));
//        chien luoc 2: dung ham isErrorDisplayed()
        boolean hasError = loginPage.isErrorDisplayed();
        Assert.assertTrue(hasError);
    }

    //define data provider de doc du lieu

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() throws Exception {
        //B1: lấy path chứa file csv
        String csvPath = System.getProperty("user.dir") + "/src/test/resources/login_data.csv";
        List<LoginData> data = CsvReader.readLoginData(csvPath);

        Object[][] listUsers = new Object[data.size()][2];
        for (int i = 0; i < data.size(); i++) {
            listUsers[i][0] = data.get(i).getUsername();
            listUsers[i][1] = data.get(i).getPassword();
        }

        return listUsers;
    }

    @Test(dataProvider = "loginData")
    public void testLoginWithCsvData(String username, String password) throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        Thread.sleep(3000);

        String currentUrl = loginPage.getCurrentUrl();
        if (currentUrl.contains("login")) {
            Assert.assertTrue(currentUrl.contains("/login"));
            boolean hasError = loginPage.isErrorDisplayed();
            Assert.assertTrue(hasError);
        } else {
            Assert.assertTrue(currentUrl.contains("/dashboard"));
        }
    }
}
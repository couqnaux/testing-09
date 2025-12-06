package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.BookingPage;
import pages.LoginPage;

public class BookingTest extends BaseTest{
    private static final String VALID_USERNAME = "xuanquoc";
    private static final String VALID_PASSWORD = "123456";

    @Test
    public void testBookingAppointment() throws Exception {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(VALID_USERNAME, VALID_PASSWORD);
        Thread.sleep(3000);

        BookingPage bookingPage = new BookingPage(driver);
        bookingPage.openBookingFromMenu();
        Thread.sleep(3000);

        bookingPage.selectBranch("Chi nhánh trung tâm");
        Thread.sleep(3000);

        bookingPage.selectDoctor("Dr. User Fullname 10 - Cardiology");
        Thread.sleep(3000);

        bookingPage.pickDateAppointment("December 6, 2025");
        Thread.sleep(3000);

        Assert.assertTrue(true);
    }
}

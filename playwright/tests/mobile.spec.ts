import { test, expect } from "@playwright/test";
import { LoginPage } from "../pages/LoginPage";

//Test responsive trên mobile

test.describe("Mobile test login", () => {
  test("Test login", async ({ page }) => {
    await page.setViewportSize({ width: 375, height: 667 }); //Kích thước iPhone 6/7/8
    const loginPage = new LoginPage(page);
    //Thực hiện login
    await loginPage.login("Admin", "admin123");
    //Kiểm tra login thành công
    await loginPage.isLoginSuccessful();
  });
});

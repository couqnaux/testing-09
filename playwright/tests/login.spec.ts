import { test, expect } from "@playwright/test";
import { LoginPage } from "../pages/LoginPage";

//describe: tạo cụm test case
test.describe("Login Tests", () => {
  test("Test login successful", async ({ page }) => {
    const loginPage = new LoginPage(page);
    //Thực hiện login
    await loginPage.login("Admin", "admin123");
    //Kiểm tra login thành công
    await loginPage.isLoginSuccessful();
  });

  // test("Test login failed", async ({ page }) => {
  //   const loginPage = new LoginPage(page);
  //   //Thực hiện login với thông tin sai
  //   await loginPage.login("Admin", "wrongpassword");
  //   //Kiểm tra login thất bại
  //   (await loginPage.isLoginSuccessful()) === false;
  // });
});

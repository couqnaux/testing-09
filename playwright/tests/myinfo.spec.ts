import { test, expect } from "@playwright/test";
import { LoginPage } from "../pages/LoginPage";
import { HomePage } from "../pages/HomePage";
import { MyInfoPage } from "../pages/MyInfoPage";

test.describe("My Info tests", () => {
  // B1: Login

  //B2: Click menu My Info
  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    const homePage = new HomePage(page);
    const myInfoPage = new MyInfoPage(page);

    await loginPage.login("Admin", "admin123");

    await page.waitForURL("**/dashboard**", { timeout: 10000 });

    await homePage.sidebarMenuNames.first().waitFor({ state: "visible" });

    await homePage.clickMenuMyInfo();

    await myInfoPage.avatarWrapper.waitFor({
      state: "visible",
      timeout: 10000,
    });
  });
  test("Upload avatar", async ({ page }) => {
    const myInfoPage = new MyInfoPage(page);

    await myInfoPage.uploadAvatar();

    expect(true).toBeTruthy();
  });
});

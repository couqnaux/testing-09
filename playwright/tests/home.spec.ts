import { test, expect } from "@playwright/test";
import { LoginPage } from "../pages/LoginPage";
import { HomePage } from "../pages/HomePage";
test.describe("Home Page tests", () => {
  // Setup môi trường
  // 1. Login với account
  // 2. goto page Home
  test.beforeEach(async ({ page }) => {
    const loginPage = new LoginPage(page);
    const homePage = new HomePage(page);

    //Login
    await loginPage.login("Admin", "admin123");

    //Đợi đến khi trang Home load thành công
    // => check url
    await page.waitForURL("**/dashboard**", { timeout: 10000 });

    // Đợi đến khi phần sidebar hiển thị
    await homePage.sidebarMenuNames.first().waitFor({ timeout: 10000 });
  });
  test("Verify các menu có đầy đủ không", async ({ page }) => {
    const homePage = new HomePage(page);

    const menuItems = await homePage.getSidebarMenuItems();

    // kiểm tra
    // case 1: menuItems > 0
    expect(menuItems.length).toBeGreaterThan(0);
    // case 2: menuItems có chứa các giá trị mong muốn
    expect(menuItems).toContain("Admin");
    // case 3: kiểm tra menuItems có đầy đủ các giá trị mong muốn không
  });
});

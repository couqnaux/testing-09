// private final By sidebarMenuItems = By.cssSelector(".oxd-main-menu-item-wrapper a.oxd-main-menu-item");
//     private final By sidebarMenuNames = By.cssSelector(".oxd-main-menu-item-wrapper span.oxd-main-menu-item--name");

//     private final By hambugerMenu = By.cssSelector(".oxd-topbar-header-hamburger");
//     private final By dashboardTitle = By.cssSelector(".oxd-topbar-header-breadcrumb-module");
//     private final By sidebarPanel = By.cssSelector(".oxd-sidepanel");
//     private final By headerMenu = By.cssSelector(".oxd-topbar-header");

import { Page, Locator } from "@playwright/test";

export class HomePage {
  readonly page: Page;
  readonly sidebarMenuItems: Locator;
  readonly sidebarMenuNames: Locator;
  constructor(page: Page) {
    this.page = page;
    this.sidebarMenuItems = page.locator(
      ".oxd-main-menu-item-wrapper a.oxd-main-menu-item"
    );
    this.sidebarMenuNames = page.locator(
      ".oxd-main-menu-item-wrapper span.oxd-main-menu-item--name"
    );
  }

  // lấy danh sách tên các menu trong sidebar
  async getSidebarMenuItems(): Promise<string[]> {
    // B1: đếm số lượng locator sau khi tìm
    // => dùng trong vòng lặp

    const count = await this.sidebarMenuNames.count();

    // B2: tạo biến lưu các menu name
    const menuNames: string[] = [];

    // B3: lặp qua từng locator, lấy text, push vào mảng
    for (let i = 0; i < count; i++) {
      // lấy locator thứ i => nth(i).textContent()
      const name = await this.sidebarMenuNames.nth(i).textContent();
      if (name) {
        // do typescript nghi ngờ name có thể có value null
        // => phải thêm if
        menuNames.push(name);
      }
    }
    return menuNames;
  }

  async clickMenuMyInfo(): Promise<void> {
    await this.sidebarMenuItems.filter({ hasText: "My Info" }).first().click();
  }
}

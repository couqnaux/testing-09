import { Page, Locator } from "@playwright/test";
import { highlightAndScreenshot } from "../utils/screenshot";

export class LoginPage {
  //Locators
  readonly page: Page; //Page object
  readonly usernameInput: Locator; //Username input field
  readonly passwordInput: Locator; //Password input field
  readonly loginButton: Locator; //Login button
  //   readonly homeTitle: Locator; //Home page title after login

  //Functions: login, validate
  constructor(page: Page) {
    this.page = page;
    this.usernameInput = page.locator('input[name="username"]');
    this.passwordInput = page.locator('input[name="password"]');
    this.loginButton = page.locator('button[type="submit"]');
  }

  async login(username: string, password: string): Promise<void> {
    await this.page.waitForTimeout(3000);
    //B1: Vào web
    await this.page.goto(
      "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login"
    );
    //B2: Nhập username, password
    await this.usernameInput.fill(username);
    await highlightAndScreenshot(
      this.page,
      this.usernameInput,
      "LoginTest",
      "Filled_Username"
    );
    await this.passwordInput.fill(password);
    await highlightAndScreenshot(
      this.page,
      this.passwordInput,
      "LoginTest",
      "Filled_Password"
    );

    // B3: Click login và chờ redirect
    await Promise.all([
      this.page.waitForURL("**/dashboard/**", { timeout: 10000 }),
      this.loginButton.click(),
    ]);
    await highlightAndScreenshot(
      this.page,
      this.loginButton,
      "LoginTest",
      "Clicked_Login_Button"
    );
  }
  async isLoginSuccessful(): Promise<boolean> {
    //Case 1: test Url có chữ dashboard
    return this.page.url().includes("/dashboard");
  }
}

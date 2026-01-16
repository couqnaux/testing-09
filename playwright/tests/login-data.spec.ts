import { test, expect } from "@playwright/test";
import { LoginData, readLoginDataFromCSV } from "../utils/csvReader";
import { LoginPage } from "../pages/LoginPage";

// Read login data from CSV file
const loginDataArray: LoginData[] = readLoginDataFromCSV();
console.log(`Loaded ${loginDataArray.length} login data entries from CSV.`);

test.describe("Login Tests with CSV Data", () => {
  for (const loginData of loginDataArray) {
    test(`Login Test - ${loginData.description}`, async ({ page }) => {
      const loginPage = new LoginPage(page);
      await loginPage.login(loginData.username, loginData.password);
      const isSuccessful = await loginPage.isLoginSuccessful();
      if (loginData.expected_result === "success") {
        expect(isSuccessful).toBeTruthy();
      } else {
        expect(isSuccessful).toBeFalsy();
      }
    });
  }
});

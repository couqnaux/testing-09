//B1: highlight element

//B2: Chụp ảnh màn hình và lưu vào file

// nhận các tham số
// param1: page -> object Page của playwright
// param2: locator -> object Locator của playwright
// param3: testName -> để đặt folder lưu hình có highlight
// param4: stepName -> để đặt tên file hình có highlight

import { Page, Locator } from "@playwright/test";
import { mkdirSync } from "fs";
import { join } from "path";

export async function highlightAndScreenshot(
  page: Page,
  locator: Locator,
  testName: string,
  stepName: string
): Promise<void> {
  //B1: Tạo tên folder
  const folderName = testName.toLowerCase();
  //B2: Tạo đường dẫn để lưu folder
  const screenshotDir = join(__dirname, "..", "screenshot", folderName);
  //B3: Tạo folder
  mkdirSync(screenshotDir, { recursive: true });
  //B4: highlight element
  await locator.evaluate((element) => {
    (element as HTMLElement).style.border = "3px solid red";
    (element as HTMLElement).style.backgroundColor = "yellow";
  });
  await page.waitForTimeout(1000);
  //B5: chụp ảnh màn hình và lưu vào file
  const filePath = join(screenshotDir, `${stepName}.png`);
  await page.screenshot({ path: filePath });
}

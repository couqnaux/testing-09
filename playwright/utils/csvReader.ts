import { readFile } from "fs/promises";
import { join } from "path";
import { parse } from "csv-parse/sync";
import { readFileSync } from "fs";

//Định nghĩa dữ liệu có trong file csv
export interface LoginData {
  username: string;
  password: string;
  expected_result: string;
  description: string;
}

export const readLoginDataFromCSV = (): LoginData[] => {
  //B1: Xác định đường dẫn đến file csv
  const filePath = join(__dirname, "..", "data", "login-data.csv");

  //B2: Đọc nội dung file csv
  const fileContent = readFileSync(filePath);
  //B3: Parse nội dung file csv thành mảng đối tượng
  const data = parse(fileContent, {
    columns: true, //Dòng đầu tiên là header
    skip_empty_lines: true, //Bỏ qua dòng trống
    trim: true, //Xoá khoảng trắng thừa
  }) as LoginData[];
  return data;
};

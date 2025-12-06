package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtendReportManager {
    //attribute giúp tạo file HTML report
    private static ExtentReports extent;

    //attribute tương tác trực tiếp với test và render
    private static ExtentTest test;

    //Hàm khởi tạo
    public static ExtentReports getInstance() {
        if (extent == null) {

            //Tạo tên file với format timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/" + ConfigReader.getReportPath() + "/Report_" + timestamp + ".html";

            //Tạo extentSparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

            //Cấu hình report: title, report name, theme, ngày tạo report,...
            sparkReporter.config().setDocumentTitle("Orange HRM test");
            sparkReporter.config().setReportName(ConfigReader.getReportName());
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }

    //Hàm tạo testcase mới
    public static ExtentTest createTest(String testName, String description) {
        test = getInstance().createTest(testName, description);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

    //Hàm flush để ghi info testcase ra file
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}

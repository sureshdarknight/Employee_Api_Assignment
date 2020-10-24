
package api.axiom.objectrepository;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import api.axiom.utils.ConfigUtilities;
import api.axiom.utils.ExcelUtilities;

public class CoreApi {

	// Config Fie path
	public static String config = "./config/config.properties";
	public static String excel="./testdata/testDataSheet.xlsx";
	//Reading from Config file
	public static String uri = ConfigUtilities.getPropertyValue(config, "configbaseURI");
	public static String employeesresources = ConfigUtilities.getPropertyValue(config, "employeesResource");
	public static String employeeResource=ConfigUtilities.getPropertyValue(config, "employeeResource");
	//Reading from Excel
	public static String employeeID=ExcelUtilities.getKeyValue(excel, "EmployeeSheet", "EmployeeID");

	// Reporting
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeTest
	public void startReport() {
		System.out.println("Before Test ");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/TestExecutionReport.html");
		extent = new ExtentReports();
		extent.setSystemInfo("Type of Testing", "API");
		extent.setSystemInfo("User Name", "Suresh");
		htmlReporter.config().setTheme(Theme.DARK);
		extent.attachReporter(htmlReporter);
	}

	@AfterMethod
	public void getResult(ITestResult result) {
		System.out.println("After Test");
		if (result.getStatus() == ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + "Failed", ExtentColor.RED));

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "PASSED", ExtentColor.GREEN));

		}
		else {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "SKIPPED", ExtentColor.ORANGE));

		}

	}
	
	
	@AfterTest
	public void endReport() {
		extent.flush();
		System.out.println("Flushing the report");
	
	}

}

package api.axiom.test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;

import api.axiom.component.ServiceComponent;
import api.axiom.objectrepository.CoreApi;
import api.axiom.services.Scenario1_ToPickApiLinkFromConfigFile;
import api.axiom.services.Scenario2_ToPicktheRequestDataFromExternalFile;

public class TestSuite extends CoreApi {

	Scenario1_ToPickApiLinkFromConfigFile scenario1 = new Scenario1_ToPickApiLinkFromConfigFile();
	Scenario2_ToPicktheRequestDataFromExternalFile scenario2 = new Scenario2_ToPicktheRequestDataFromExternalFile();


	@Test(priority = 0)
	public void SC1_validateStatusCode() throws Exception {
		logger=extent.createTest("Scenario 1: Status Code Check");
		logger.createNode("Validating the status code from the API response");
		scenario1.assertionStatusCode();

	}

	@Test(priority = 1)
	public void SC1_validateEmployeesProfileImage() throws Exception {
		logger=extent.createTest("Scenario 1: Employees Profile Image Check");
		logger.createNode("Validating all Employee image should be blank");
		scenario1.assertionProfileImage();

	}
	
	@Test(priority = 2)
	public void SC2_validateStatusCode() throws Exception {
		logger=extent.createTest("Scenario 2: Status Code Check");
		logger.createNode("Validating the status code from the API response");
		scenario2.assertionStatusCode();

	}

	@Test(priority = 3, enabled = true)
	public void SC2_validateEmployeeImage() throws Exception {
		logger=extent.createTest("Scenario 2: Assertion EmployeeMessage");
		logger.createNode("Validating the Employee 2 message is successfully retrived or not");
		scenario2.assertionEmployeeMessage();
	}

	@Test(priority = 4)
	public void SC2_validateResponsePattern() throws Exception {
		logger=extent.createTest("Scenario 2: Validate Response pattern");
		logger.createNode("Validating the response body pattern for Employee 2");		
		scenario2.assertionResponsePattern();

	}


}
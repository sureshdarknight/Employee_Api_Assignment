package api.axiom.services;

import static io.restassured.RestAssured.given;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.plaf.synth.SynthStyle;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import api.axiom.component.ServiceComponent;
import api.axiom.objectrepository.CoreApi;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class Scenario2_ToPicktheRequestDataFromExternalFile extends CoreApi {
	ServiceComponent servicecomponent = new ServiceComponent();
	Response response = null;

	// Scenario2- Assertion to verify 200
	public void assertionStatusCode() throws Exception {
		try {
			System.out.println("Scenario 2: Assertion Status code begins");
			response = servicecomponent.invokeRestServiceEmployee(CoreApi.uri, CoreApi.employeeResource);
			// Assert Status code as 200
			Assert.assertEquals(200, response.getStatusCode());
			logger.log(Status.PASS,
					MarkupHelper.createLabel(
							"Scenario 2: Status code from API returns successfull: " + response.getStatusCode(),
							ExtentColor.GREEN));
			System.out.println("Status code is successfully validated as " + response.getStatusCode());
			System.out.println("Scenario 2: Assertion Status code ends");
		} catch (Exception e) {
			logger.log(Status.FAIL,
					MarkupHelper.createLabel(
							"Scenario 2: Status code from API is " + response.getStatusCode() + " instead of 200",
							ExtentColor.RED));
			System.out.println("Status code is " + response.getStatusCode() + " instead of 200");
			System.out.println("Scenario 2: Assertion Status code ends");

		}

	}
	
	// Scenario 2 -Assertion to verify message in the response "Successfully!
		// Record has been fetched."

		public void assertionEmployeeMessage() throws Exception {
			try {
				System.out.println("Scenario 2: Assertion retriving message for Employee2 begins");
				String expectedMessage = "Successfully! Record has been fetched.";
				String employeeMessage = response.jsonPath().getString("message");
				Assert.assertEquals(employeeMessage, expectedMessage);
				logger.log(Status.PASS, MarkupHelper.createLabel(
						"Scenario 2: Message has been successfully retrived for the Employee 2", ExtentColor.GREEN));
				System.out.println("Employee2 Message successfully fetched and compared");
				System.out.println("Scenario 2: Assertion retriving message for Employee2 ends");
			} catch (Exception e) {
				logger.log(Status.FAIL, MarkupHelper.createLabel("Scenario 2: Failed in retriving the employee message",
						ExtentColor.RED));
				System.out.println("Employee Message is not successfully fetched. " + e.getMessage());

			}
		}

	// Scenario 2 -Assertion to verify the response body pattern."

	public void assertionResponsePattern() throws Exception {
		try {
			System.out.println("Scenario 2: Assertion of response body pattern begins");
			response.then().assertThat().body(matchesJsonSchemaInClasspath("employeedetails.json"));
			System.out.println("Assertion of Response body pattern for employee is suucessfully completed");
			logger.log(Status.PASS, MarkupHelper.createLabel(
					"Scenario 2: Response body pattern for employee is validated sucessfully ", ExtentColor.GREEN));
		} catch (Exception e) {
			logger.log(Status.FAIL, MarkupHelper.createLabel("Assertion fails for Response body pattern verification",
					ExtentColor.RED));
			System.out.println("Assertion fails for Response body pattern verification " + e.getMessage());

		}
	}

	
}

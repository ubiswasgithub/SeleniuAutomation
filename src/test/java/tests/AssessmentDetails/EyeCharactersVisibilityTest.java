package tests.AssessmentDetails;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import mt.siteportal.utils.tools.Log;

@Test(groups = { "EsignEyeCharacters" })
public class EyeCharactersVisibilityTest extends AbstractAssessmentDetails {

	@DataProvider
	public Object[][] assessmentRemovingFlags() {
		return new Object[][] { { "Visit8", "CGI-I", "Paper" }, { "Visit1", "CDR", "Admin" } };
	}

	@DataProvider
	public Object[][] assessmentAddingFlags() {
		return new Object[][] { { "Visit8", "CGI-I", "Paper" } };
	}
	
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		Log.logTestClassStart(this.getClass());
		beforeSteps.loginAndChooseStudySite(adminLogin, adminPasword, studyName, siteName);
		detailsSteps.getToAssessmentList();
	}

	@Test(groups = { "EsignEyeCharactersVisibilityForAddingFlags",
			"JamaNA" }, description = "Check that while adding ClinRo assessment flag for paper transcription,Eye Characters are visible on e-sign window.", dataProvider = "assessmentAddingFlags")
	public void esignEyeCharactersVisibilityForAddingFlagsTest(String visitName, String assessmentName, String type) {
		detailsSteps.getToAssessmentDetails(subjectName, visitName, assessmentName);
		detailsSteps.checkOrUncheckAssessmentFlagCheckboxAndClickConfrimBtn(type);
		detailsSteps.eyeCharactersVisibilityChecking(adminLogin, adminPasword, type, dropDownvalue);
		detailsSteps.navigateBackToAssessmentList();
	}

	@Test(groups = { "EsignEyeCharactersVisibilityForRemovingFlags",
			"JamaNA" }, description = "Check that while Removing ClinRo Assessment flag Eye Characters are visible on e-sign dialog window.", dataProvider = "assessmentRemovingFlags")
	public void esignEyeCharactersVisibilityForRemovingFlagsTest(String visitName, String assessmentName, String type) {
		detailsSteps.getToAssessmentDetails(subjectName, visitName, assessmentName);
		detailsSteps.checkOrUncheckAssessmentFlagCheckboxAndClickConfrimBtn(type);
		detailsSteps.eyeCharactersVisibilityChecking(adminLogin, adminPasword, type, dropDownvalue);
		detailsSteps.navigateBackToAssessmentList();
	}

}

package tests.SubjectVisit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import mt.siteportal.utils.data.Constants;
import mt.siteportal.utils.data.URLsHolder;
import mt.siteportal.utils.tools.Log;
import steps.Configuration.BeforeSteps;
import steps.Configuration.CommonSteps;
import steps.Tests.AssessmentSteps;
import steps.Tests.SubjectSteps;
import steps.Tests.TemplateSteps;
import tests.Abstract.AbstractTest;

/**
 * Created by maksym.tkachov on 2/26/2016.
 * Refactored by Abdullah Al Hisham on 12/22/2016.
 */
@Test(groups = { "SubjectVisit", "AddSubjectVisit", "E-Sign" })
public class AddSubjectVisitTests extends AbstractTest {
	
	protected final String loginPageURL = URLsHolder.getHolder().getSiteportalURL();
	
	protected static DateFormat subjectFormat;
	protected static String formattedSubject, suffix, subjectName;
	
	protected String study = "Auto - Subject";
	protected String site = "Site_1 - Francis Gibbes";
	protected String pendingVisitName = "Visit 1";
	protected String skipedVisitName = "Visit 2";
	protected String unscheduledVisitName = "Visit 5";
	
	SubjectSteps subjectSteps = new SubjectSteps();
    TemplateSteps templateSteps = new TemplateSteps();
    AssessmentSteps assessmentSteps = new AssessmentSteps();

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		Log.logTestClassStart(this.getClass());
//		dbSteps.deleteSubjectVisit(subjectName, pendingVisitName);
		subjectFormat = new SimpleDateFormat("MMddyy");
		formattedSubject = subjectFormat.format(new Date());
		suffix = "-" + CommonSteps.generateRandomNames(2);
		subjectName = "AddSubjectVisitTest#" + formattedSubject + suffix;

		beforeSteps = new BeforeSteps();
		beforeSteps.loginAndOpenAllSubjects(adminLogin, adminPasword, study, site);
		subjectSteps.createNewSubject(subjectName);
	}

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        afterSteps.logout();
        Log.logTestClassEnd(this.getClass());
    }

	@Test(groups = { "AddVisitAndVerifyStatus", "SF2015_2-TC-108" }, description = "Schedule subject visit & verify available visit statuses")
	public void addVisitAndVerifyStatusTest() {
//		subjectSteps.openSubjectDetails(subjectName);
		subjectSteps.subjectDetailsVerification(subjectName);
		subjectSteps.selectAndScheduleVisitInPosition(pendingVisitName, 0);
		subjectSteps.visitStatusVerification(Constants.VISIT_STATUS_PENDING, pendingVisitName, 0);
		subjectSteps.deleteButtonVisitTableVerification(true);
		templateSteps.notAssignedOptionVerification();
		subjectSteps.visitSchedulingVerification(pendingVisitName);
		subjectSteps.unScheduleVisit();

		subjectSteps.selectAndScheduleVisitInPosition(skipedVisitName, 1);
		subjectSteps.visitStatusVerification(Constants.VISIT_STATUS_SKIPPED, pendingVisitName, 0);
		subjectSteps.visitSkippedVerification(pendingVisitName);
		subjectSteps.unScheduleVisit();

		subjectSteps.selectAndScheduleVisitInPosition(pendingVisitName, 0);
		templateSteps.assignTemplateToMe(pendingVisitName, 1, true, false);
		subjectSteps.deleteButtonVisitTableVerification(false);
		
		subjectSteps.addUnscheduledVisit(unscheduledVisitName);
		subjectSteps.visitStatusVerification(Constants.VISIT_STATUS_PENDING, pendingVisitName, 0);
		
		subjectSteps.selectVisitInPosition(unscheduledVisitName, 1);
		templateSteps.notAssignedOptionVerification();
		subjectSteps.unScheduleVisit();
		
		subjectSteps.selectVisitInPosition(pendingVisitName, 0);
		templateSteps.unAssignTemplate(1);
		subjectSteps.unScheduleVisit();

		subjectSteps.selectAndScheduleVisitInPosition(pendingVisitName, 0);
		templateSteps.assignTemplateToMe(pendingVisitName, 1, true, true);
		assessmentSteps.makeTemplateNotAdministered(adminLogin, adminPasword);
		subjectSteps.returnToSubjectDetails(subjectName);
		
		subjectSteps.addUnscheduledVisit(pendingVisitName);
		subjectSteps.selectVisitInPosition(pendingVisitName, 1);
//		subjectSteps.selectVisitWithStatus(pendingVisitName, Constants.VISIT_STATUS_PENDING);
		subjectSteps.visitStatusVerification(Constants.VISIT_STATUS_PENDING, pendingVisitName, 1);
		templateSteps.notAssignedOptionVerification();
		subjectSteps.unScheduleVisit();
	}
}

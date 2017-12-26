package steps.Tests;

import org.openqa.selenium.support.PageFactory;

import hu.siteportal.pdfreport.StepVerify;
import mt.siteportal.pages.CrVisitList;
import mt.siteportal.pages.HomePage;
import mt.siteportal.utils.Browser;
import mt.siteportal.utils.tools.HardVerify;
import mt.siteportal.utils.tools.Verify;
import steps.Abstract.AbstractStep;

public class CrVisitListSteps extends AbstractStep {

	public void navToCentralRating() {
		homePage = PageFactory.initElements(Browser.getDriver(), HomePage.class);
		if (homePage.isHomePageOpened()) {
			crVisitList = homePage.openCentralRating();
		}
		StepVerify.True(crVisitList.isCentralRatingOpened(), "Checking Central Rating page is opened.",
				"Central rating page is opened.", "Failed to open Central rating page");

	}

	public void verifyCrAssessmentLabelisDispalyed() {
		StepVerify.True(crVisitList.crAssessmentLabelisDispalyed(),
				"Checking CR Assessment lable is found on CR visit list page", "CR Assessment label is found",
				"CR Assessment label is not found");

	}

	public void veifyRefreshIconIsdisplayed() {
		StepVerify.True(crVisitList.crRefreshIconIsdisplayed(),
				"Checking Refresh Icon is present in CR visit list page", "Refresh icon is found",
				"Refresh icon is not found");

	}

	public void verifyDatePickerIsDisplayedWithCurrentDate(String dateFrom, String dateTo, String currentDate) {
		boolean scheduleDateFrom = crVisitList.crScheduleDatePickerIsDisplayed(dateFrom);
		boolean scheduleDateTo = crVisitList.crScheduleDatePickerIsDisplayed(dateTo);

		StepVerify.True(scheduleDateFrom && scheduleDateTo, "Checking Schedule Date From date picker is present",
				"Schedule Date From/To date picker is present", "Schedule Date From/To date picker is not present");

		String scheduleDateFromValue = crVisitList.crScheduleDatePickerGetSelectedDate(dateFrom);
		String scheduleDateToValue = crVisitList.crScheduleDatePickerGetSelectedDate(dateTo);

		StepVerify.True(
				scheduleDateFromValue.equalsIgnoreCase(currentDate)
						&& scheduleDateToValue.equalsIgnoreCase(currentDate),
				"Checking Selected  date From: " + scheduleDateFromValue + "/To: " + scheduleDateToValue
						+ " date picker is same as " + currentDate,
				"Selected date From: " + scheduleDateFromValue + "/To: " + scheduleDateToValue
						+ " date picker is mathced with " + currentDate,
				"Selected date From: " + scheduleDateFromValue + "/To: " + scheduleDateToValue
						+ " date picker is not mathced with " + currentDate);

	}

	public void verifyListIsShowingAllColoumns(String[] expectedColHeaders) {
		for (String colName : expectedColHeaders) {
			StepVerify.True(crVisitList.crExpectedColHeaderIsPresent(colName),
					"Checking " + colName + " column is present", "Coloumn " + colName + " is present",
					"Coloumn " + colName + " is not present");

		}
	}

	public void verifyListIsShowingOnlyCurrentDatesVisits(String currentDate) {
		crVisitList = PageFactory.initElements(Browser.getDriver(), CrVisitList.class);
		StepVerify.True(crVisitList.crVisitsAreForCurrentDateDispalyed(currentDate),
				"Checking Visit list contains only current date's visits", "Contains only current dates visits",
				"Showing other dates visits also");

	}

	public void verifyResetButtonIsDisplayed() {
		StepVerify.True(crVisitList.crResetButtonIsdisplayed(),
				"Checking Reset Button is present in CR visit list page", "Resent button is found",
				"Reset Button is not found");

	}

	public void verifyListIsShowingAllTextSearchBoxes(String[] expectedTextSearchBoxes) {
		for (String colName : expectedTextSearchBoxes) {
			StepVerify.True(crVisitList.crExpectedSearchBoxIsPresent(colName),
					"Checking " + colName + " search box is present", "Search box " + colName + " is present",
					"Search box " + colName + " is not present");

		}
	}

	public void verifyListIsShowingAllStatusDropdownMenus(String[] expectedStatusDropdowns) {
		for (String colName : expectedStatusDropdowns) {
			StepVerify.True(crVisitList.crExpectedStatusDropdownIsPresent(colName),
					"Checking " + colName + "  drop down menu is present", " Drop down menu " + colName + " is present",
					"Drop down menu " + colName + " is not present");

		}
	}

	public void verifyDateFromShouldBeSmallerThanTo(String scheduledDateFrom, String currentDate, String nextDayDate) {
		crVisitList = crVisitList.chooseDatePicker(nextDayDate, scheduledDateFrom);
		StepVerify.True(
				!crVisitList.crScheduleDatePickerGetSelectedDate(scheduledDateFrom).equalsIgnoreCase(nextDayDate),
				"Checking Schedule DateFrom Cant Be Greater Than DateTo", "DateFrom is smaller date than DateTo",
				"DateFrom is bigger date than DateTo");

	}

	public void verifyDateToShouldBeGreaterThanFrom(String scheduledDateTo, String currentDate, String yesterdayDate) {
		crVisitList = crVisitList.chooseDatePicker(yesterdayDate, scheduledDateTo);
		StepVerify.True(
				!crVisitList.crScheduleDatePickerGetSelectedDate(scheduledDateTo).equalsIgnoreCase(yesterdayDate),
				"Checking Schedule DateTo Cant Be Less Than DateFrom", "DateTo is bigger than DateFrom",
				"DateTo is smaller than DateFrom");

	}

	public void verifyListIsShowingVisitsForSelectedDate(String scheduledDateTo, String currentDate,
			String selectedDate) {
		crVisitList.chooseDatePicker(selectedDate, scheduledDateTo);
		StepVerify.True(crVisitList.crGetExpectedVisitList(currentDate, selectedDate),
				"Checking visit list based on selected date", "Visits are displayed on based on selected date",
				"Visits are dispalyed from different dates not as selected date");

	}

	public void verifyDatePickerIsSetToCurrentDateAfterClickOnResetButton(String scheduledDateFrom,
			String scheduledDateTo, String currentDate) {

		crVisitList = crVisitList.crScheduleDateFromAndToSetTocurrentDateAfterClickOnResetButton();
		HardVerify.True(crVisitList.crScheduleDatePickerGetSelectedDate(scheduledDateFrom).equalsIgnoreCase(currentDate)
				&& crVisitList.crScheduleDatePickerGetSelectedDate(scheduledDateTo).equalsIgnoreCase(currentDate),
				"Checking schedule date from/to set to current date after clicking reset button",
				"Schedule date from/to set to current date after clicking reset button",
				"Schedule date from/to is not set to current date after clicking reset button");

	}

	public void verifyVisitListIsRefreshedAfterClickOnRefreshIcon() {
		StepVerify.True(crVisitList.crRVisitListIsRefreshedAfterClickOnRefreshIcon(),
				"Checking CR visit list is refreshed on clicking refresh icon", "Rfreshed CR visit list",
				"Failed to refresh page");

	}

	public void verifyVisitCommentsAreDispalyedAsTooltip() {
		StepVerify.True(crVisitList.crVisitCommentsAreDispalyedAsTooltip(), "Checking Visit tooltip comments",
				"Visit tooltip is present with comments", "No Tooltip is present for cr visit list");

	}

	public void verifyVisitListIsFilteredBasedOnSubjectStatus(String statusDropDown, String[] subjectStatus) {
		for (String subStatus : subjectStatus) {
			crVisitList = crVisitList.chooseStatus(statusDropDown, subStatus);
			StepVerify.True(
					crVisitList.crVisitListAreFilteredBasedOnSubjectStatus(crVisitList.getCurrentListSize(), subStatus),
					"Checking Subject Status drop down menu with status= " + subStatus + " is present.",
					"CR Subject visits with status " + subStatus + " present in list",
					"There is no CR Subject visits with status " + subStatus + " in list");

		}
	}

	public void verifyScheuledTimeAndSiteTimeZonesAreDispalyedForScheduledVisit() {
		StepVerify.True(crVisitList.crScheuledTimeSiteTimeZonesAreDispalyedForScheduledVisit(),
				"Checking Scheduled tooltip comments present",
				"Scheuled Time & SiteTime Zones are Dispalyed for Scheduled Visit",
				"No Tooltip is present for cr Scheduled visit");

	}

	public void verifyVisitListIsFilteredBasedOnVisitStatus(String statusDropDown, String[] subjectVisitStatasuses) {
		crVisitList = crVisitList.crClikResetButton();
		for (String visitStatus : subjectVisitStatasuses) {
			crVisitList = crVisitList.chooseStatus(statusDropDown, visitStatus);
			StepVerify.True(
					crVisitList.crVisitListAreFilteredBasedOnSubjectStatus(crVisitList.getCurrentListSize(),
							visitStatus),
					"Checking Visit Status drop down menus with status= " + visitStatus + " is present in list",
					"CR visits with status " + visitStatus + " present in list",
					"There is no Cr visits with status= " + visitStatus + " in list");

		}

	}

	public void verifyStudyNameFormatIsCorrect() {
		crVisitList = crVisitList.crClikResetButton();
		StepVerify.True(crVisitList.crStudyNameFormatIsCorrect(), "Checking Study Name format",
				"All study names are correct format", "Study names format is not correct");

	}

	public void verifyVisitListIsFilteredBasedOnSearchItems(String[] expectedTextSearchBoxes, int[] searchIndex) {
		int i = 0;
		for (String searchbox : expectedTextSearchBoxes) {
			String getSearchOption = crVisitList.getSearchItem(searchIndex[i]);
			if (getSearchOption != null) {
				crVisitList = crVisitList.filteredVisitListBySearch(getSearchOption, searchbox);
				StepVerify.True(crVisitList.checkingFilteringListBySearchOption(getSearchOption, searchIndex[i]),
						"Filtering visit list by " + searchbox + "= " + getSearchOption,
						"Filtered Visit list are displayed based on " + searchbox + "= " + getSearchOption,
						"Filtered Visit list are not displayed based on " + searchbox + "= " + getSearchOption);

			}
			crVisitList = crVisitList.crClikResetButton();
			i++;
		}

	}

}

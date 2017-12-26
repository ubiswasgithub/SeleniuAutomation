package hu.siteportal.pdfreport;

import java.util.Iterator;
import java.util.List;

import org.testng.Assert;

import mt.siteportal.utils.tools.Log;

public class StepVerify extends PdfLog {

	public static void True(boolean condition, String stepMsg, String successMsg, String failMsg) {
		PdfLog.actionStep(stepMsg);
		PdfLog.expectedResultStep(successMsg);
		try {
			Log.logVerify(stepMsg);
			Assert.assertTrue(condition, successMsg);
			Log.logPass(successMsg);
			PdfLog.actualResultStep(successMsg);
			PdfLog.setStepStatus("Passed");
		} catch (AssertionError err) {
			Log.logFail(failMsg);
			PdfLog.actualResultStep(failMsg);
			PdfLog.setStepStatus("Failed");
			throw new AssertionError(failMsg, err);
		}
	}
	
	public static void actionTaken(String stepMsg){
		PdfLog.actionStep(stepMsg);
	}
	public static void True(String successMsg){
		PdfLog.expectedResultStep(successMsg);
		PdfLog.actualResultStep(successMsg);
		PdfLog.setStepStatus("Passed");
	}
	
	public static void False(boolean condition, String stepMsg, String successMsg, String failMsg) {
		PdfLog.actionStep(stepMsg);
		PdfLog.expectedResultStep(successMsg);
		try {
			Log.logVerify(stepMsg);
			Assert.assertFalse(condition, successMsg);
			Log.logPass(successMsg);
			PdfLog.actualResultStep(successMsg);
			PdfLog.setStepStatus("Passed");
		} catch (AssertionError err) {
			Log.logFail(failMsg);
			PdfLog.actualResultStep(failMsg);
			PdfLog.setStepStatus("Failed");
			throw new AssertionError(failMsg, err);
		}
	}
	
	public static void True(boolean condition, String successMsg, String failMsg) {
		PdfLog.expectedResultStep(successMsg);
		try {
			Assert.assertTrue(condition, successMsg);
			Log.logPass(successMsg);
			PdfLog.actualResultStep(successMsg);
			PdfLog.setStepStatus("Passed");
		} catch (AssertionError err) {
			Log.logFail(failMsg);
			PdfLog.actualResultStep(failMsg);
			PdfLog.setStepStatus("Failed");
			throw new AssertionError(failMsg, err);
		}
	}
	
	public static void False(boolean condition, String successMsg, String failMsg) {
		PdfLog.expectedResultStep(successMsg);
		try {
			Assert.assertFalse(condition, successMsg);
			Log.logPass(successMsg);
			PdfLog.actualResultStep(successMsg);
			PdfLog.setStepStatus("Passed");
		} catch (AssertionError err) {
			Log.logFail(failMsg);
			PdfLog.actualResultStep(failMsg);
			PdfLog.setStepStatus("Failed");
			throw new AssertionError(failMsg, err);
		}
	}
	
	public static void True(List<Boolean> condition, String stepMsg, List<String> successMsg, String successStatus,
			List<String> failMsg, String failStatus) {
		String success = "", failure = "";
		boolean status = true;
		Log.logDebugMessage(String.valueOf(condition.size()));
		Log.logDebugMessage(String.valueOf(successMsg.size()));
		Log.logDebugMessage(String.valueOf(failMsg.size()));
		
		Iterator<Boolean> conditionItr = condition.iterator();
		Iterator<String> successMsgItr = successMsg.iterator();
		Iterator<String> failMsgItr = failMsg.iterator();

		Log.logVerify(stepMsg);
//		PdfLog.actionStep(stepMsg);
		while (conditionItr.hasNext() && successMsgItr.hasNext() && failMsgItr.hasNext()) {
			Boolean conditionFound = conditionItr.next();
			String successMsgFound = successMsgItr.next();
			String failMsgFound = failMsgItr.next();

			try {
				Assert.assertTrue(conditionFound, successMsgFound);
				Log.logPass(successMsgFound);
				success += "- "+successMsgFound+"\n\n";
			} catch (AssertionError err) {
				Log.logFail(failMsgFound);
				failure += "- "+failMsgFound+"\n\n";
				status = false;
			}
		}
		True(status, stepMsg, success, failure);
	}
	
	public static void EqualsIgnoreCase(String actual, String expected, String stepMsg, String successMsg,
			String successStatus, String failMsg, String failStatus) {
		PdfLog.actionStep(stepMsg);
		PdfLog.expectedResultStep(successMsg);
		try {
			Log.logVerify(stepMsg);
			PdfLog.actionStep(stepMsg);
			Assert.assertTrue(actual.equalsIgnoreCase(expected), successMsg);
			Log.logPass(successMsg);
			PdfLog.actualResultStep(successMsg);
			PdfLog.setStepStatus(successStatus);
		} catch (AssertionError err) {
			Log.logFail(failMsg);
			PdfLog.actualResultStep(failMsg);
			PdfLog.setStepStatus(failStatus);
			throw new AssertionError(failMsg, err);
		}
	}

}

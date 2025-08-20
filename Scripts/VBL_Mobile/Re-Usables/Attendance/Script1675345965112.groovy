import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 5)

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 2, FailureHandling.OPTIONAL)) {
	Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
} else {
	
	if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/LocationAccess_PopUp Label'), 2, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Re-usable/Mobile Login/While using the app'), 5)
	}
	
	if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Mobile_locationAccess-OK button'), 1, FailureHandling.OPTIONAL)) {
		Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Mobile_locationAccess-OK button'), 5)
	}
	
	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Plus Icon'), 5)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-Working'), 0)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/RadioButton-PopUp-OK'), 5)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
}

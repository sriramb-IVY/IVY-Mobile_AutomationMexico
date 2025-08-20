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

//if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 2, FailureHandling.OPTIONAL)) {
//    'Main menu visible'
//    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
//} else {
//    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)
//
//    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
//}

if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-SYNCHRONIZATION'), 0)

    if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert-Orders exist without invoice creation'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

        //RetailerName = GlobalVariable.RetailerName
        Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-SYNCHRONIZATION'), 0)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert-Attendance Activity Not Completed'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 5)

        if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 0)
        }
        
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-SYNCHRONIZATION'), 0)
    }
    
    Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Data upload completed Successfully'), 120, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Data upload completed Successfully'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    println('Data upload completed Successfuly!')

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 10, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-DOWNLOAD'), 0)

    if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert-Attendance Activity Not Completed'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/Attendance_Menu'), 5)

        if (Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Attendance/AttendanceScreen-End Button'), 0)
        }
        
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Sync'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-SYNCHRONIZATION'), 0)

        Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Data upload completed Successfully'), 40)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-DOWNLOAD'), 0)
    }
    
    Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Downloaded Successfully'), 100, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Downloaded Successfully'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    println('Downloaded Successfully!')

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 0)
}
else {
	Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)
	
	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-SYNCHRONIZATION'), 0)
	
	Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Data upload completed Successfully'), 40)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-OK'), 0)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/Btn-DOWNLOAD'), 0)
	
	Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Downloaded Successfully'), 100, FailureHandling.STOP_ON_FAILURE)
	
		Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Sync/SyncAlert_Downloaded Successfully'), 5, FailureHandling.STOP_ON_FAILURE)
}
not_run: Mobile.closeApplication()


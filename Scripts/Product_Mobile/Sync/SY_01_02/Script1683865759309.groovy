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

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.scrollToText('Sync', FailureHandling.OPTIONAL)

if (Mobile.verifyElementExist(findTestObject('Mobile/MainMenu/Menu_Sync'), 2, FailureHandling.OPTIONAL)) {
	'Sync menu visible'
} else {
	
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
	
	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
}

if (Mobile.verifyElementVisible(findTestObject('Mobile/MainMenu/Menu_Sync'), 0, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

    Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

    if (Mobile.verifyElementVisible(findTestObject('Mobile/Sync/SyncAlert-Orders exist without invoice creation'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

        Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

        //RetailerName = GlobalVariable.RetailerName
        Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(3)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
		
		Mobile.scrollToText('Sync', FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

        Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('Mobile/Sync/SyncAlert-Attendance Activity Not Completed'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Global_SellerMenuName_Selection'), 5)

        if (Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Btn_END'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Mobile/Attendance/Btn_END'), 0)
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
		
		Mobile.scrollToText('Sync', FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

        Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)
    }
    
    Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 15, FailureHandling.OPTIONAL)

    Mobile.verifyElementVisible(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    println('Data upload completed Successfuly!')

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 10, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 0)

    if (Mobile.verifyElementVisible(findTestObject('Mobile/Sync/SyncAlert-Attendance Activity Not Completed'), 2, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Global_SellerMenuName_Selection'), 5)

        if (Mobile.verifyElementVisible(findTestObject('Mobile/Attendance/Btn_END'), 5, FailureHandling.OPTIONAL)) {
            Mobile.tap(findTestObject('Mobile/Attendance/Btn_END'), 0)
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)
		
		Mobile.scrollToText('Sync', FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

        Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

        Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 40)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 0)
    }
    
    Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Downloaded Successfully'), 200, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/Sync/SyncAlert_Downloaded Successfully'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    println('Downloaded Successfully!')

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

   
}


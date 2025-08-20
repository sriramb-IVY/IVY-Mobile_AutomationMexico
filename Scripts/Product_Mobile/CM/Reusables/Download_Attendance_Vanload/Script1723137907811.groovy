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

if (Mobile.verifyElementVisible(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 2, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 0)
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.OPTIONAL)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 0)
}

for (i = 1; i <= 15; i++) {
    if (Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Downloaded Successfully'), 15, FailureHandling.OPTIONAL)) {
        Mobile.verifyElementVisible(findTestObject('Mobile/Sync/SyncAlert_Downloaded Successfully'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        println('Downloaded Successfully!')

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

        break
    } else if (Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_DeviceID_Mismatch'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

       'App relaunch'
	    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.OPTIONAL)
	
	    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.OPTIONAL)
	
	    Mobile.tap(findTestObject('Mobile/Sync/Btn-DOWNLOAD'), 0, FailureHandling.OPTIONAL)
    }

	else if(Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Session Expired'), 5, FailureHandling.OPTIONAL)) {
	
		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)
		
		if( Mobile.verifyElementVisible(findTestObject('Mobile/Login/Field_EnterUsername'), 5, FailureHandling.OPTIONAL))
			{
		 
			 Mobile.sendKeys(findTestObject('Mobile/Login/Field_EnterUsername'), GlobalVariable.VanSeller_UserName)
		 
			 Mobile.sendKeys(findTestObject('Mobile/Login/Field_EnterPassword'), GlobalVariable.VanSeller_Password)
		 
			 Mobile.tap(findTestObject('Mobile/Login/Btn_Login'), 0)
		 
			 if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Popup_Title_ContinueInThisDevice'), 5, FailureHandling.OPTIONAL)) {
				 Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 10)
			 }
			 
			 Mobile.delay(10)
		 
			 for (int i = 0; i <= 15; i++) {
				 
				 if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Menu'), 5, FailureHandling.OPTIONAL)) {
					 'Successfully logged in.'
					 break
				 }
				 
				 if (Mobile.verifyElementVisible(findTestObject('null'), 5, FailureHandling.OPTIONAL)) {
					 Mobile.tap(findTestObject('null'), 0)
				 }
				 
				 if (Mobile.verifyElementVisible(findTestObject('Mobile/Login/Btn_Login'), 5, FailureHandling.OPTIONAL)) {
					 Mobile.tap(findTestObject('Mobile/Login/Btn_Login'), 0, FailureHandling.OPTIONAL)
		 
					 Mobile.delay(20)
				 }
				 
				 if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 5, FailureHandling.OPTIONAL)) {
					 Mobile.tap(findTestObject('Object Repository/Mobile/Common/PermissionAlert_Option_WhileUsingTheApp'), 0)
				 }
			  }	
			}
	   }
}
Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0, FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Start_Attendance'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/CM/Reusables/Stock_Update_Mobile'), [('ProductName') : findTestData('Mobile Input Data/CreditManagement').getValue('Credit_Management_SKU', 1), ('Quantity') : '1000'], FailureHandling.STOP_ON_FAILURE)


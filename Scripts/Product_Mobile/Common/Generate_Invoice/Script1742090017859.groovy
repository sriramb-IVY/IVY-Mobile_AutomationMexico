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

for (int i = 1; i <= 4; i++) {
	
    if (Mobile.verifyElementExist(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 5, FailureHandling.OPTIONAL)) {
        
		Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Enter_Collection_Amt_In_SummaryScreen'), [:], FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), 
            [('DateCount') : 0], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

        Mobile.waitForElementPresent(findTestObject('Mobile/Common/Btn_OK'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
		
		break
    }
	else
	{
		
		Mobile.swipe(50, 50, 50, 600, FailureHandling.OPTIONAL)
		
	if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Global_StoreMenuName_Selection', [('Name') : MenuName]), 
            1, FailureHandling.OPTIONAL)) {
		
            Mobile.tap(findTestObject('Mobile/Common/Global_StoreMenuName_Selection', [('Name') : MenuName]), 0)
			
			Mobile.delay(3)
			
			Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)
        }
		
		if (Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/OrderPopUp/OrderPopUp_Title'), 1, FailureHandling.OPTIONAL)) {
			
			Mobile.tap(findTestObject('Mobile/SummaryScreen/OrderPopUp/Edit_Order 1'), 0)
		}
	
	}
}



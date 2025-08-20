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
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

'Search and select the retailer in the tradecoverage screen.'

for (int a = 0; a <= 3; a++) {
   
    if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Retailer_Search_Icon'), 1, FailureHandling.OPTIONAL)) {
		
		Mobile.tap(findTestObject('Mobile/Common/Retailer_Search_Icon'), 0)
		
		if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Field_Search_EnterText'), 2, FailureHandling.OPTIONAL)) {
		
        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), RetailerName, 5)
		
		break
		}  
    }else
    {
		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 3)],
			FailureHandling.STOP_ON_FAILURE)
	} 
}

Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 0)


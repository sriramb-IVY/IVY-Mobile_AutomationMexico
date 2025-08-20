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

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

Mobile.tap(findTestObject('Object Repository/Mobile/Deviation/Trade Coverage Menu'), 3)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Start visit').getValue('Retailer', 1)

Mobile.sendKeys(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName)

Retailer_name = Mobile.getText(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('Retailer_name = ' + Retailer_name)

Mobile.verifyMatch(Retailer_name, GlobalVariable.ProductName, false)

Mobile.tap(findTestObject('Mobile/Seller Task/Retailer'), 0)

KeywordUtil.logInfo('verifing Start visit button')

Mobile.verifyElementVisible(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Seller Task/Start Visit button'), 0)

KeywordUtil.logInfo('Getting GPS Location Popup Message')

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Seller Task/Location message'), 
    0)

Mobile.tap(findTestObject('Object Repository/Mobile/Seller Task/Location message'), 5)

Mobile.takeScreenshot()

GPS_Message = Mobile.getText(findTestObject('Object Repository/Mobile/Seller Task/Location message'), 
    0)

KeywordUtil.logInfo('GPS_Message Popup = ' + GPS_Message)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Location validation yes button'), 0)

Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

Mobile.delay(3)

not_run: GlobalVariable.label = findTestData('Data Files/Web Input Data/Survey').getValue('Menu', 1)

not_run: Mobile.scrollToText(GlobalVariable.label)

Mobile.swipe(0, 150, 0, 400)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Deviation/Stock and Order Menu'), 5)

//	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
//	
//	Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)
//	
//	int Product_Name = 1
//	
//	GlobalVariable.ProductName = findTestData('Mobile Input Data/Deviation').getValue('Product', Product_Name)
//	
//	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)
//	
Mobile.tap(findTestObject('Mobile/Deviation/Enter Qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/1 qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Avail_CheckBox'), 5)

Product_Amt = Mobile.getText(findTestObject('Mobile/Deviation/Product Total AMT'), 5)

KeywordUtil.logInfo('Product_Amt is  = ' + Product_Amt)

Mobile.tap(findTestObject('Mobile/Deviation/Next button'), 5)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Scheme Next Button'), 5)

Mobile.delay(2)

//Mobile.tap(findTestObject('Mobile/Deviation/Summary Total AMT'), 0)
not_run: Summary_Amt = Mobile.getText(findTestObject('Mobile/Deviation/Summary Total AMT'), 5)

not_run: KeywordUtil.logInfo('Summary_Amt is  = ' + Summary_Amt)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Deviation/Invoice Button'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 5)

Mobile.delay(2)

INVOICE_CREATE_TEXT = Mobile.getText(findTestObject('Mobile/Deviation/Invoice created successfully Text'), 
    0)

KeywordUtil.logInfo('INVOICE_CREATE_TEXT is  = ' + INVOICE_CREATE_TEXT)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.swipe(0, 150, 0, 400)

Mobile.tap(findTestObject('Mobile/Seller Task/Close Call1 Button'), 3)

//Mobile.tap(findTestObject('Mobile/Seller Task/Select Reason for no order'), 3)
//
//Mobile.tap(findTestObject('null'), 3)
//
//Mobile.tap(findTestObject('Mobile/Seller Task/Remarks'), 3)
//
//Remark = 'Testing'
//
//Mobile.setText(findTestObject('Mobile/Seller Task/Remarks'), Remark, 5)
Mobile.waitForElementPresent(findTestObject('Mobile/Seller Task/Close Call2 Button'), 4)

Mobile.tap(findTestObject('Mobile/Seller Task/Close Call2 Button'), 4)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 4)

Mobile.closeApplication()

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

not_run: Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 
    20, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 20, FailureHandling.OPTIONAL)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/DB/Order_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/DB/Invoice_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)


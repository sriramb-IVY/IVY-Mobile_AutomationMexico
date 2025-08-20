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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Deviation/Trade Coverage Menu'), 3)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Deviation Button'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Deviation/Deviation Button'), 3)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/While using the app For Deviation'), 
    3, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Deviation/While using the app For Deviation'), 5)
}

//Mobile.checkElement(findTestObject(null), 0)
Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Deviation').getValue('Retailer', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

Mobile.tap(findTestObject('Mobile/Deviation/Retailer Name'), 3)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Add To Plan Button'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Add To Plan Button'), 5)

Mobile.delay(3)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Deviation/today task over RBTN'), 5)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Todays PLan ADD Button'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Todays PLan ADD Button'), 5)

KeywordUtil.logInfo('Saved successfully Popup')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Deviation').getValue('Retailer', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Deviation Icon Symbol'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Deviation/Deviation Icon Symbol'), 5)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Start Visit Button'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Start Visit Button'), 5)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

Mobile.delay(2)

GlobalVariable.label = findTestData('Data Files/Web Input Data/Survey').getValue('Menu', 1)

Mobile.scrollToText(GlobalVariable.label)

Mobile.tap(findTestObject('Mobile/Deviation/Stock and Order Menu'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

GlobalVariable.ProductName = findTestData('Mobile Input Data/Deviation').getValue('Product', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
    5)

Mobile.tap(findTestObject('Mobile/Deviation/Enter Qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/1 qty'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Avail_CheckBox'), 5)

Mobile.takeScreenshot()

Product_Amt = Mobile.getText(findTestObject('Mobile/Deviation/Product Total AMT'), 5)

KeywordUtil.logInfo('Product_Amt is  = ' + Product_Amt)

Mobile.tap(findTestObject('Mobile/Deviation/Next button'), 5)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Deviation/Scheme Next Button'), 5)

//Mobile.tap(findTestObject('Mobile/Deviation/Summary Total AMT'), 0)
Summary_Amt = Mobile.getText(findTestObject('Mobile/Deviation/Summary Total AMT'), 5)

KeywordUtil.logInfo('Summary_Amt is  = ' + Summary_Amt)

Mobile.tap(findTestObject('Mobile/Deviation/Invoice Button'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 5)

Mobile.delay(2)

Mobile.takeScreenshot()

INVOICE_CREATE_TEXT = Mobile.getText(findTestObject('Mobile/Deviation/Invoice created successfully Text'), 
    0)

KeywordUtil.logInfo('INVOICE_CREATE_TEXT is  = ' + INVOICE_CREATE_TEXT)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

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

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.label = findTestData('Data Files/Web Input Data/Survey').getValue('Menu', 2)

Mobile.scrollToText(GlobalVariable.label)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 
    20, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 20, FailureHandling.OPTIONAL)


import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 3)

Mobile.tap(findTestObject('Mobile/Deviation/Trade Coverage Menu'), 3)

Number_of_store_Before_Deviation = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Number_of_store'), 
    0)

KeywordUtil.logInfo('No.of Stores ' + Number_of_store_Before_Deviation)

KeywordUtil.logInfo('Tap the deviation button after enter the trade coverage screen')

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Deviation Button'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Deviation/Deviation Button'), 3)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/While using the app For Deviation'), 
    3, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Deviation/While using the app For Deviation'), 5)
}

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

KeywordUtil.logInfo('Retailer Is Already Planned For The Day')

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Deviation').getValue('planned_retailer', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

Mobile.tap(findTestObject('Mobile/Deviation/Retailer Name'), 3)

AppiumDriver driver = MobileDriverFactory.getDriver()

Planned_retailer_alertPopup = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Planned_retailer_alertPopup)

Mobile.verifyMatch(Planned_retailer_alertPopup, findTestData('Mobile Input Data/Deviation').getValue('Alert', 1), false)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Deviation Button'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Deviation/Deviation Button'), 3)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/While using the app For Deviation'), 
    3, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Deviation/While using the app For Deviation'), 5)
}

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 3)

KeywordUtil.logInfo('Plan For Current Date')

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Deviation').getValue('Retailer', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Deviation/Retailer Name'), 3)

Mobile.tap(findTestObject('Mobile/Deviation/Add To Plan Button'), 5)

Mobile.delay(2)

KeywordUtil.logInfo('Without Choosing Reason. Reason is mandatory')

Mobile.tap(findTestObject('Mobile/Deviation/Todays PLan ADD Button'), 5)

KeywordUtil.logInfo('Choosing Reason. Reason is mandatory')

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/today task over RBTN'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/today task over RBTN'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Todays PLan ADD Button'), 5)

KeywordUtil.logInfo('Saved successfully Popup')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.delay(3)

Number_of_store_After_Deviation = Mobile.getText(findTestObject('Mobile/Seller_2/Todays Plan/Number_of_store'), 
    0)

KeywordUtil.logInfo('No.of Stores After deviation ' + Number_of_store_After_Deviation)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.tap(findTestObject('Mobile/Common/Field_Search_EnterText'), 5)

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Deviation').getValue('Retailer', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName, 5)

KeywordUtil.logInfo('After Deviate the store having deviation symbol')

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Deviation Icon Symbol'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Deviation Icon Symbol'), 5)

Mobile.delay(2)

Mobile.verifyElementVisible(findTestObject('Mobile/Deviation/Start Visit Button'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Start Visit Button'), 5)

Mobile.tap(findTestObject('Mobile/Deviation/Location validation yes button'), 5)

Mobile.delay(6)

GlobalVariable.label = findTestData('Data Files/Web Input Data/Survey').getValue('Menu', 1)

//Mobile.scrollToText(GlobalVariable.label)
Mobile.swipe(0, 150, 0, 400)

Mobile.tap(findTestObject('Mobile/Deviation/Stock and Order Menu'), 5)

Mobile.delay(1)

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0, 
    FailureHandling.OPTIONAL)

Mobile.delay(1)

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

Mobile.verifyMatch(Summary_Amt, Product_Amt, false)

Mobile.tap(findTestObject('Mobile/Deviation/Invoice Button'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 3)

Mobile.delay(2)

Mobile.takeScreenshot()

INVOICE_CREATE_TEXT = Mobile.getText(findTestObject('Mobile/Deviation/Invoice created successfully Text'), 
    15)

KeywordUtil.logInfo('INVOICE_CREATE_TEXT is  = ' + INVOICE_CREATE_TEXT)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 3)

Mobile.delay(2)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.delay(2)

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

not_run: GlobalVariable.label = findTestData('Data Files/Web Input Data/Survey').getValue('Menu', 2)

not_run: Mobile.scrollToText(GlobalVariable.label)

not_run: Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Sync'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Sync/Btn-SYNCHRONIZATION'), 0)

not_run: Mobile.waitForElementPresent(findTestObject('Mobile/Sync/SyncAlert_Data upload completed Successfully'), 
    20, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 20, FailureHandling.OPTIONAL)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/DB/Invoice_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/DB/Order_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)


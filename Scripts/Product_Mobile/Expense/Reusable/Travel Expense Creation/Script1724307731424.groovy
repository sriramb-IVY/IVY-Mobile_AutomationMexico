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
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebElement as WebElement

//Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

//CustomKeywords.'android.Keywords.android_custom_keywords.Resetapk'()
Mobile.delay(5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.scrollToText('Expense', FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Menu_Expense'), 0)

'Create new expense'
Mobile.tap(findTestObject('Mobile/Expense/Field_Date'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-EnableValidation'), [('DateCount') : -2], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 0)

GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 3)

Mobile.tap(findTestObject('Mobile/Expense/Dropdown_Global'), 0)

Mobile.setText(findTestObject('Mobile/Expense/Field_Amount'), findTestData('Mobile Input Data/Expense').getValue(
        'Amount', 3), 0)

Mobile.setText(findTestObject('Mobile/Expense/Field_Remark'), findTestData('Mobile Input Data/Expense').getValue(
        'Remarks', 1), 0)

Mobile.tap(findTestObject('Mobile/Expense/Camera_Icon'), 0)

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_CenterIcon'), 0, 
    FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Camera_CenterIcon'), 0)

Mobile.takeScreenshot()

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0)

'The "Save Successfully." alert toast should be visible.'
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.takeScreenshot()

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 3), false)

'The Total Amount should be shown expense value '
Mobile.takeScreenshot()

expense_val = Mobile.getText(findTestObject('Mobile/Expense/Field_TotalAmount'), 0)

expected_expense_Val = findTestData('Mobile Input Data/Expense').getValue('Amount', 3)

Mobile.verifyEqual(Double.parseDouble(expense_val), Double.parseDouble(expected_expense_Val), FailureHandling.STOP_ON_FAILURE)

//Mobile.verifyElementText(findTestObject('Mobile/Expense/Field_TotalAmount'), findTestData('Mobile Input Data/Expense').getValue('Amount', 1))
//'The following saved details should be visible in the grid:'
//Mobile.takeScreenshot()
//
//Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Date(YYYY-MM-DD)'), [('DateCount') : -2], FailureHandling.STOP_ON_FAILURE)
//
//GlobalVariable.label = GlobalVariable.sDate

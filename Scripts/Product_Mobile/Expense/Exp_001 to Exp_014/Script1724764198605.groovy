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

Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Expense_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.scrollToText('Expense', FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Menu_Expense'), 0)

'The user should be navigated to the Expense screen.'
Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Expense/Header_Expense'), findTestData('Mobile Input Data/Expense').getValue('Header', 1))

'The following fields should be displayed in the Expense screen.'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Start Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Expense Type'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Amount'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Remarks'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Capture proof'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Clear'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Tab-CURRENT MONTH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Tab_MTD'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Tab_P3M'), 0)

'The above details should be visible in the grid also.'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Grid_Title_Start Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Grid_Title_Expense Type'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Grid_Title_Amount'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Grid_Title_Capture proof'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Grid_Title_Remarks'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Total Amount'), 0)

'The Calender icon sholud be present.'
Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Field_Date'), 0)

'The Date Picker opens while tapping the Calender icon.'
Mobile.tap(findTestObject('Mobile/Expense/Field_Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Calendar(day,date,month)'), 0)

'The date picker should not allow to select the Future dates.'
Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-DisableValidation'), [('DateCount') : 1], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 2)

'The date picker should not allow to select the dates beyond 30 days.'
Mobile.tap(findTestObject('Mobile/Expense/Field_Date'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-DisableValidation'), [('DateCount') : -31], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 2)

'The date picker should allow to select within the 30 days.'
Mobile.tap(findTestObject('Mobile/Expense/Field_Date'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-EnableValidation'), [('DateCount') : -2], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

'The date selected from the Date picker should be visible correctly in the Start Date field.'
Mobile.takeScreenshot()

get_date = Mobile.getText(findTestObject('Mobile/Expense/Field_Date'), 0)

'The Date format should be YYYY/MM/DD'
Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Date'), [('DateCount') : -2], FailureHandling.STOP_ON_FAILURE)

String selectedDate = GlobalVariable.sDate

Mobile.verifyMatch(get_date, selectedDate, false, FailureHandling.STOP_ON_FAILURE)

'Please Select Expense Type !! -  toast messages should be visible When tap "Done button" without entering expense type'
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.takeScreenshot()

AppiumDriver driver = MobileDriverFactory.getDriver()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 1), false)

'Please Select Expense Type !! - toast messages should be visible when the user tries to capture the proof without selecting the expense type '
Mobile.tap(findTestObject('Object Repository/Mobile/Expense/Camera_Icon'), 0)

Mobile.takeScreenshot()

//AppiumDriver driver = MobileDriverFactory.getDriver()
Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 1), false)

'The following expense type should be visible in the Expense Type dropdown:'
Mobile.tap(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Dropdown_Travel Expense'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Dropdown_Value_Daily Expense'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Dropdown_Value_Others'), 0)

'The selected type should be visible in the Expense Type field.'
GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 3)

Mobile.tap(findTestObject('Object Repository/Mobile/Expense/Dropdown_Global'), 0)

Mobile.delay(2)

Mobile.verifyElementText(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), GlobalVariable.label)

'Please enter amount!! -  toast messages should be visible When tap "Done button" without entering amount'
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.takeScreenshot()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 2), false)

'Please enter amount!! -   toast messages should be visible when the user tries to capture the proof without entering the amount'
Mobile.tap(findTestObject('Mobile/Expense/Camera_Icon'), 0)

Mobile.takeScreenshot()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 2), false)

'The Amount field should be an editable one.'
Mobile.tap(findTestObject('Mobile/Expense/Field_Amount'), 0)

Mobile.hideKeyboard()

expected_properties = Mobile.getAttribute(findTestObject('Mobile/Expense/Field_Amount'), 'clickable', 0)

Mobile.verifyMatch('true', expected_properties, false)

'An expense cannot be created when the amount is zero.'
Mobile.setText(findTestObject('Mobile/Expense/Field_Amount'), '0', 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.takeScreenshot()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 2), false)

Mobile.setText(findTestObject('Mobile/Expense/Field_Amount'), findTestData('Mobile Input Data/Expense').getValue('Amount', 3), 0)

entered_amt = Mobile.getText(findTestObject('Mobile/Expense/Field_Amount'), 0)

'After entering the amount, when the user chooses the expense type the enerted amount should be cleared.'
Mobile.tap(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 0)

GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 2)

Mobile.tap(findTestObject('Object Repository/Mobile/Expense/Dropdown_Global'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Expense/Field_Amount'), '')

Mobile.setText(findTestObject('Mobile/Expense/Field_Amount'), findTestData('Mobile Input Data/Expense').getValue('Amount', 1), 0)

'The Remarks field should be an editable one.'
Mobile.tap(findTestObject('Mobile/Expense/Field_Remark'), 0)

Mobile.hideKeyboard()

expected_properties = Mobile.getAttribute(findTestObject('Mobile/Expense/Field_Remark'), 'clickable', 0)

Mobile.verifyMatch('true', expected_properties, false)

'The Remarks field should accept the alphanumeric and special characters.'
Mobile.setText(findTestObject('Mobile/Expense/Field_Remark'), findTestData('Mobile Input Data/Expense').getValue('Remarks', 1), 0)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Expense/Field_Remark'), findTestData('Mobile Input Data/Expense').getValue('Remarks', 1))

'The camera icon should be visible.'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Camera_Icon'), 0)

'The camera should be opened while tapping the camera icon.'
Mobile.tap(findTestObject('Mobile/Expense/Camera_Icon'), 0)

Mobile.takeScreenshot()

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_CenterIcon'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Expense/Camera_CenterIcon'), 0)

Mobile.tap(findTestObject('Mobile/Expense/Camera_CenterIcon'), 0)

'The user can choose the captured image by tapping the tick icon.'
Mobile.takeScreenshot()

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0)

' The user should be able to capture the image and The captured image count should be visible in the Capture Proof field.'
Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/ImageCount'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementText(findTestObject('Mobile/Expense/ImageCount'), '1')

'The entered details should be deleted while clicking the Clear button.'
Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), '---Select---')

Mobile.verifyElementText(findTestObject('Mobile/Expense/Field_Amount'), '')

Mobile.verifyElementText(findTestObject('Mobile/Expense/Field_Remark'), '')

Mobile.verifyElementText(findTestObject('Mobile/Expense/ImageCount'), '')

'Create new expense'
Mobile.tap(findTestObject('Mobile/Expense/Field_Date'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-EnableValidation'), [('DateCount') : -2], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 0)

GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 1)

Mobile.tap(findTestObject('Mobile/Expense/Dropdown_Global'), 0)

Mobile.setText(findTestObject('Mobile/Expense/Field_Amount'), findTestData('Mobile Input Data/Expense').getValue('Amount', 1), 0)

Mobile.setText(findTestObject('Mobile/Expense/Field_Remark'), findTestData('Mobile Input Data/Expense').getValue('Remarks', 1), 0)

Mobile.tap(findTestObject('Mobile/Expense/Camera_Icon'), 0)

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_CenterIcon'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Camera_CenterIcon'), 0)

Mobile.takeScreenshot()

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0)

'The "Save Successfully." alert toast should be visible.'
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.takeScreenshot()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 3), false)

'The Total Amount should be shown expense value '
Mobile.takeScreenshot()

expense_val = Mobile.getText(findTestObject('Mobile/Expense/Field_TotalAmount'), 0)

expected_expense_Val = findTestData('Mobile Input Data/Expense').getValue('Amount', 1)

Mobile.verifyEqual(Double.parseDouble(expense_val), Double.parseDouble(expected_expense_Val), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Create new expense-2'
Mobile.tap(findTestObject('Mobile/Expense/Field_Date'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Mobile Date Picker-EnableValidation'), [('DateCount') : -2], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.tap(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 0)

GlobalVariable.label = findTestData('Mobile Input Data/Expense').getValue('Expense_Type', 2)

Mobile.tap(findTestObject('Mobile/Expense/Dropdown_Global'), 0)

Mobile.setText(findTestObject('Mobile/Expense/Field_Amount'), findTestData('Mobile Input Data/Expense').getValue('Amount', 2), 0)

Mobile.setText(findTestObject('Mobile/Expense/Field_Remark'), findTestData('Mobile Input Data/Expense').getValue('Remarks', 2), 0)

Mobile.tap(findTestObject('Mobile/Expense/Camera_Icon'), 0)

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_CenterIcon'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Expense/Camera_CenterIcon'), 0)

Mobile.takeScreenshot()

Mobile.waitForElementPresent(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/Expense/Camera_TickBtn'), 0)

'The "Save Successfully." alert toast should be visible.'
Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.takeScreenshot()

Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

KeywordUtil.logInfo(Actualtoastmessage)

Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/Expense').getValue('Alerts', 3), false)

Mobile.takeScreenshot()

'The Total Amount should be calculated as follows: Total Amount = Sum of all the expense amount. '
Sum_amt = Mobile.getText(findTestObject('Mobile/Expense/Field_TotalAmount'), 0, FailureHandling.STOP_ON_FAILURE)

ExpenseValue_1 = findTestData('Mobile Input Data/Expense').getValue('Amount', 1)

ExpenseValue_2 = findTestData('Mobile Input Data/Expense').getValue('Amount', 2)

Expected_amt = (Double.parseDouble(ExpenseValue_1) + Double.parseDouble(ExpenseValue_2))

KeywordUtil.logInfo(Expected_amt.toString())

sum_amt = Double.parseDouble(Sum_amt)

Mobile.verifyEqual(sum_amt, Expected_amt)

Mobile.verifyEqual(Double.parseDouble(Sum_amt), Expected_amt)

Mobile.takeScreenshot()

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

'Creating a New Expense for Rejection Flow:-'
WebUI.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Expense Creation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

'Creating a New Expense for Approval Flow:-'
Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Reusable/Travel Expense Creation'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)


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
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Expense/Expense_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APK_File, false)

//CustomKeywords.'android.Keywords.android_custom_keywords.Resetapk'()
Mobile.delay(5)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Seller_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 24), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

'The user should be navigated to the Expense screen.'
Mobile.takeScreenshot()

Mobile.verifyElementText(findTestObject('Mobile/Expense/Header_Expense'), findTestData('Mobile Input Data/Expense').getValue('Header', 1))

'The following fields should be displayed in the Expense screen.'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Tab-CURRENT MONTH'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Tab_MTD'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Tab_P3M'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Cash In Hand'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Label_Total Amount'), 0, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Plus'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Plus'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Clear'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Field_Amount'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Field_Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Field_Remark'), 0)

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

Mobile.setText(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 'Daily Expense', 0, FailureHandling.STOP_ON_FAILURE)

String boundary = Mobile.getAttribute(findTestObject('Mobile/Expense/Field_Dropdown_ExpenseType'), 'bounds', 0, FailureHandling.STOP_ON_FAILURE)

String[] End_y = boundary.replace(']', '').split(',')

KeywordUtil.logInfo(End_y[2])

Mobile.tapAtPosition(100, Integer.parseInt(End_y[2]) + 30, FailureHandling.STOP_ON_FAILURE)


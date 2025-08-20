import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import org.openqa.selenium.Keys as Keys
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import org.openqa.selenium.By as By
import org.openqa.selenium.WebElement as WebElement
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import java.text.SimpleDateFormat as SimpleDateFormat
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
//import io.appium.java_client.MobileElement as MobileElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.DateFormat as DateFormat
import java.util.Date as Date

'Completed the Collection'
if (Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-Collection'), 2, FailureHandling.OPTIONAL)) {
    'Collection menu visible'
} else {
    Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Mobile_Login/Relaunch APK'), [:], FailureHandling.CONTINUE_ON_FAILURE)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

    Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.RetailerName = findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('Retailer_Name', 1)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Trade Coverage'), 0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Search Icon'), 5)

    Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Retailer Enter Search Field'), GlobalVariable.RetailerName, 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/stores click'), 5)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Start Visit Button'), 5)

    if (Mobile.verifyElementVisible(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/Location Validation'), 5, FailureHandling.OPTIONAL)) {
        Mobile.tap(findTestObject('Object Repository/XXXXXXXXXXXX/Mobile Part/Store_Actvy/location validtion- YES'), 5)
    }
    
    Mobile.delay(7)

    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
}

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-Collection'), 0)

Mobile.delay(2)

String pattern = 'yyyy/MM/dd'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

ScrollDate = (currentdate + '(')

Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

GlobalVariable.label = currentdate

int b = Mobile.getDeviceHeight()

int y1 = b / 2

int y2 = y1 - 30

Mobile.swipe(10, y1, 10, y2)

TransactionNo = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Get_TransactionNo'), 0)

GlobalVariable.Transaction_No = TransactionNo

Transaction_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Transaction_Amt'), 0)

GlobalVariable.C_TransactionAmt = Transaction_Amt

exlpath = 'DDF\\VBL\\Web Input Data\\Web Input Data.xlsx'

Sheetname = 'Stock_Cash_Reconciliation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 23, GlobalVariable.Transaction_No)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 24, GlobalVariable.C_TransactionAmt)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

pay = (Double.parseDouble(Transaction_Amt) / 3)

BigDecimal single_pay = new BigDecimal(pay).setScale(0, RoundingMode.HALF_DOWN)

KeywordUtil.logInfo(single_pay.toString())

before_OS_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/OS_Amt'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Get_TransactionNo'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Pay_Btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Menu-Cash'), 0)

Mobile.takeScreenshot()

Mobile.clearText(findTestObject('Mobile/Collection/Cash/Amount'), 0, FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Mobile/Collection/Cash/Amount'), single_pay.toString(), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

Mobile.takeScreenshot()

'Validation for Cheque'
mode_2 = Mobile.getText(findTestObject('Mobile/Collection/Cheque/Cheque_Submenu'), 0)

Mobile.tap(findTestObject('Mobile/Collection/Cheque/Cheque_Submenu'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can able to select cheque mode of payment')

Mobile.setText(findTestObject('Mobile/Collection/Cheque/Enter_ChequeNo'), '12345', 0, FailureHandling.STOP_ON_FAILURE)

String bound = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/Select Bank'), 'bounds', 2, FailureHandling.OPTIONAL)

x = Mobile.getAttribute(findTestObject('Mobile/Collection/Cheque/Select Bank'), 'x', 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Collection/Cheque/Select Bank'), 0)

Bank_Name = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Bank_Name', 1)

Mobile.sendKeys(findTestObject('Mobile/Collection/Cheque/Select Bank'), Bank_Name)

Mobile.delay(2)

String[] arr = bound.split(']')

Start_Y_value = (arr[0])

End_Y_value = (arr[1])

String[] ar = Start_Y_value.split(',')

Start_Y_value = (ar[1])

KeywordUtil.logInfo(Start_Y_value)

String[] ar1 = End_Y_value.split(',')

End_Y_value = (ar1[1])

KeywordUtil.logInfo(End_Y_value)

y_point = (Integer.parseInt(End_Y_value) + 20)

x_point = (Integer.parseInt(x) + 30)

Mobile.tapAtPosition(x_point, y_point)

Mobile.delay(3)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Edit_BankName'), 0)

//Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Edit_BankName'),findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Bank_Name', 2))
Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Edit_BankName'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Bank_Name', 2), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Edit_BranchName'), 0)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Edit_BranchName'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Branch_Name', 1), 0, FailureHandling.STOP_ON_FAILURE)

//Mobile.sendKeys(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Edit_BranchName'), findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Branch_Name', 1))
Mobile.clearText(findTestObject('Mobile/Collection/Cheque/Enter_Amount_Field'), 0)

Mobile.sendKeys(findTestObject('Mobile/Collection/Cheque/Enter_Amount_Field'), single_pay.toString())

Mobile.hideKeyboard(FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

'Validation for creditnote'
Mobile.tap(findTestObject('Mobile/Collection/CreditNote/Credit Note_Submenu'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can able to select cheque mode of payment')

Mobile.tap(findTestObject('Mobile/Collection/CreditNote/CreditNote_CheckBox'), 0)

Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

Mobile.takeScreenshot()

println('Edited amount value should be displated correctly')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/SAVE_Btn'), 0)

Mobile.delay(2)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/Enter Receipt Number'), findTestData('VBL_Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Collection/OK_Btn'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)


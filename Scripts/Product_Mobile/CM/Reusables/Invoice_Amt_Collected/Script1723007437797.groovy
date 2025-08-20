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
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

/*Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

GlobalVariable.RetailerName = findTestData('Mobile Input Data/Limit-Bill_Combination').getValue('Credit_limit_retailer', 1)

Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName,
	5)

Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)

if (Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Common/Popup_Title_LocationValidation'),
	5, FailureHandling.OPTIONAL)) {
	Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
}*/

RetailerIndex= Credit_Index

Mobile.delay(2)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-Collection'), 0)

String pattern = 'dd-MM-yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

ScrollDate = (currentdate + '(')

GlobalVariable.label = currentdate

Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can view the list of Pending Invoices details')

TransactionNo = Mobile.getText(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

GlobalVariable.Transaction_No = TransactionNo

String exlpath = 'DDF\\Mobile Input data\\Mobile Input data.xlsx'

String Sheetname = 'CreditManagement'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, RetailerIndex, 8, TransactionNo)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Transaction_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Transaction_Amt'), 0)

GlobalVariable.C_TransactionAmt = Transaction_Amt

//before_OS_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/OS_Amt'), 0)

Mobile.tap(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Collection/Blue_Tick'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can able to select any invoice')

Mobile.tap(findTestObject('Mobile/Collection/Pay_Btn'), 0)

'Validation for Cash'
mode_1 = Mobile.getText(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

Mobile.tap(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

Mobile.tap(findTestObject('Mobile/Collection/SAVE_Btn'), 0)

Mobile.setText(findTestObject('Mobile/Collection/Enter Receipt Number'), findTestData('Mobile Input Data/CallAnalysis').getValue('InvoiceNo', 1), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)


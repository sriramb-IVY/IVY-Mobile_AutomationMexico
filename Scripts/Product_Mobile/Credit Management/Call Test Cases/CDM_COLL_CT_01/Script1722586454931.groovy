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

GlobalVariable.RetailerName = findTestData('Data Files/Mobile Input Data/Period_Bal_Combination').getValue('Retailer', 2)

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
////////
Mobile.delay(6)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

not_run: Mobile.delay(3)

not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-Collection'), 0)

String pattern = 'dd-MM-yyyy'

SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

String currentdate = simpleDateFormat.format(new Date())

ScrollDate = (currentdate + '(')

GlobalVariable.label = currentdate

Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice No'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo_Date'), 
    0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/OS Amt'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/OS_Amt'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Due Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Due Date_Value'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Amt'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Transaction_Amt'), 
    0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Received'), 0)

//Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Received_Amt'), 0)
Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can view the list of Pending Invoices details')

TransactionNo = Mobile.getText(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 
    0)

GlobalVariable.Transaction_No = TransactionNo

Transaction_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Transaction_Amt'), 
    0)

GlobalVariable.C_TransactionAmt = Transaction_Amt

pay = Double.parseDouble(Transaction_Amt)

BigDecimal single_pay = new BigDecimal(pay).setScale(0, RoundingMode.HALF_DOWN)

KeywordUtil.logInfo(single_pay.toString())

//before_OS_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/OS_Amt'), 0)
Mobile.tap(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Collection/Blue_Tick'), 
    0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can able to select any invoice')

Mobile.tap(findTestObject('Mobile/Collection/Pay_Btn'), 0)

//Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Bill Payment Screen_Title'),
//	0)
Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can able to navigate Bill payment screen while click the pay button')

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Payment Amount_Value'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Balance Amount_Value'), 0)

Mobile.takeScreenshot()

KeywordUtil.logInfo('The user can able to view the Payment Amount_Value and Balance Amount_Value')

'Validation for Cash'
mode_1 = Mobile.getText(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

Mobile.tap(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

Mobile.clearText(findTestObject('Mobile/Collection/Cash/Amount'), 0, FailureHandling.OPTIONAL)

Mobile.setText(findTestObject('Mobile/Collection/Cash/Amount'), single_pay.toString(), 0, 
    FailureHandling.STOP_ON_FAILURE)

//Paid_Amount = Mobile.getText(findTestObject('Mobile/Collection/Cash/Amount'), 0)
Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

/*Paid_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Paid_Amt'), 0)

Paid_Amt2 = Paid_Amt.replaceAll('\\s', '')

Exepected_Cash_Paid_Amount = Paid_Amt2.replaceAll('Paid', '')

Mobile.verifyEqual(Double.parseDouble(Paid_Amount), Double.parseDouble(Exepected_Cash_Paid_Amount), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()*/
Mobile.tap(findTestObject('Mobile/Collection/SAVE_Btn'), 0)

Mobile.setText(findTestObject('Mobile/Collection/Enter Receipt Number'), findTestData('Mobile Input Data/CallAnalysis').getValue(
        'InvoiceNo', 1), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

Mobile.delay(5)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Print_Bluetooth_Device'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

//Mobile.verifyElementVisible(findTestObject('Mobile/Common/Icon_Back'), 10)
Mobile.delay(7)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)


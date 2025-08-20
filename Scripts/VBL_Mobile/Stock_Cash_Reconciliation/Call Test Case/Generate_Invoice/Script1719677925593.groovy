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

'Generated Invoice '

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('SKU', 1)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

GlobalVariable.keypadValue = findTestData('VBL_Web Input Data/Stock_Cash_Reconciliation').getValue('Invoice_Qty', 1)

KeywordUtil.logInfo(GlobalVariable.keypadValue)

String Slab_Qty = Integer.parseInt(GlobalVariable.keypadValue)

length = Slab_Qty.size()

if (2 == length) {
	GlobalVariable.keypadValue = Slab_Qty.charAt(0)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

	GlobalVariable.keypadValue = Slab_Qty.charAt(1)

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
} else {
	GlobalVariable.keypadValue = Slab_Qty

	Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

Mobile.takeScreenshot()

FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

exlpath = 'DDF\\VBL\\Web Input Data\\Web Input Data.xlsx'

Sheetname = 'Stock_Cash_Reconciliation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 9, FreeSKU_Qty_Slab1)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Done_Btn'), 0)

Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-Invoice btn'), 0)

DateFormat dateFormat = new SimpleDateFormat('dd-MMM-yyyy')

Date date = new Date()

String today = dateFormat.format(date)

println(today)

DateValue = today

String[] sDate = DateValue.split('-')

println(((((sDate[0]) + ';') + (sDate[1])) + ';') + (sDate[2]))

String day = sDate[0]

println(day.substring(0))

println(day.charAt(1))

if (day.startsWith('0') == true) {
    GlobalVariable.sDate = day.charAt(1)
} else {
    GlobalVariable.sDate = (sDate[0])
}

if (Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 2, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 0)

    Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 3, FailureHandling.OPTIONAL)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)


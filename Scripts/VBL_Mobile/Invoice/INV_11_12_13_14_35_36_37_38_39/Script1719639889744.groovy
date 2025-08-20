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
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
//import io.appium.java_client.MobileElement as MobileElement
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import java.text.DateFormat as DateFormat
import java.text.SimpleDateFormat as SimpleDateFormat
import java.util.Date as Date

//Mobile.startApplication(GlobalVariable.APK_File, false)
//
//Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/MainMenu/Menu_Load Management'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/SubMenu_Current Stock view'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/Invoice').getValue('Product_Name', 1)

println(GlobalVariable.ProductName)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search Field'), GlobalVariable.ProductName, 
    0, FailureHandling.OPTIONAL)

Exepected_SIH_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/LoadManagement/CurrentStockView/SIH_Value'), 
    0)

println('vanload_SIH_value  : ' + Exepected_SIH_value)

exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Invoice'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, Exepected_SIH_value)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData(
            'VBL_Mobile Input Data/Invoice').getValue('Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

println(GlobalVariable.ProductName)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 
    5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

'To verify whether users can select when stock is available for SKU.'
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

println('Verified : the Product detail Screen')

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 
    0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 
    0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 
    0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 
    0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 
    0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 
    0)

Mobile.verifyElementExist(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/SIH_Value_Field'), 
    0)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/SIH_Value_Field'), 
    0)

Mobile.takeScreenshot()

println('Verified : the Product detail Fields')

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 
    0)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 
    0)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 
    0)

Actual_SIH = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/SIH_Value_Field'), 
    0)

GlobalVariable.BasePrice = findTestData('VBL_Mobile Input Data/Invoice').getValue('BasePrice', 1)

GlobalVariable.PiecePrice = findTestData('VBL_Mobile Input Data/Invoice').getValue('PiecePrice', 1)

GlobalVariable.CaseSize = findTestData('VBL_Mobile Input Data/Invoice').getValue('CaseSize', 1)

Mobile.verifyEqual(Double.parseDouble(Actual_BasePrice), Double.parseDouble(GlobalVariable.BasePrice), FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(Actual_PiecePrice), Double.parseDouble(GlobalVariable.PiecePrice), FailureHandling.CONTINUE_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(Actual_CaseSize), Double.parseDouble(GlobalVariable.CaseSize), FailureHandling.STOP_ON_FAILURE)

Mobile.verifyEqual(Double.parseDouble(Actual_SIH), Double.parseDouble(Exepected_SIH_value), FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

println('Verified : the Product detail Field Values')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 4)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 10)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

actualSIH = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0, FailureHandling.STOP_ON_FAILURE)

'INV_37 - To verify whether users are prevented from entering quantities exceeding the available SIH'
Mobile.verifyNotEqual(actualSIH, 4000, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 1, FailureHandling.OPTIONAL)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Invoice').getValue('Keypad_Number', 4)

println(GlobalVariable.keypadValue)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Actual_piece_value = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Piece_Field'), 0)

SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 
    0)

Mobile.verifyMatch(Actual_piece_value, GlobalVariable.keypadValue, false, FailureHandling.CONTINUE_ON_FAILURE)

println('INV_14 To verify the Product details tab functionality')

Mobile.takeScreenshot()

println('Verified :User should be able to take order through the Product details page')

exlpath = 'DDF\\VBL\\Web Input Data\\Web Input Data.xlsx'

Sheetname = 'Sales_Order_Invoice_View'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 0, GlobalVariable.ProductName)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 1, Actual_PiecePrice)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 2, GlobalVariable.keypadValue)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 3, SKU_TOTAL)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Test Cases/VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

Mobile.takeScreenshot()

TAX_Amount = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

Gross_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-Invoice btn'), 0)

'To verify whether the user can view the fields and label of the Delivery Date popup screen in the summary screen while generating an order and invoice.'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/Expense/Do you want to save'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('null'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.verifyElementVisible(findTestObject('Mobile/Common/Btn_Cancel'), 0)

'To verify that selecting the Cancel button closes the calendar popup without loading data.'
Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 0)

Mobile.takeScreenshot()

Mobile.verifyElementNotExist(findTestObject('null'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Cancel'), 0)

Expected_Text = Mobile.getText(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 0)

Mobile.verifyMatch(Expected_Text, '', false)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 0)

'User should not be able to pass past date in the calender popup screen'
SimpleDateFormat sdf = new SimpleDateFormat('dd-MMM')

Calendar c = Calendar.getInstance()

c.setTime(new Date())

c.add(Calendar.DATE, -1)

String Pastdate = sdf.format(c.getTime())

KeywordUtil.logInfo('Past Date and month : ' + Pastdate)

String[] sDate1 = Pastdate.split('-')

KeywordUtil.logInfo((((sDate1[0]) + ';') + (sDate1[1])) + ';')

DateValue = (sDate1[0])

if (DateValue.startsWith('0') == true) {
    GlobalVariable.sDate = DateValue.charAt(1)
} else {
    GlobalVariable.sDate = DateValue
}

Mobile.takeScreenshot()

calendar_Value = Mobile.getText(findTestObject('Object Repository/Mobile/Common/Calendar(day,date,month)'), 
    0, FailureHandling.STOP_ON_FAILURE)

int length = calendar_Value.length()

String month_short = calendar_Value.substring(length - 3)

KeywordUtil.logInfo(' Month name in short form: ' + month_short)

if (Mobile.verifyMatch(sDate1[1], month_short, false, FailureHandling.OPTIONAL)) {
    Mobile.takeScreenshot()

    Mobile.verifyElementNotExist(findTestObject('Mobile/SummaryScreen/date_Global'), 2)
} else {
    KeywordUtil.logInfo(' Past date not available because its from previous month date. ')
}

'Selected date should be properly loaded in the delivery field.'
SimpleDateFormat CD = new SimpleDateFormat('yyyy/MM/dd')

Calendar cale = Calendar.getInstance()

cale.setTime(new Date())

cale.add(Calendar.DATE, 0)

String Currentdate = CD.format(cale.getTime())

KeywordUtil.logInfo(' Current Date and Month : ' + Currentdate)

String[] sDate = Currentdate.split('/')

println(((((sDate[0]) + ';') + (sDate[1])) + ';') + (sDate[2]))

DateValue = (sDate[2])

if (DateValue.startsWith('0') == true) {
    GlobalVariable.sDate = DateValue.charAt(1)
} else {
    GlobalVariable.sDate = DateValue
}

Mobile.tap(findTestObject('Mobile/SummaryScreen/date_Global'), 2)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2)

Mobile.takeScreenshot()

Loaded_Date_Value = Mobile.getText(findTestObject('Mobile/SummaryScreen/Delivery_Date'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyMatch(Currentdate, Loaded_Date_Value, false, FailureHandling.STOP_ON_FAILURE)

'The screen should display the next popup alert when the user clicks the confirm button.'
Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 
    0)

id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

invoice_ID = Ord_ID.replaceAll('[\']', '')

KeywordUtil.logInfo(invoice_ID)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 4, TAX_Amount)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 5, Gross_Amt)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 6, invoice_ID)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.delay(3)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/OrderPopUp(EditOrder)'), [:], FailureHandling.OPTIONAL)


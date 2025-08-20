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
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

not_run: Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/PreSeller_Login'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Product/Re-usable/Mobile Login/Menu_Icon'), 50)

Mobile.tap(findTestObject('Product/Re-usable/Mobile Login/Menu_Icon'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 1300, 0, 530, FailureHandling.OPTIONAL)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/DashBoard_KPI_menu'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Line_Per_Call_title'), 0)

LPC_Target = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Line_Per_Call_Target_get'), 0)

LPC_Achieved = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Line_Per_Call_Achieved_get'), 0)

LPC_Balance = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Line_Per_Call_Balance_get'), 0)

Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Sales_value_title'), 0)

Target_bf = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/SalesValue_Target_get'), 0)

BfAchieved = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/SalesValue_Achieved_get'), 0)

Mobile.verifyEqual(Double.parseDouble(BfAchieved), GlobalVariable.B_SV_Acheivement, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

BF_Balance = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/DashBoard_KPI/Salesvalue_Balance_get'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

Mobile.scrollToText('Trade Coverage', FailureHandling.OPTIONAL)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Web Input Data/Seller KPI').getValue(
            'Retailer_Name', 1)], FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Preseller/New Store/Mike_allow_popup'), 0, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/New Store/Allow_Inventory_screen_btn'), 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
}

println(GlobalVariable.ProductName)

Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/Collection/Star Btn'), 5)

Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/Collection/All_btn_in_filter'), 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('Web Input Data/Seller KPI').getValue('Sku_Name', 1)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
    5)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Actual_PTD = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/StockOrder/Get_Values/PiecePrice_get'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/StockOrder/Get_Values/Casesize_get'), 0)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

GlobalVariable.keypadValue = findTestData('Mobile Input Data/Invoice').getValue('Keypad_Number', 5)

println(GlobalVariable.keypadValue)

Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Actual_piece_value = Mobile.getText(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/Collection/Next_btn_Inventory'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.waitForElementPresent(findTestObject('Mobile/Mobile Part/Preseller/Collection/Scheme_Next_btn'), 0)

Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/Collection/Scheme_Next_btn'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

TAX_Amount = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/StockOrder/Summary_Screen/IGST_Value'), 0)

Gross_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 
    0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

if (Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Preseller/Collection/Collection_Total_amt_copy_btn'), 
    0, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/Collection/Collection_Total_amt_copy_btn'), 0)

    Mobile.tap(findTestObject('Mobile/Mobile Part/Preseller/Collection/Done_Btn_Collection'), 0)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

id = Mobile.getText(findTestObject('Product/Mobile Part/Seller_1/StockOrder/Summary_Screen/Order_ID_get'), 0)

KeywordUtil.logInfo(id)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

KeywordUtil.logInfo(Ord_ID)

invoice_ID = Ord_ID

KeywordUtil.logInfo(invoice_ID)

exlpath = 'DDF\\Web Input Data\\Web Input Data.xlsx'

Sheetname = 'Sales_Order_Invoice_View'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 8, 4, TAX_Amount)

ExcelKeywords.setValueToCellByIndex(sheet1, 8, 5, Gross_Amt)

ExcelKeywords.setValueToCellByIndex(sheet1, 8, 6, invoice_ID)

ExcelKeywords.setValueToCellByIndex(sheet1, 8, 7, BfAchieved)

ExcelKeywords.setValueToCellByIndex(sheet1, 8, 8, BF_Balance)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.swipe(0, 0, 0, 400, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)


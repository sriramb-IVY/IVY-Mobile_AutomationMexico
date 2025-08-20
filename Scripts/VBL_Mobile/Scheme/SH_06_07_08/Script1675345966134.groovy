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
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.sun.net.httpserver.Authenticator.Failure as Failure
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

//Mobile.startApplication(GlobalVariable.APK_File, false)

Scheme_Index = 4

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('VBL_Mobile Input Data/OTPR').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

//slab_1
'Slab_1'

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Calculated_Sku_Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.keypadValue)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Calculated_Sku_Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_Sku_Total.toString() + ' : Slab_1 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)

Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :Slab_1 Scheme Description correctly displayed !')

Discount_value_Sheet = findTestData('VBL_Mobile Input Data/OTPR').getValue('DiscounttValueSlab1', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Discountvalue + ' :Slab_1 Discount percentage correctly applied !')

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeAmt + ' :Slab_1 scheme value in split screen correctly applied !')

CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')

'Slab_2'
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Calculated_Sku_Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.keypadValue)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Calculated_Sku_Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_Sku_Total.toString() + ' : Slab_2 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)

Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_2 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :Slab_2 scheme Description correctly displayed !')

Discount_value_Sheet = findTestData('VBL_Mobile Input Data/OTPR').getValue('DiscounttValueSlab2', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Discountvalue + ' :Slab_2 Discount percentage correctly applied !')

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeAmt + ' :Slab_2 scheme value in split screen correctly applied !')

CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_2 Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

'Higher_Slab'
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 5, FailureHandling.OPTIONAL)

Slab_2_Min_Qty = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

String High_Slab_Qty = Integer.parseInt(Slab_2_Min_Qty) * 2

length = High_Slab_Qty.size()

if (2 == length) {
    GlobalVariable.keypadValue = High_Slab_Qty.charAt(0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

    GlobalVariable.keypadValue = High_Slab_Qty.charAt(1)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
} else {
    GlobalVariable.keypadValue = High_Slab_Qty

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(High_Slab_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU_TOTAL + ' : High_Slab Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)

Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : High_Slab Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :High_Slab Scheme Description correctly displayed !')

Discount_value = ((Double.parseDouble(SchemeAmt) / Double.parseDouble(Slab_2_Min_Qty)) * Double.parseDouble(High_Slab_Qty))

calculated_Discount_value = new BigDecimal(Discount_value).setScale(2, RoundingMode.DOWN)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), calculated_Discount_value, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Discountvalue + ' :High Slab Discount percentage correctly applied !')

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

calculated_Discount_value = new BigDecimal(Discount_value).setScale(2, RoundingMode.HALF_DOWN)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), calculated_Discount_value, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(calculated_Discount_value.toString() + ' : High Slab Scheme amount displayed correctly in summary screen !')

CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

KeywordUtil.logInfo(TotalAmt)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

KeywordUtil.logInfo(Calculated_TotalAmt.toString())

CalculatedTotalAmt = new BigDecimal(Calculated_TotalAmt).setScale(2, RoundingMode.HALF_DOWN)

//Mobile.verifyMatch(TotalAmt, CalculatedTotalAmt.toString(), false, FailureHandling.OPTIONAL)
Mobile.verifyEqual(Double.parseDouble(TotalAmt), CalculatedTotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : High_Slab Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

exlpath = 'DDF\\VBL\\Web Input Data\\Web Input Data.xlsx'

Sheetname = 'Sales_Order_Invoice_View'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 0, GlobalVariable.ProductName)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 1, Actual_PiecePrice)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 2, High_Slab_Qty)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 3, SKU_TOTAL)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 4, CGST)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 5, TotalAmt)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 7, GlobalVariable.RetailerName)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 8, Actual_CaseSize)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 9, SchemeName)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 10, SchemeDesc)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 12, Discountvalue)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 13, SchemeAmt)

ExcelKeywords.setValueToCellByIndex(sheet1, 3, 6, GlobalVariable.VersionCodenvoiceNo)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/DB_Sales_Invoice_SchAmt'), [:], FailureHandling.STOP_ON_FAILURE)

'Stock reduce validation for scheme'

Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

Slab_2_Min_Qty = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

High_Slab_Qty

SIH_BUY_SKU01 = Integer.parseInt(High_Slab_Qty) * Integer.parseInt(Actual_CaseSize)

exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Scheme_Stock_Validation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 7, 5, SIH_BUY_SKU01)

//ExcelKeywords.setValueToCellByIndex(sheet1, 14, 5, SIH_GET_SKU01)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

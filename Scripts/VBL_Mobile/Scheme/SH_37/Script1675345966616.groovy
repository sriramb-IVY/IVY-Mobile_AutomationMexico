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
import com.sun.net.httpserver.Authenticator.Failure as Failure
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import org.junit.Assert as Assert
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
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

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('XXXXXXXXXXXX/Re-usable/Mobile Login/Menu'), 10)

String sheet_name = 'OTP_Automation_Scheme_CMB'

String file_name = 'Mobile Input data'

Scheme_Index = 2

RetailerApplyType = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('RetailerApplyType', Scheme_Index)

KeywordUtil.logInfo(RetailerApplyType)

if (RetailerApplyType == GlobalVariable.Fixed_HT) {
    KeywordUtil.logInfo('Expected "RetailerApplyType" was Equal, hence we can validate the scheme !')
} else {
    KeywordUtil.logInfo('Expected "RetailerApplyType" was Not Equal, hence we can not validate the scheme ')

    Mobile.takeScreenshot()

    Assert.fail()
}

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

'Slab 1 ----AND Logic'
GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('Slab_1_Min_Qty', Scheme_Index)

String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

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

Mobile.takeScreenshot()

SKU_1_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total1 = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_1_TOTAL), Total1, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('BuyProduct2', Scheme_Index)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Actual_CaseSize02 = Actual_CaseSize

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('Slab_1_Min_Qty', Scheme_Index)

String Slab_Qty2 = Integer.parseInt(Slab_1_Min_Qty)

length = Slab_Qty2.size()

if (2 == length) {
    GlobalVariable.keypadValue = Slab_Qty2.charAt(0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

    GlobalVariable.keypadValue = Slab_Qty2.charAt(1)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
} else {
    GlobalVariable.keypadValue = Slab_Qty2

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

SKU_2_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total2 = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_Qty2)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_2_TOTAL), Total2, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Sku Total Amount calculated and displayed correctly according the formula.')

'Get Free Product details'

'Free product 01'
GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct_Slab1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.Scheme_Free_SKU, 5)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Actual_PiecePrice_FP_1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize_FP_1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

'Free product 02'
GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct02_Slab1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.Scheme_Free_SKU, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

'Free product 01'
GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Actual_PiecePrice_FP_2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize_FP_2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'Scheme Should be Applied'

Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Additional Discount Field'), 5)

Discount_Amt = Discountvalue.replaceAll('Additional Discount : ', '')

Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

Web_Addition_dis_Amt = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('Additional_Dis_Amt', Scheme_Index)

'user need to add both product case qty because it is AND logic for buy product'

'formula : x= [ (inv qty * add. disc amt) / (sum of line value) ]* particular sku line value '
Two_SKU_Case_Qty = (Double.parseDouble(Slab_Qty) + Double.parseDouble(Slab_Qty2))

KeywordUtil.logInfo(Two_SKU_Case_Qty.toString() + ' : Sum of two product invoice Quantity')

SumOfTheLineValue = (Double.parseDouble(SKU_1_TOTAL) + Double.parseDouble(SKU_2_TOTAL))

KeywordUtil.logInfo(SumOfTheLineValue.toString() + ' : Sum of two sku total line value')

SKU_1_DiscountAmt = (((Two_SKU_Case_Qty * Double.parseDouble(Web_Addition_dis_Amt)) / SumOfTheLineValue) * Double.parseDouble(SKU_1_TOTAL))

KeywordUtil.logInfo(SKU_1_DiscountAmt.toString() + ' : Sku 1 discount amount')

SKU_2_DiscountAmt = (((Two_SKU_Case_Qty * Double.parseDouble(Web_Addition_dis_Amt)) / SumOfTheLineValue) * Double.parseDouble(SKU_2_TOTAL))

KeywordUtil.logInfo(SKU_2_DiscountAmt.toString() + ' : Sku 2 discount amount')

Total_Scheme_Discount_amt = (SKU_1_DiscountAmt + SKU_2_DiscountAmt)

Total_Scheme_Discount_amt = Math.ceil(Total_Scheme_Discount_amt)

KeywordUtil.logInfo(Total_Scheme_Discount_amt.toString() + ' : Sum of the sku discount amt (total discount amt)')

Mobile.takeScreenshot()

Mobile.verifyEqual(Double.parseDouble(Discount_Amt), Total_Scheme_Discount_amt, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(Discount_Amt + ' : Additional discount Amount Displayed correctly in Screen Screen !')

Mobile.takeScreenshot()

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/SH_37(CallTestCase_ApplySchemeScreen)'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Done_Btn'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

//1_free Sku
'Summary Screen ---And_Logic Free Product'
Mobile.takeScreenshot()

GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct_Slab1', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

//2_freeSku
GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct02_Slab1', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct02_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

'Amount Split Up Screen'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

String FreeSKU01_Qty = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct_Case_Slab1', Scheme_Index)

Slab1_FreeSku_1_Amt = ((Double.parseDouble(Actual_CaseSize_FP_1) * Double.parseDouble(FreeSKU01_Qty)) * Double.parseDouble(Actual_PiecePrice_FP_1))

KeywordUtil.logInfo(Slab1_FreeSku_1_Amt.toString())

String FreeSKU02_Qty = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct02_Case_Slab1', Scheme_Index)

Slab1_FreeSku_2_Amt = ((Double.parseDouble(Actual_CaseSize_FP_2) * Double.parseDouble(FreeSKU02_Qty)) * Double.parseDouble(Actual_PiecePrice_FP_2))

KeywordUtil.logInfo(Slab1_FreeSku_2_Amt.toString())

Mobile.takeScreenshot()

OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

Calculated_Order_Amt = (((Double.parseDouble(SKU_1_TOTAL) + Double.parseDouble(SKU_2_TOTAL)) + Slab1_FreeSku_1_Amt) + Slab1_FreeSku_2_Amt)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Calculated_Order_Amt, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(OrderAmt + ' : Order line amount displayed correctly in summary screen !')

Mobile.takeScreenshot()

Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

Calculated_Scheme_Amt = ((Slab1_FreeSku_1_Amt + Slab1_FreeSku_2_Amt) + Total_Scheme_Discount_amt)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Scheme_Amt, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(SchemeAmt + ' : Scheme amount displayed correctly !')

Mobile.takeScreenshot()

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0, FailureHandling.OPTIONAL)

CESS = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CESS Value'), 0, FailureHandling.OPTIONAL)

IGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-IGST Value'), 0, FailureHandling.OPTIONAL)

SGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-SGSTValue'), 0, FailureHandling.OPTIONAL)

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

'This is credit retailer + apportionate so buy product total + Tax equal to total'
tax = (((Double.parseDouble(CGST) + Double.parseDouble(CESS)) + Double.parseDouble(IGST)) + Double.parseDouble(SGST))

orderAmount = (Total1 + Total2)

Calculated_TotalAmt = ((Calculated_Order_Amt + tax) - Calculated_Scheme_Amt)

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

//'Stock reduce validation for scheme'
//Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('Slab_1_Min_Qty', Scheme_Index)
//
//SIH_BUY_SKU01 = (Integer.parseInt(Slab_1_Min_Qty) * Integer.parseInt(Actual_CaseSize))
//
//SIH_BUY_SKU02 = (Integer.parseInt(Slab_1_Min_Qty) * Integer.parseInt(Actual_CaseSize02))
//
//FreeProduct_Case_Slab1 = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct_Case_Slab1', Scheme_Index)
//
//SIH_GET_SKU01 = (Integer.parseInt(FreeProduct_Case_Slab1) * Integer.parseInt(Actual_CaseSize_FP_1))
//
//FreeProduct02_Case_Slab1 = findTestData('VBL_Mobile Input Data/OTP_Automation_Scheme_CMB').getValue('FreeProduct02_Case_Slab1', Scheme_Index)
//
//SIH_GET_SKU02 = (Integer.parseInt(FreeProduct02_Case_Slab1) * Integer.parseInt(Actual_CaseSize_FP_2))
//
//exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'
//
//Sheetname = 'Scheme_Stock_Validation'
//
//workbook01 = ExcelKeywords.getWorkbook(exlpath)
//
//sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)
//
//ExcelKeywords.setValueToCellByIndex(sheet1, 15, 5, SIH_BUY_SKU01)
//
//ExcelKeywords.setValueToCellByIndex(sheet1, 16, 5, SIH_BUY_SKU02)
//
//Get_Qty_01 = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Invoice_Generated_Qty', 8)
//
//SIH_GET_SKU01 = SIH_GET_SKU01 + Integer.parseInt(Get_Qty_01)
//
//Get_Qty_02 = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Invoice_Generated_Qty', 9)
//
//SIH_GET_SKU01 = SIH_GET_SKU01 + Integer.parseInt(Get_Qty_02)
//
//ExcelKeywords.setValueToCellByIndex(sheet1, 8, 5, SIH_GET_SKU01)
//
//ExcelKeywords.setValueToCellByIndex(sheet1, 9, 5, SIH_GET_SKU02)
//
//ExcelKeywords.saveWorkbook(exlpath, workbook01)


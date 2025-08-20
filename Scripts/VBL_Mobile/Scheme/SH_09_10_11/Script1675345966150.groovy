import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.sun.net.httpserver.Authenticator.Failure as Failure
import org.openqa.selenium.Keys as Keys
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver as WebDriver
import org.openqa.selenium.chrome.ChromeDriver as ChromeDriver
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import org.junit.Assert as Assert

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/StockUpdation/CT/StartApplication_StockUpdate(Web,Specific_SKU)'), [('Starting_Row_No') : 0, ('Ending_Row_No') : 10], FailureHandling.STOP_ON_FAILURE)

//Mobile.startApplication(GlobalVariable.APK_File, false)

Scheme_Index = 5

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

Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

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

SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_1_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU_TOTAL + ' : Slab_1 Sku Total Amount calculated and displayed correctly according the formula.')

///
'Get Free Product details'

'Free product 01'
GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Slab1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.Scheme_Free_SKU, 5)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

//Actual_PiecePrice_FP_1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)
Actual_CaseSize_FP_1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

///
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

//Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)
Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :Slab 1 Scheme Description correctly displayed !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Slab1', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab1, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab1 + ' :Slab 1 Free product correctly displayed !')

Mobile.verifyMatch(FreeSKU_Qty_Slab1, findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab 1 Free Product Case Qty correctly displayed !')

Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1_MAX', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' :Slab 1 Free Product Max Case Qty correctly displayed !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Done_Btn'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Slab1', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :  Slab_1 Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

//2_Slab
'2_Slab'
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 5)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 1, FailureHandling.OPTIONAL)

Slab_2_Min_Qty = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

String Slab2_Qty = Integer.parseInt(Slab_2_Min_Qty)

length = Slab2_Qty.size()

if (2 == length) {
    GlobalVariable.keypadValue = Slab2_Qty.charAt(0)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

    GlobalVariable.keypadValue = Slab2_Qty.charAt(1)

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
} else {
    GlobalVariable.keypadValue = Slab2_Qty

    Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab2_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU_TOTAL + ' : Slab_2 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

//Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)
Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' :  Slab_2 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab_2 Scheme Description correctly displayed !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab2, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab2 + ' :Slab 2 Free Product  correctly displayed !')

Mobile.verifyMatch(FreeSKU_Qty_Slab2, findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_Qty_Slab2 + ' :Slab 2 Free Product Case Qty correctly displayed !')

Expecetd_FreeSKU_MinQty_Slab2 = ('Min:' + findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MinQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab2 + ' :Slab 2 Free Product Min Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab2 = ('Max:' + findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2_MAX', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MaxQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab2 + ' :Slab 2 Free Product Max Qty correctly displayed !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Done_Btn'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku_CaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab2_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 2 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab2_FreeSku_CaseQty, findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab2_FreeSku_CaseQty + ' :Slab 2 Free Product Caase Qty correctly displayed in Summary Screen !')

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

//High_Slab
'High_Slab'
Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 5)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 5)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 5, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Back Arrow-Keypad'), 1, FailureHandling.OPTIONAL)

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

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(High_Slab_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU_TOTAL + ' : High_Slab Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

//Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)
Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : High_Slab Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : High_Slab Scheme Description correctly displayed !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab2 = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab2, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab2 + ' :High_Slab Free Product  correctly displayed !')

scheme_slab2_free_quantity = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index)

Calculated_high_Slab_free_Qty = (Integer.parseInt(High_Slab_Qty) / (Integer.parseInt(Slab_2_Min_Qty) / Integer.parseInt(scheme_slab2_free_quantity)))

KeywordUtil.logInfo(Calculated_high_Slab_free_Qty.toString())

Mobile.verifyMatch(FreeSKU_Qty_Slab2, Calculated_high_Slab_free_Qty.toString(), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_Qty_Slab2 + ' :High_Slab Free Product Case Qty correctly displayed !')

Expecetd_FreeSKU_MinQty_Slab2 = ('Min:' + Calculated_high_Slab_free_Qty)

Mobile.verifyMatch(FreeSKU_MinQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab2 + ' :High_Slab Free Product Min Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab2 = ('Max:' + Calculated_high_Slab_free_Qty)

Mobile.verifyMatch(FreeSKU_MaxQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab2 + ' :High_Slab Free Product Max Qty correctly displayed !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Done_Btn'), 0)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.Scheme_Free_SKU = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku_CaseQty = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedCaseQty(Global)'), 5)

Slab2_FreeSku_tax_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/Summary_ProductBasedTotalAmt(Global)'), 5)

Mobile.verifyMatch(Slab2_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :High_Slab Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab2_FreeSku_CaseQty, Calculated_high_Slab_free_Qty.toString(), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab2_FreeSku_CaseQty + ' :Slab 2 Free Product Caase Qty correctly displayed in Summary Screen !')

Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

exlpath = 'DDF\\VBL\\Web Input Data\\Web Input Data.xlsx'

Sheetname = 'Sales_Order_Invoice_View'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 0, GlobalVariable.ProductName)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 1, Actual_PiecePrice)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 2, High_Slab_Qty)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 3, SKU_TOTAL)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 4, CGST)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 5, TotalAmt)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 7, GlobalVariable.RetailerName)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 8, Actual_CaseSize)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 9, SchemeName)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 10, SchemeDesc)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 13, SchemeAmt)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 14, GlobalVariable.Scheme_Free_SKU)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 15, Slab2_FreeSku_CaseQty)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 16, Slab2_FreeSku_tax_Amt)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 6, GlobalVariable.VersionCodenvoiceNo)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/DB_Sales_Invoice_FreeProduct'), [:], FailureHandling.STOP_ON_FAILURE)

//SKU_Conversion_Qty = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Conversion_Qty', 11)

'Stock reduce validation for scheme'

Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

Slab_2_Min_Qty = findTestData('VBL_Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

//High_Slab_Qty

SIH_BUY_SKU01 = (Integer.parseInt(High_Slab_Qty) * Integer.parseInt(Actual_CaseSize) )

SKU_Conversion_Qty = findTestData('VBL_Mobile Input Data/Scheme_Stock_Validation').getValue('SKU_Conversion_Qty', 12)

FreeProduct_Case_Slab1 = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1', Scheme_Index)

FreeProduct_Case_Slab2 = findTestData('VBL_Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index)

//Calculated_high_Slab_free_Qty

SIH_GET_SKU01 = ( Calculated_high_Slab_free_Qty * Integer.parseInt(Actual_CaseSize_FP_1) )

exlpath = 'DDF\\VBL\\Mobile Input data\\Mobile Input data.xlsx'

Sheetname = 'Scheme_Stock_Validation'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 5, 5, SIH_BUY_SKU01)

ExcelKeywords.setValueToCellByIndex(sheet1, 6, 5, SIH_GET_SKU01)

ExcelKeywords.saveWorkbook(exlpath, workbook01)


import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

// Mobile.startApplication(GlobalVariable.APK_File, false)
if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Main menu visible'
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Scheme_Index = 5

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/OTPR').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)



//slab_1
'Slab_1'
GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Slab_1_Min_Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty = Slab_1_Min_Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_1_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU_TOTAL + ' : Slab_1 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

//Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)
Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :Slab 1 Scheme Description correctly displayed !')

Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Slab1', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab1, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab1 + ' :Slab 1 Free product correctly displayed !')

Mobile.verifyMatch(FreeSKU_Qty_Slab1, findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab 1 Free Product Case Qty correctly displayed !')

Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1_MAX', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' :Slab 1 Free Product Max Case Qty correctly displayed !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Slab1', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :  Slab_1 Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

//2_Slab
'2_Slab'
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)



GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 5)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 5)

Slab_2_Min_Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty = Slab_2_Min_Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_2_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU_TOTAL + ' : Slab_2 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

//Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)
Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' :  Slab_2 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab_2 Scheme Description correctly displayed !')

Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab2, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab2 + ' :Slab 2 Free Product  correctly displayed !')

Mobile.verifyMatch(FreeSKU_Qty_Slab2, findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_Qty_Slab2 + ' :Slab 2 Free Product Case Qty correctly displayed !')

Expecetd_FreeSKU_MinQty_Slab2 = ('Min:' + findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MinQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab2 + ' :Slab 2 Free Product Min Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab2 = ('Max:' + findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2_MAX', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MaxQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab2 + ' :Slab 2 Free Product Max Qty correctly displayed !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku_CaseQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab2_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 2 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab2_FreeSku_CaseQty, findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab2_FreeSku_CaseQty + ' :Slab 2 Free Product Caase Qty correctly displayed in Summary Screen !')

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

//High_Slab
'High_Slab'
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 5)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 5)

Slab_2_Min_Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

String High_Slab_Qty = Integer.parseInt(Slab_2_Min_Qty) * 2

println(GlobalVariable.Qty = High_Slab_Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(High_Slab_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SKU_TOTAL + ' : High_Slab Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

//Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)
Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : High_Slab Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : High_Slab Scheme Description correctly displayed !')

Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab2, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab2 + ' :High_Slab Free Product  correctly displayed !')

scheme_slab2_free_quantity = findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Case_Slab2', Scheme_Index)

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

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/OTPR').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku_CaseQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'), 5)

Slab2_FreeSku_tax_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedTotalAmt(Global)'), 5)

Mobile.verifyMatch(Slab2_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :High_Slab Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab2_FreeSku_CaseQty, Calculated_high_Slab_free_Qty.toString(), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab2_FreeSku_CaseQty + ' :Slab 2 Free Product Caase Qty correctly displayed in Summary Screen !')

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

exlpath = 'DDF\\Web Input Data\\Web Input Data.xlsx'

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

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DatePicker_and _Navigation/Mobile Date Picker - Current Date'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

invoice_ID = Ord_ID.replaceAll('[\']', '')

KeywordUtil.logInfo(invoice_ID)

exlpath = 'DDF\\Web Input Data\\Web Input Data.xlsx'

Sheetname = 'Sales_Order_Invoice_View'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 4, 6, invoice_ID)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

Mobile.delay(2)

Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 2, FailureHandling.OPTIONAL)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Scheme/Reusable Cases/DB_Sales_Invoice_FreeProduct'), [:], FailureHandling.STOP_ON_FAILURE)


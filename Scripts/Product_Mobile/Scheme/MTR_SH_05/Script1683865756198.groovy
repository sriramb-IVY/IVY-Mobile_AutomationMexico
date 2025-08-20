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

not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Main menu visible'
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Scheme_Index = 1

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)


//2_Slab
'2_Slab'
GlobalVariable.ProductName = findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 5)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 5)

Slab_2_Max_Qty = findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('Slab_2_Max_Qty', Scheme_Index)

println(GlobalVariable.Qty = Slab_2_Max_Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_2_Max_Qty)) * Double.parseDouble(Actual_PiecePrice))

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
Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' :  Slab_2 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab_2 Scheme Description correctly displayed !')

Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab2, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab2 + ' :Slab 2 Free Product  correctly displayed !')

Mobile.verifyMatch(FreeSKU_Qty_Slab2, findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('FreeProduct_Case_Slab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_Qty_Slab2 + ' :Slab 2 Free Product Case Qty correctly displayed !')

Expecetd_FreeSKU_MinQty_Slab2 = ('Min:' + findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('FreeProduct_Case_Slab2', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MinQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab2 + ' :Slab 2 Free Product Min Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab2 = ('Max:' + findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('FreeProduct_Case_Slab2_MAX', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MaxQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab2 + ' :Slab 2 Free Product Max Qty correctly displayed !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('FreeProduct_Slab2', Scheme_Index)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab2_FreeSku_CaseQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab2_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab2_FreeSku + ' :Slab 2 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab2_FreeSku_CaseQty, findTestData('Mobile Input Data/MTR_MTS_Scheme').getValue('FreeProduct_Case_Slab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

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

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)


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

//Mobile.startApplication(GlobalVariable.APK_File, false)
if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Main menu visible'
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Scheme_Index = 4

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)



//slab_1
'Slab_1'

/// !_Sku
'ONLY Logic'
GlobalVariable.ProductName = findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

Slab_1_Min_Qty = findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

println(GlobalVariable.Qty = Slab_Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 5)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 5)

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :Slab 1 Scheme Description correctly displayed !')

Mobile.callTestCase(findTestCase('VBL_Mobile/Scheme/Reusable Cases/ANY_Condition_Free_Sku_Validation'), [('Scheme_Free_SKU') : findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('FreeProduct_Slab1', Scheme_Index), ('Scheme_Free_SKU02') : findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue(
            'FreeProduct02_Slab1', Scheme_Index), ('FreeSKU_MinQty') : findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('FreeProduct_Case_Slab1', Scheme_Index), ('FreeSKU_MaxQty') : findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('FreeProduct_Case_Slab1_MAX', 
            Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

'Getting free product details'
Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_OK'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.Scheme_Free_SKU, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'), 5)

Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('Mobile Input Data/Automatio_Apportionate_Scheme').getValue('FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

FreeSKU_Qty_Slab1 = GlobalVariable.FreeProductQty

Slab1_FreeSku_Amt = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(FreeSKU_Qty_Slab1)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Slab1_FreeSku_Amt, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(SchemeAmt + ' : Scheme amount displayed correctly in summary screen !')

Mobile.takeScreenshot()

Calculated_OrderAmt = (Total + Slab1_FreeSku_Amt)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Calculated_OrderAmt, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo(OrderAmt + ' : Apportionate Scheme Order value displayed correctly in summary screen !')

Mobile.takeScreenshot()

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

not_run: Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

not_run: invoice_ID = Ord_ID.replaceAll('[\']', '')

not_run: KeywordUtil.logInfo(invoice_ID)

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.swipe(0, 0, 0, 400, FailureHandling.OPTIONAL)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)


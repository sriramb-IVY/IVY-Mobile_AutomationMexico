import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

not_run: Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Main menu visible'
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Scheme_Index = 1

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/OTPR').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)



//slab_1
'Slab 1'
GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.keypadValue)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Total.toString() + ': Slab_1 Sku Total calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

DiscountPerc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc'), 0)

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

Discount_Perc_Sheet = findTestData('Mobile Input Data/OTPR').getValue('DiscountPercSlab1', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(DiscountPerc), Double.parseDouble(Discount_Perc_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(DiscountPerc + ' : Slab_1 Discount percentage correctly applied !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Sch_Amt = ((Total * Double.parseDouble(Discount_Perc_Sheet)) / 100)

KeywordUtil.logInfo(Calculated_Sch_Amt.toString())

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Sch_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' : Slab_1 Calculated Scheme Amount correctly displayed !')

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Calculated_Sch_Amt)

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)


//2slab
'Slab 2'
GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5, FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.keypadValue)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Total.toString() + ' : Slab_2 Sku Total calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

DiscountPerc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc'), 0)

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_2 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab_2 Scheme Description correctly displayed !')

Discount_Perc_Sheet = findTestData('Mobile Input Data/OTPR').getValue('DiscountPercSlab2', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(DiscountPerc), Double.parseDouble(Discount_Perc_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(DiscountPerc + ' : Slab_2 Discount percentage correctly applied !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Sch_Amt = ((Total * Double.parseDouble(Discount_Perc_Sheet)) / 100)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Sch_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' : Slab_2 Calculated Scheme Amount correctly displayed !')

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Calculated_Sch_Amt)

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_2 Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

not_run: Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

not_run: invoice_ID = Ord_ID.replaceAll('[\']', '')

not_run: KeywordUtil.logInfo(invoice_ID)

not_run: GlobalVariable.InvoiceNo = invoice_ID

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

not_run: Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

not_run: Mobile.delay(2)

not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

not_run: Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/DB/Order_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/DB/Invoice_Delete_DB'), [:], FailureHandling.STOP_ON_FAILURE)


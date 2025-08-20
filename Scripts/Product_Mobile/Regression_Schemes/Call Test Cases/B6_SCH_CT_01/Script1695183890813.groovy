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

not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

'Slab1 : Positive flow Scheme Validation'

'ANY Condition'

'Buy_Product01'
Scheme_Index = 1

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Buy_Product01', Scheme_Index)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

KeywordUtil.logInfo('Price of the product' + Actual_PiecePrice)

Slab = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Buy_Product_Piece_Qty', Scheme_Index)

println(GlobalVariable.Qty = Slab)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

'Verify the Total amount of get_product01 displayed in the screen'
Mobile.takeScreenshot()

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo('Buy_Product01 Total Value ' + SKU_TOTAL)

Buy_Product01_Total = (Double.parseDouble(GlobalVariable.Qty) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Buy_Product01_Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Buy_Product01_Total.toString() + ' : Buy_Product01 Total Amount calculated and displayed correctly according the formula.')

'Buy_Product02'
GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Buy_Product02', Scheme_Index)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

KeywordUtil.logInfo('Price of the product' + Actual_PiecePrice)

Slab = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Buy_Product_Piece_Qty', Scheme_Index)

println(GlobalVariable.Qty = Slab)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

'Verify the Total amount of get_product02 displayed in the screen'
Mobile.takeScreenshot()

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

KeywordUtil.logInfo('Buy_Product01 Total Value ' + SKU_TOTAL)

Buy_Product02_Total = (Double.parseDouble(GlobalVariable.Qty) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Buy_Product02_Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Buy_Product02_Total.toString() + ' : Buy_Product02 Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

'Scheme should be apply & scheme name should be display since it is positive flow'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

'Scheme Name Verification'
Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' :Scheme Name correctly displayed !')

'Scheme Slab Verification'
Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('S1_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :Scheme Description correctly displayed !')

'View screen scheme validations for Free Product1'
Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

Mobile.delay(2)

GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product01', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_1_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

'Free product_1 name verification in view screen'
Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product1 name correctly displayed !')

'Free product1 Min and Max quantity verification in view screen'
Expecetd_FreeSKU_MinQty_Slab = ('Min:' + findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product1 Min Piece Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab = ('Max:' + findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product1 Max Piece Qty correctly displayed !')

'View screen scheme validations for Free Product02'
Mobile.takeScreenshot()

GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product02', Scheme_Index)

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_2_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

'Free product_1 name verification in view screen'
Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product2 name correctly displayed !')

'Free product2 Min and Max quantity verification in view screen'
Expecetd_FreeSKU_MinQty_Slab = ('Min:' + findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product2 Min Piece Qty correctly displayed !')

Expecetd_FreeSKU_MaxQty_Slab = ('Max:' + findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index))

Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product2 Max Piece Qty correctly displayed !')

'If Get product is in ANY Condition means User should verify which free product get Quantity'
if (FreeSKU_1_Qty_Slab != 0) {
    'Get_Product01 have piece quantity'
    Mobile.takeScreenshot()

    GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product01', Scheme_Index)

    //FP_Get01 = Scheme_Free_SKU
    FreeSKU_1_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

    Mobile.verifyMatch(FreeSKU_1_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_1_Qty_Slab + ' : Free Product1 Piece Qty correctly displayed !')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.Scheme_Free_SKU, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.Scheme_GetProduct01_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
    'Get_Product02 have piece quantity'
    Mobile.takeScreenshot()

    GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product02', Scheme_Index)

    FreeSKU_2_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

    Mobile.verifyMatch(FreeSKU_2_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_2_Qty_Slab + ' : Free Product2 Piece Qty correctly displayed !')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.Scheme_Free_SKU, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.Scheme_GetProduct01_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

'Summary screen scheme validations'
Mobile.takeScreenshot()

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Slab_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

'Free product name verification in Summary screen'
Mobile.verifyMatch(Slab_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab_FreeSku + ' : Free Product name correctly displayed in Summary Screen !')

'Free product quantity verification in Summary screen'
GlobalVariable.Qty = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index)

Mobile.verifyMatch(Slab_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Slab_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

'Individual Buy Product Scheme amount validation'

'Buy Product_1'
GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Buy_Product01', Scheme_Index)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

'Validation for Order Value'
Mobile.takeScreenshot()

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Buy_Product01_Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Validation for Scheme Amount'
Slab_Qty = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index)

Scheme_Applied_Get_Product_Total_Amount = (Double.parseDouble(GlobalVariable.Scheme_GetProduct01_PiecePrice) * Double.parseDouble(Slab_Qty))

KeywordUtil.logInfo(Scheme_Applied_Get_Product_Total_Amount.toString() + ' : Scheme_Applied_Get_Product_Total_Amount')

Buy_Products_OrderValue = (Buy_Product01_Total + Buy_Product02_Total)

Buy_Product01_Splited_Scheme_Amt = ((Scheme_Applied_Get_Product_Total_Amount * Buy_Product01_Total) / Buy_Products_OrderValue)

KeywordUtil.logInfo(Buy_Product01_Splited_Scheme_Amt.toString() + ' : Buy_Product01_Splited_Scheme_Amt')

Scheme_Amt_for_Buy_Product01 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

Scheme_Amt_for_Buy_Product01 = Scheme_Amt_for_Buy_Product01.replaceAll('- ', '')

KeywordUtil.logInfo(Scheme_Amt_for_Buy_Product01)

Mobile.verifyEqual(Double.parseDouble(Scheme_Amt_for_Buy_Product01), Buy_Product01_Splited_Scheme_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Validation for Total'
Buy_Product01_TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Buy_Product01_TotalAmt = (Buy_Product01_Total - Buy_Product01_Splited_Scheme_Amt)

Mobile.verifyEqual(Double.parseDouble(Buy_Product01_TotalAmt), Calculated_Buy_Product01_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)

'Buy Product_2'
GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Buy_Product02', Scheme_Index)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

'Validation for Order Value'
Mobile.takeScreenshot()

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Buy_Product01_Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Validation for Scheme Amount'
Slab_Qty = findTestData('Batch_Scheme_Inputs_01/5_B6(Appor)_MS_FP(QTY)_MTS').getValue('Get_Product_S1_Piece_Qty', Scheme_Index)

Scheme_Applied_Get_Product_Total_Amount = (Double.parseDouble(GlobalVariable.Scheme_GetProduct01_PiecePrice) * Double.parseDouble(Slab_Qty))

KeywordUtil.logInfo(Scheme_Applied_Get_Product_Total_Amount.toString() + ' : Scheme_Applied_Get_Product_Total_Amount')

Buy_Products_OrderValue = (Buy_Product01_Total + Buy_Product02_Total)

Buy_Product02_Splited_Scheme_Amt = ((Scheme_Applied_Get_Product_Total_Amount * Buy_Product02_Total) / Buy_Products_OrderValue)

KeywordUtil.logInfo(Buy_Product02_Splited_Scheme_Amt.toString() + ' : Buy_Product02_Splited_Scheme_Amt')

Scheme_Amt_for_Buy_Product02 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

Scheme_Amt_for_Buy_Product02 = Scheme_Amt_for_Buy_Product02.replaceAll('- ', '')

KeywordUtil.logInfo(Scheme_Amt_for_Buy_Product02)

Mobile.verifyEqual(Double.parseDouble(Scheme_Amt_for_Buy_Product02), Buy_Product02_Splited_Scheme_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Validation for Total'
Buy_Product02_TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Buy_Product02_TotalAmt = (Buy_Product02_Total - Buy_Product02_Splited_Scheme_Amt)

Mobile.verifyEqual(Double.parseDouble(Buy_Product02_TotalAmt), Calculated_Buy_Product02_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)

'Validation in info screen'
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

Mobile.takeScreenshot()

'Validate Order Value'
OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

'This is Apportionate Scheme so Buy products total added with get products total is equal to Order value '
Buy_Products_OrderValue = ((Buy_Product01_Total + Buy_Product02_Total) + Scheme_Applied_Get_Product_Total_Amount)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Buy_Products_OrderValue, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Validate Scheme Amount'
Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Scheme_Applied_Get_Product_Total_Amount, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Validate Total Amount'
TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Total_Amt = (Buy_Products_OrderValue - Scheme_Applied_Get_Product_Total_Amount)

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_Total_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 3)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

invoice_ID = Ord_ID.replaceAll('[\']', '')

KeywordUtil.logInfo(invoice_ID)

Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.swipe(50, 50, 50, 600, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)


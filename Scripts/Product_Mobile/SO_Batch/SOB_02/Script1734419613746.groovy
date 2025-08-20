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
import java.time.LocalDate as LocalDate
import org.testng.Assert as Assert
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

Mobile.startApplication(GlobalVariable.APKFile, false)

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 20)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Menu Icon visible'
} else {
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/SO_Batch').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.comment('Slab1')

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

'Dicountpercentage scheme products'
Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)
'Slab_1'

//GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 1)
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)
'AND Logic'
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

Slab_1_Min_Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 1)

String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

GlobalVariable.Qty = Slab_Qty

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2)

//2_SKU
'2_SKU'
Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

//Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)
//
//GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 2)
//
//Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)
Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 2)], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 2)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

'Discount Value SKUS'

'SKU1'
Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 3)], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 3)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

'SKU2'
Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 4)], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 4)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

'free product quantity'
Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 5)], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 5)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

'Discountand tax'
Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') : findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 10)], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

println(Actual_PiecePrice)

GlobalVariable.RP_Price = Actual_PiecePrice

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 10)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

'batch screen'
Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert_without_batch'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(3)

Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Batch_Allocation_Screen_title'), 0, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

String sheet_name = 'SO_Batch'

String file_name = 'Mobile Input data'

ArrayList<String> SKU_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SKU_Name')

for (int i = 0; i < 5; i++) {
    Scheme_Index = (i + 1)

    KeywordUtil.logInfo('index' + Scheme_Index)

    GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', Scheme_Index)

    Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name_batch_screen'), 0, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name_batch_screen'), 0)

    Mobile.waitForElementPresent(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Name'), 5)

    GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('Labels', 1)

    Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 5)

    Batch_name_get = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 0)

    GlobalVariable.label1 = Batch_name_get

    Mobile.callTestCase(findTestCase('Product_Mobile/SO_Batch/SOB_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo('first batch : ' + GlobalVariable.batch1)

    GlobalVariable.batch1 = GlobalVariable.batch

    Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Case_field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

    Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

    GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Buy_Quantity1', Scheme_Index)

    String Qty = Integer.parseInt(GlobalVariable.Qty)

    length = Qty.size()

    if (1 == length) {
        GlobalVariable.keypadValue = Qty

        Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
    } else if (2 == length) {
        GlobalVariable.keypadValue = Qty.charAt(0)

        Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

        GlobalVariable.keypadValue = Qty.charAt(1)

        Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
    }
    
    GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('Labels', 2)

    Mobile.verifyElementExist(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 5)

    Batch2_name_get = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Product_Batch_Name'), 5)

    GlobalVariable.label1 = Batch2_name_get

    Mobile.callTestCase(findTestCase('Product_Mobile/SO_Batch/SOB_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo('Second batch : ' + GlobalVariable.batch2)

    GlobalVariable.batch2 = GlobalVariable.batch

    Mobile.tap(findTestObject('Mobile/Seller_2/Stock_order_Batch/Piece_field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Vertical keypad-BackSpace'), 0)

    GlobalVariable.Qty = findTestData('Mobile Input Data/SO_Batch').getValue('Buy_Quantity2', Scheme_Index)

    String Qty1 = Integer.parseInt(GlobalVariable.Qty)

    length1 = Qty1.size()

    if (1 == length1) {
        GlobalVariable.keypadValue = Qty1

        Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
    } else if (2 == length1) {
        GlobalVariable.keypadValue = Qty1.charAt(0)

        Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)

        GlobalVariable.keypadValue = Qty1.charAt(1)

        Mobile.tap(findTestObject('Object Repository/Mobile/Common/Global_Number_keypad'), 5)
    }
    
    Mobile.tap(findTestObject('Object Repository/Mobile/Common/Btn_Done'), 0)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('Scheme_Name', 1)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 15)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 0, FailureHandling.STOP_ON_FAILURE)

Dis_Per_SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 0)

KeywordUtil.logInfo('Scheme name : ' + Dis_Per_SchemeName)

Mobile.verifyMatch(Dis_Per_SchemeName, findTestData('Mobile Input Data/SO_Batch').getValue('Scheme_Name', 1), false, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Discount Percentage scheme Applied correctly')

Dis_Per_SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeDesc'), 0)

KeywordUtil.logInfo('Secheme Desc : ' + Dis_Per_SchemeDesc)

DiscountPerc = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Discount_Perc_Value'), 0, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Secheme value : ' + DiscountPerc)

Dis_Per = findTestData('Mobile Input Data/SO_Batch').getValue('S1_Discount_Percentage', 1)

Mobile.verifyEqual(Double.parseDouble(DiscountPerc), Double.parseDouble(Dis_Per), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Discount Value scheme'
GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('Scheme_Name', 3)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 15)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 0, FailureHandling.STOP_ON_FAILURE)

Dis_value_SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 0)

KeywordUtil.logInfo('Scheme name : ' + Dis_value_SchemeName)

Mobile.verifyMatch(Dis_value_SchemeName, findTestData('Mobile Input Data/SO_Batch').getValue('Scheme_Name', 3), false, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Discount Value scheme Applied correctly')

Dis_value_SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeDesc'), 0)

KeywordUtil.logInfo('Secheme Desc : ' + Dis_value_SchemeDesc)

Discountvalue = Mobile.getText(findTestObject('Mobile/Seller_2/Stock_order_Batch/Discount_Value_amount'), 0, FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Secheme value : ' + Discountvalue)

Dis_val = findTestData('Mobile Input Data/SO_Batch').getValue('S1_Discount_Value', 3)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Dis_val), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

'Free product scheme'
GlobalVariable.label = findTestData('Mobile Input Data/SO_Batch').getValue('Scheme_Name', 5)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 15)

Mobile.verifyElementExist(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 0, FailureHandling.STOP_ON_FAILURE)

FP_SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeName'), 0)

KeywordUtil.logInfo('Scheme name : ' + FP_SchemeName)

FP_SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Global_SchemeDesc'), 0)

KeywordUtil.logInfo('Secheme Desc : ' + FP_SchemeDesc)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/SO_Batch').getValue('Free_SKU1', 5)

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

Free_Qty = findTestData('Mobile Input Data/SO_Batch').getValue('S1_Free_Qty', 5)

if (Mobile.verifyNotMatch(FreeSKU_Qty_Slab1, Free_Qty, false, FailureHandling.OPTIONAL)) {
    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/SO_Batch').getValue('Free_SKU2', 5)
} else {
    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/SO_Batch').getValue('Free_SKU1', 5)
}

KeywordUtil.logInfo(GlobalVariable.Scheme_Free_SKU + ' : Free product!')

Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeProduct_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

Mobile.verifyMatch(FreeProduct_Slab1, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeProduct_Slab1 + ' :Slab 1 Free product correctly displayed !')

Mobile.verifyMatch(FreeSKU_Qty_Slab1, findTestData('Mobile Input Data/SO_Batch').getValue('S1_Free_Qty', 5), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab 1 Free Product Case Qty correctly displayed !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.comment('Validation for Free product lodaing in summary screen')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.scrollToText(GlobalVariable.Scheme_Free_SKU, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

Mobile.takeScreenshot()

Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

if(Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5,FailureHandling.OPTIONAL)) 
	{
		KeywordUtil.logInfo('free qty is visible')
	}
	
	else {
		Mobile.swipe(200, 100, 500, 600, FailureHandling.OPTIONAL)
	}
	

Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Mobile Input Data/SO_Batch').getValue('S1_Free_Qty', 5), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.comment('Validation for discount percentage calculation')

'Scheme discount perc for Product_1'
GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 1)

Mobile.scrollToText(GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

DP1_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

'Discountpercentage calculation'
DP = findTestData('Mobile Input Data/SO_Batch').getValue('S1_Discount_Percentage', 1)

DP_Cal1 = (Double.parseDouble(DP1_OrderAmt) * (Double.parseDouble(DP) / 100))

KeywordUtil.logInfo(DP_Cal1 + ' : Scheme discount percentage')

Disc_Scheme_Amt1 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

DiscSchemeAmt_1 = Disc_Scheme_Amt1.replaceAll('- ', '')

Mobile.verifyEqual(DP_Cal1, Double.parseDouble(DiscSchemeAmt_1), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

'Scheme discount value for Product_2'
GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 2)

Mobile.scrollToText(GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

DP2_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

'Discountpercentage calculation'
DP_Cal2 = (Double.parseDouble(DP2_OrderAmt) * (Double.parseDouble(DP) / 100))

KeywordUtil.logInfo(DP_Cal2 + ' : Scheme discount percentage')

Disc_Scheme_Amt2 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

DiscSchemeAmt_2 = Disc_Scheme_Amt2.replaceAll('- ', '')

Mobile.verifyEqual(DP_Cal2, Double.parseDouble(DiscSchemeAmt_2), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.comment('Discount Value calculation')

GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 3)

Mobile.scrollToText(GlobalVariable.ProductName, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

DV1_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 4)

Mobile.scrollToText(GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

DV2_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Total_DV_Amount = (Double.parseDouble(DV1_OrderAmt) + Double.parseDouble(DV2_OrderAmt))

KeywordUtil.logInfo(Total_DV_Amount + ' : Total Amount')

DV = findTestData('Mobile Input Data/SO_Batch').getValue('S1_Discount_Value', 3)

DV_Cal = (Double.parseDouble(DV) / Total_DV_Amount)

String D_Cal = DV_Cal

KeywordUtil.logInfo(DV_Cal + ' : discount')

String numStr1 = String.valueOf(D_Cal)

String result1 = numStr1.substring(0, 4)

KeywordUtil.logInfo('Rounded down value: ' + result1)

DV_Cal1 = (Double.parseDouble(DV2_OrderAmt) * Double.parseDouble(result1))

KeywordUtil.logInfo(DV_Cal1 + 'Calculation1')

DV_Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

DV_Scheme_Amt1 = DV_Scheme_Amt.replaceAll('- ', '')

Mobile.verifyEqual(DV_Cal1, Double.parseDouble(DV_Scheme_Amt1), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 3)

Mobile.scrollToText(GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

DV1_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

DV_Cal2 = (Double.parseDouble(DV1_OrderAmt) * Double.parseDouble(result1))

KeywordUtil.logInfo(DV_Cal2 + 'Calculation2')

DV1_Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

DV1_Scheme_Amt1 = DV1_Scheme_Amt.replaceAll('- ', '')

Mobile.verifyEqual(DV_Cal2, Double.parseDouble(DV1_Scheme_Amt1), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.comment('Validation for Tax and Discount')

GlobalVariable.ProductName = findTestData('Mobile Input Data/SO_Batch').getValue('SKU_Name', 10)

Mobile.scrollToText(GlobalVariable.ProductName, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

Tax_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Discount = findTestData('Mobile Input Data/SO_Batch').getValue('Discount', 10)

QTY = findTestData('Mobile Input Data/SO_Batch').getValue('Qty', 10)

//formula for discount
//totalDiscountValue = totalOrderValue - totalQty * (batchProductBo.srp - discount)
qtycal = (Double.parseDouble(QTY) * (Double.parseDouble(GlobalVariable.RP_Price) - Double.parseDouble(Discount)))

totalDiscountValue = (Double.parseDouble(Tax_OrderAmt) - qtycal)

KeywordUtil.logInfo(totalDiscountValue + ' : Discount Amount')

//Discount new
Actual_SplitScreen_ITEM_Disc = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-ITEM_Discount'), 0, FailureHandling.STOP_ON_FAILURE)

Actual_SplitScreen_ITEM_Disc = Actual_SplitScreen_ITEM_Disc.replaceAll('-', '')

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_ITEM_Disc), totalDiscountValue, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo('Verified : the Amount Splitup Screen ITEM Discount Values is ' + Actual_SplitScreen_ITEM_Disc)

//tax new
Actual_SplitScreen_CGST_Value = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

CGST_Percentage = findTestData('Mobile Input Data/SO_Batch').getValue('Tax', 10)

CGST_CAL_Amount = (Double.parseDouble(Tax_OrderAmt) - Double.parseDouble(Actual_SplitScreen_ITEM_Disc))

Expected_CGST_Value = (CGST_CAL_Amount * (Double.parseDouble(CGST_Percentage) / 100))

Mobile.verifyEqual(Double.parseDouble(Actual_SplitScreen_CGST_Value), Expected_CGST_Value, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Overall_Scheme_Amount = (((DP_Cal1 + DP_Cal2) + DV_Cal1) + DV_Cal2)

KeywordUtil.logInfo(Overall_Scheme_Amount + ' : overall scheme amount')

'Info screen Validations'
Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

Overall_OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Overall_Scheme_Amount, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

ITEM_Disc = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-ITEM_Discount'), 0, FailureHandling.STOP_ON_FAILURE)

ITEM_Disc1 = ITEM_Disc.replaceAll('-', '')

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

SchemeCal = ((Double.parseDouble(SchemeAmt) + Double.parseDouble(ITEM_Disc1)) - Double.parseDouble(CGST))

println(SchemeCal)

Calculated_TotalAmt = (Double.parseDouble(Overall_OrderAmt) - SchemeCal)

println(Calculated_TotalAmt)

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

exlpath = 'DDF\\Mobile Input Data\\Mobile Input Data.xlsx'

Sheetname = 'SO_Batch'

workbook01 = ExcelKeywords.getWorkbook(exlpath)

sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 22, totalDiscountValue)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 23, Expected_CGST_Value)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 24, DP_Cal1)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 25, DP_Cal2)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 26, DV_Cal1)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 27, DV_Cal2)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 28, Overall_OrderAmt)

ExcelKeywords.saveWorkbook(exlpath, workbook01)

 Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Generate_OrderInvoice'), [:], FailureHandling.STOP_ON_FAILURE)

ExcelKeywords.setValueToCellByIndex(sheet1, 1, 29, GlobalVariable.InvoiceNo)

ExcelKeywords.saveWorkbook(exlpath, workbook01)


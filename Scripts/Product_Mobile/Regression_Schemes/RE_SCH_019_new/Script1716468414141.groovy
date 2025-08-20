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

//Mobile.startApplication(GlobalVariable.APK_File, false)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

String sheet_name = '2_B6_MTS_FP_Slabs_2_App'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    //slab_1
    'Slab_1'
    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('SKU_Name', Scheme_Index)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

    Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Piece_Price = Double.parseDouble(Actual_PiecePrice)

    KeywordUtil.logInfo('Price of the product' + Piece_Price)

    Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

    Slab_1_Min_Qty = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Piece_quantity', Scheme_Index)

    println(GlobalVariable.Qty = Slab_1_Min_Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

    'total value calculation'
    Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

    GlobalVariable.Total = Total

    'verification of total amount'
    Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), GlobalVariable.Total, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Total.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula.')

    //get FP price
    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

    GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_product', Scheme_Index)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.Scheme_Free_SKU, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    GlobalVariable.Scheme_GetProduct01_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

    if ((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab2')) {
        'Positive flow Scheme Validation for single slab'
        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' : Slab Scheme Name correctly applied !')

        'Slab Description Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' : Scheme Description correctly displayed !')

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

        Mobile.delay(3)

        'View screeen scheme validations for Free product'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_product', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        'Free product_1 name verification in view screen'
        Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product name correctly displayed !')

        Mobile.verifyMatch(FreeSKU_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_Qty_Slab + ' : Free product Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', Scheme_Index))

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        'Summary screen scheme validations'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_product', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        KeywordUtil.logInfo('Scheme is applied and scheme name is displayed since it is positive flow ')

        Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

        'Free product name verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product name correctly displayed in Summary Screen !')

        'Free product quantity verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

        'Individual Buy Product Scheme amount validation'

        'Buy Product_1'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('SKU_Name', Scheme_Index)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        'Validation for Order Value'
        Mobile.takeScreenshot()

        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        Mobile.verifyEqual(Double.parseDouble(OrderAmt), GlobalVariable.Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'Validation for Scheme Amount'
        Slab_Qty = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', Scheme_Index)

        Scheme_Applied_Get_Product_Total_Amount = (Double.parseDouble(GlobalVariable.Scheme_GetProduct01_PiecePrice) * Double.parseDouble(Slab_Qty))

        KeywordUtil.logInfo(Scheme_Applied_Get_Product_Total_Amount.toString() + ' : Scheme_Applied_Get_Product_Total_Amount')

        Buy_Products_OrderValue = GlobalVariable.Total

        Buy_Product01_Splited_Scheme_Amt = ((Scheme_Applied_Get_Product_Total_Amount * GlobalVariable.Total) / Buy_Products_OrderValue)

        KeywordUtil.logInfo(Buy_Product01_Splited_Scheme_Amt.toString() + ' : Buy_Product01_Splited_Scheme_Amt')

        Scheme_Amt_for_Buy_Product01 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        Scheme_Amt_for_Buy_Product01 = Scheme_Amt_for_Buy_Product01.replaceAll('- ', '')

        KeywordUtil.logInfo(Scheme_Amt_for_Buy_Product01)

        Mobile.verifyEqual(Double.parseDouble(Scheme_Amt_for_Buy_Product01), Buy_Product01_Splited_Scheme_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'Validation for Total'
        Buy_Product01_TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

        Calculated_Buy_Product01_TotalAmt = (GlobalVariable.Total - Buy_Product01_Splited_Scheme_Amt)

        Mobile.verifyEqual(Double.parseDouble(Buy_Product01_TotalAmt), Calculated_Buy_Product01_TotalAmt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3 //MTS both slabs
            )
    } else if (Quantity_variations.get(i) == 'Both Slabs') {
        'Applying Scheme screen validations for Both Slabs'
        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeName2 = Mobile.getText(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/SchemeName_2'), 0)

        SchemeDesc1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        SchemeDesc2 = Mobile.getText(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/SchemeDesc_2'), 0)

        'Slab1 Scheme name validation'
        Mobile.verifyMatch(SchemeName1, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName1 + ' : Scheme Name correctly displayed !')

        'Slab2 Scheme name validation'
        Mobile.verifyMatch(SchemeName2, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName2 + ' : Scheme Name correctly displayed !')

        'Slab1 validation'
        Mobile.verifyMatch(SchemeDesc1, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Slab_02', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc1 + ' : Slab1 Description correctly displayed !')

        'Slab2 validation'
        Mobile.verifyMatch(SchemeDesc2, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc2 + ' : Slab Description correctly displayed !')

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

        'View screeen scheme validations'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_product', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_Qty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        Mobile.verifyMatch(FreeProduct_Slab2, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab2 + ' :Slab2  Free product correctly displayed !')

        Expecetd_FreeSKU_MinQty_Slab2 = ('Min:' + findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', 2))

        GlobalVariable.Qty = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', 2)

        Mobile.verifyMatch(FreeSKU_MinQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab2 + ' : Free Product Min Case Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab2 = ('Max:' + findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', 2))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab2 + ' : Free Product Max Piece Qty correctly displayed !')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        Mobile.tap(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/Scheme_View_Btn_2'), 0)

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_product', Scheme_Index)

        Expected_FreeQuantity = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', 1))

        Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' : Free Product Min Case Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', 1))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Free Product Max Piece Qty correctly displayed !')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        FreeSKU_Qty_Slab = (Double.parseDouble(FreeSKU_Qty_Slab1) + Double.parseDouble(FreeSKU_Qty_Slab2))

        Mobile.verifyEqual(FreeSKU_Qty_Slab, Double.parseDouble(Expected_FreeQuantity), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' : FreeSKU_Qty correctly displayed !')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        'Summary screen scheme validations'

        'For Free Product1'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_product', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global_Index_1)'), 5)

        Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product correctly displayed in Summary Screen !')

        Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', 2), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

        'For Free Product 2'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_product', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

        Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product correctly displayed in Summary Screen !')

        Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', 1), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

        'Individual Buy Product Scheme amount validation'

        'Buy Product_1'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('SKU_Name', Scheme_Index)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        'Validation for Order Value'
        Mobile.takeScreenshot()

        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        Mobile.verifyEqual(Double.parseDouble(OrderAmt), GlobalVariable.Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'Validation for Scheme Amount'
        Slab_Qty = findTestData('Batch_Scheme_Inputs_01/2_B6_MTS_FP_Slabs_2_App').getValue('Free_quantity', Scheme_Index)

        Scheme_Applied_Get_Product_Total_Amount = (Double.parseDouble(GlobalVariable.Scheme_GetProduct01_PiecePrice) * Double.parseDouble(Slab_Qty))

        KeywordUtil.logInfo(Scheme_Applied_Get_Product_Total_Amount.toString() + ' : Scheme_Applied_Get_Product_Total_Amount')

        Buy_Products_OrderValue = GlobalVariable.Total

        Buy_Product01_Splited_Scheme_Amt = ((Scheme_Applied_Get_Product_Total_Amount * GlobalVariable.Total) / Buy_Products_OrderValue)

        KeywordUtil.logInfo(Buy_Product01_Splited_Scheme_Amt.toString() + ' : Buy_Product01_Splited_Scheme_Amt')

        Scheme_Amt_for_Buy_Product01 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        Scheme_Amt_for_Buy_Product01 = Scheme_Amt_for_Buy_Product01.replaceAll('- ', '')

        KeywordUtil.logInfo(Scheme_Amt_for_Buy_Product01)

        Mobile.verifyEqual(Double.parseDouble(Scheme_Amt_for_Buy_Product01), Buy_Product01_Splited_Scheme_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'Validation for Total'
        Buy_Product01_TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

        Calculated_Buy_Product01_TotalAmt = (GlobalVariable.Total - Buy_Product01_Splited_Scheme_Amt)

        Mobile.verifyEqual(Double.parseDouble(Buy_Product01_TotalAmt), Calculated_Buy_Product01_TotalAmt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)
    } else if (Quantity_variations.get(i) == 'Less_Slab1') {
        Mobile.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_019_CT_1'), [:], FailureHandling.STOP_ON_FAILURE)
    }
    
    Mobile.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/Order_generate'), [:], FailureHandling.STOP_ON_FAILURE)
}

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


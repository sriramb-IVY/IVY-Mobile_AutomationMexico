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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

String sheet_name = '14_B5_OTP_FP_ADDISC_MS'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Flag = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Flag')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

////for slab1 & slab2 norm FP validation + additional disc scheme validation
for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    if ((Quantity_variations.get(i) == 'First slab') || (Quantity_variations.get(i) == 'Second slab')) {
        'Positive flow FP Scheme Validation & Additional disc scheme validation for slab2'
        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

        'Buy_SKU_1'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('SKU_Name_1', Scheme_Index)

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

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

        Min_Qty_1 = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Min_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Min_Qty_1)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

        // Total_1 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)
        'total value calculation for product1'
        Total_1 = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.Qty)) * Double.parseDouble(Actual_PiecePrice))

        'verification of total amount for product1'
        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_1, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total_1.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 1st Product')

        //Sku2
        'Buy_SKU_2'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('SKU_Name_2', Scheme_Index)

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

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

        Min_Qty_2 = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Max_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Min_Qty_2)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

        //Total_2 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)
        'total value calculation for product2'
        Total_2 = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.Qty)) * Double.parseDouble(Actual_PiecePrice))

        'verification of total amount for product2'
        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_2, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total_2.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 2nd Product')

        //sku3
        'Buy_SKU_3'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('SKU_Name_3', Scheme_Index)

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

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

        Min_Qty_3 = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Mid_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Min_Qty_3)

        total_qty_entered = ((Double.parseDouble(Min_Qty_1) + Double.parseDouble(Min_Qty_2)) + Double.parseDouble(Min_Qty_3))

        println(GlobalVariable.Total_qty = total_qty_entered)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

        //Total_3 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)
        'total value calculation for product3'
        Total_3 = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.Qty)) * Double.parseDouble(Actual_PiecePrice))

        'verification of total amount for product3'
        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_3, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total_3.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 3rd Product')

        'sum of total value of all the 3 products'
        Total = ((Total_1 + Total_2) + Total_3)

        KeywordUtil.logInfo(Total.toString() + ' : Sum of total value of all the 3 products')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' : Slab Scheme Name correctly applied !')

        'Slab Description Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' : Scheme Description correctly displayed !')

        Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Additional Discount Field'), 5)

        Additional_Discount_Amt = Discountvalue.replaceAll('Additional Discount : ', '')

        KeywordUtil.logInfo(Additional_Discount_Amt + ' :  ')

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

        Mobile.delay(3)

        GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Free_Product', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        'Free product name verification in view screen'
        Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product names correctly displayed !')

        Mobile.verifyMatch(FreeSKU_Qty_Slab, findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_Qty_Slab + ' : Free Product Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Free_quantity', Scheme_Index))

        'Free product quantity verification in view screen'
        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product Max Piece Qty correctly displayed !')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        'Summary screen scheme validations'
        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Free_Product', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        KeywordUtil.logInfo('Scheme is applied and scheme name is displayed since it is positive flow ')

        Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

        'Free product name verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product name correctly displayed in Summary Screen !')

        'Free product quantity verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

        Mobile.delay(2)

        'Additional discount calculation for Product_1'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('SKU_Name_1', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_1 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_1 = Scheme_Amt_1.replaceAll('- ', '')

        SchemeAmountt_1 = Double.parseDouble(SchemeAmt_1)

        KeywordUtil.logInfo(SchemeAmt_1)

        add_disc = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Additional_discount', Scheme_Index)

        additional_disc = Double.parseDouble(add_disc)

        double cal1 = GlobalVariable.Total_qty * additional_disc

        KeywordUtil.logInfo('Calculation1 of 1st product ' + cal1)

        cal2 = (cal1 * Total_1)

        KeywordUtil.logInfo('Calculation2 of 1st product ' + cal2)

        scheme_disc_for_one_product = (cal2 / Total)

        scheme_disc_product1 = scheme_disc_for_one_product.round(2)

        KeywordUtil.logInfo('scheme_disc_product1 ' + scheme_disc_product1)

        Mobile.verifyEqual(scheme_disc_product1, SchemeAmountt_1, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo(scheme_disc_product1 + ' : Additional discount is displayed properly for Product_1 !')

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        'Additional discount calculation for Product_2'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('SKU_Name_2', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_2 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_2 = Scheme_Amt_2.replaceAll('- ', '')

        SchemeAmountt_2 = Double.parseDouble(SchemeAmt_2)

        KeywordUtil.logInfo(SchemeAmt_2)

        add_disc = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Additional_discount', Scheme_Index)

        additional_disc = Double.parseDouble(add_disc)

        double cal1_2 = GlobalVariable.Total_qty * additional_disc

        KeywordUtil.logInfo('Calculation1 of 2nd product ' + cal1_2)

        cal2_2 = (cal1_2 * Total_2)

        KeywordUtil.logInfo('Calculation2 of 2nd product ' + cal2_2)

        scheme_disc_for_2nd_product = (cal2_2 / Total)

        scheme_disc_product2 = scheme_disc_for_2nd_product.round(2)

        KeywordUtil.logInfo('scheme_disc_product2 ' + scheme_disc_product2)

        Mobile.verifyEqual(scheme_disc_product2, SchemeAmountt_2, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo(scheme_disc_product2 + ' : Additional discount is displayed properly for Product_2 !')

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        'Additional discount calculation for Product_3'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('SKU_Name_3', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_3 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_3 = Scheme_Amt_3.replaceAll('- ', '')

        SchemeAmountt_3 = Double.parseDouble(SchemeAmt_3)

        KeywordUtil.logInfo(SchemeAmt_3)

        add_disc = findTestData('Mobile Input Data/14_B5_OTP_FP_ADDISC_MS').getValue('Additional_discount', Scheme_Index)

        additional_disc = Double.parseDouble(add_disc)

        double cal1_3 = GlobalVariable.Total_qty * additional_disc

        KeywordUtil.logInfo('Calculation1 of 3rd product ' + cal1_3)

        cal2_3 = (cal1_3 * Total_3)

        KeywordUtil.logInfo('Calculation2 of 3rd product ' + cal2_3)

        scheme_disc_for_3rd_product = (cal2_3 / Total)

        scheme_disc_product3 = scheme_disc_for_3rd_product.round(2)

        KeywordUtil.logInfo('scheme_disc_product3 ' + scheme_disc_product3)

        Mobile.verifyEqual(scheme_disc_product3, SchemeAmountt_3, FailureHandling.STOP_ON_FAILURE)

        KeywordUtil.logInfo(scheme_disc_product3 + ' : Additional discount is displayed properly for Product_3 !')

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        'Sum of scheme amount of all the 3 products '
        Expected_total_scheme_amount = ((Double.parseDouble(SchemeAmt_1) + Double.parseDouble(SchemeAmt_2)) + Double.parseDouble(SchemeAmt_3))

        KeywordUtil.logInfo(Expected_total_scheme_amount + ' : Total Scheme Amount of all the Products !')

        GlobalVariable.Total_qty = Total

        GlobalVariable.Total_Amt = Expected_total_scheme_amount

        WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_016_CT_4'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_014_CT_1'), [:], FailureHandling.STOP_ON_FAILURE)


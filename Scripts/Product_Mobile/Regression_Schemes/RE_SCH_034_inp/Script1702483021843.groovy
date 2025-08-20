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

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

String sheet_name = '16_B9_MTR_FP_MS_ADDisc'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    KeywordUtil.logInfo(Scheme_Index.toString() + '-------------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>')

    not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    if ((((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab2')) || (Quantity_variations.get(i) == 'Double_Slab1')) || (Quantity_variations.get(i) == 'Double_Slab2')) {
        'Buy_SKU_1'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Buy_Group1_P1', Scheme_Index)

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

        Slab_1_Min_Qty = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('BG1_Piece_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Slab_1_Min_Qty)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

        'total value calculation for product1'
        Total_1 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

        'verification of total amount for product1'
        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_1, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total_1.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 1st Product')

        'Buy_SKU_2'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Buy_Group2_P1', Scheme_Index)

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

        Slab_2_Min_Qty = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('BG2_Piece_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Slab_1_Min_Qty)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

        'total value calculation for product2'
        Total_2 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

        'verification of total amount for product2'
        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_2, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total_2.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 2nd Product')

        'Buy_SKU_3'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Buy_Group2_P2', Scheme_Index)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

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

        Slab_3_Min_Qty = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('BG3_Piece_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Slab_3_Min_Qty)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

        'total value calculation for product3'
        Total_3 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

        'verification of total amount for product2'
        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_3, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total_3.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 2nd Product')

        total_qty_entered = ((Double.parseDouble(Slab_1_Min_Qty) + Double.parseDouble(Slab_2_Min_Qty)) + Double.parseDouble(Slab_3_Min_Qty))

        KeywordUtil.logInfo(total_qty_entered.toString())

        GlobalVariable.Total_qty = total_qty_entered

        'sum of total value of all the products'
        Total = ((Total_1 + Total_2) + Total_3)

        GlobalVariable.Total = Total

        KeywordUtil.logInfo(Total.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula.')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        'Positive flow FP Scheme Validation & Additional disc scheme validation for slab2'
        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        if (((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab2')) || (Quantity_variations.get(i) == 'Double_S2')) {
            'For Slab2 Additional discount will apply'

            'Scheme Name Verification'
            Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' : Slab Scheme Name correctly applied !')

            'Slab Description Verification'
            Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' : Scheme Description correctly displayed !')

            Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Additional Discount Field'), 5)

            Additional_Discount_Amt = Discountvalue.replaceAll('Additional Discount : ', '')

            KeywordUtil.logInfo(Additional_Discount_Amt + ' :  ')

            Mobile.takeScreenshot()
        }
        
        Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

        Mobile.delay(3)

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Free_Product', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        'Free product name verification in view screen'
        Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product names correctly displayed !')

        Mobile.verifyMatch(FreeSKU_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_Qty_Slab + ' : Free Product Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Free_quantity', Scheme_Index))

        'Free product quantity verification in view screen'
        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product Max Piece Qty correctly displayed !')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        'Summary screen scheme validations'
        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Free_Product', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        KeywordUtil.logInfo('Scheme is applied and scheme name is displayed since it is positive flow ')

        Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

        'Free product name verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product name correctly displayed in Summary Screen !')

        'Free product quantity verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

        Mobile.delay(2)

        'Additional discount calculation for Product_1'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Buy_Group1_P1', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_1 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_1 = Scheme_Amt_1.replaceAll('- ', '')

        SchemeAmountt_1 = Double.parseDouble(SchemeAmt_1)

        KeywordUtil.logInfo(SchemeAmt_1)

        add_disc = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Additional_discount', Scheme_Index)

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
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Buy_Group2_P1', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_2 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_2 = Scheme_Amt_2.replaceAll('- ', '')

        SchemeAmountt_2 = Double.parseDouble(SchemeAmt_2)

        KeywordUtil.logInfo(SchemeAmt_2)

        add_disc = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Additional_discount', Scheme_Index)

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
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Buy_Group2_P2', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_3 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_3 = Scheme_Amt_3.replaceAll('- ', '')

        SchemeAmountt_3 = Double.parseDouble(SchemeAmt_3)

        KeywordUtil.logInfo(SchemeAmt_3)

        add_disc = findTestData('Batch_Scheme_Inputs_01/16_B9_MTR_FP_MS_ADDisc').getValue('Additional_discount', Scheme_Index)

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

        GlobalVariable.label1 = Expected_total_scheme_amount

        TotalSchemeAmount = Expected_total_scheme_amount.round()

        KeywordUtil.logInfo(Expected_total_scheme_amount + ' : Total Scheme Amount of all the Products !')

        // GlobalVariable.Total_qty = Total
        // GlobalVariable.Total_Amt = TotalSchemeAmount
        WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_016_CT_4'), [:], FailureHandling.STOP_ON_FAILURE)
    } else if (Quantity_variations.get(i) == 'Less than slab1') {
        WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_037_CT_1'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}


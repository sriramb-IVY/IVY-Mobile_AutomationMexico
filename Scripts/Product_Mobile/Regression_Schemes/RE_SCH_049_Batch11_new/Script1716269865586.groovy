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

String sheet_name = 'B11_OTPR_SS_Get(EXCL)_DV'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

    'Get Exclude with ANY Condition scheme'

    'Buy_SKU_1'
    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Non_Get_exl_product', Scheme_Index)

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

    Schemevalue = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Scheme_value', Scheme_Index)

    Sales_Value = Double.parseDouble(Schemevalue)

    'Entering Slab Piece qty'
    int Quantity = Sales_Value / Piece_Price

    GlobalVariable.Qty = Quantity.toString()

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

    'Total value calculation for Non exclude product1'
    Total_1 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

    'verification of total amount for product1'
    Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_1, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Total_1.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 1st Product')

    //Sku2
    'Get Exclude Product'
    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Get_exl_product', Scheme_Index)

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

    Schemevalue2 = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Get_Exc_Scheme_value', Scheme_Index)

    Sales_Value_2 = Double.parseDouble(Schemevalue2)

    'Entering Slab Piece qty'
    int Quantity_1 = Sales_Value_2 / Piece_Price

    GlobalVariable.Qty = Quantity_1.toString()

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('SKU Total Value =' + SKU_TOTAL)

    'total value calculation for product2'
    Total_2 = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

    'verification of total amount for product2'
    Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total_2, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Total_2.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula for 2nd Product')

    Total = (Total_1 + Total_2)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

    if (((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab achieved and Remaining Qty')) || (Quantity_variations.get(i) == 'Get_Excluded_Product')) {
        'Positive flow Scheme Validation for slab and Get Exclude Product'

        'Scheme should be apply & scheme name should be display since it is positive flow'
        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' :Scheme Name correctly displayed !')

        'Scheme Slab Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Scheme_Slab', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' :Scheme Description correctly displayed !')

        if (Quantity_variations.get(i) == 'Slab achieved and Remaining Qty') {
            'OTPR Formula'

            'OTPR  =(Total no of Order Qty / (Buy Product Max Qty / Get Product Max Qty)).'
            Buy_Max = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Buy_Max', Scheme_Index)

            Get_Min = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Get_Product_Min', Scheme_Index)

            Calcu1 = (Double.parseDouble(Buy_Max) / Double.parseDouble(Get_Min))

            Discount = (Total / Calcu1)

            KeywordUtil.logInfo(Discount + ' : Div Calc')

            Calculated_Discountvalue = Discount.round()

            KeywordUtil.logInfo(Calculated_Discountvalue + ' :Calculated Discount value!')

            Mobile.verifyEqual(Double.parseDouble(Discountvalue), Calculated_Discountvalue, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()
        } else if ((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Get_Excluded_Product')) {
            Discount_value_Sheet = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Discount_Value', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Discountvalue + ' :Slab Discount Value correctly applied !')
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        Mobile.takeScreenshot()

        if ((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab achieved and Remaining Qty')) {
            'Summary screen'
            Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            'Scheme discount Value for Non Exclude Product_1'
            GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Non_Get_exl_product', Scheme_Index)

            Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

            Scheme_Amt_1 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt_1 = Scheme_Amt_1.replaceAll('- ', '')

            SchemeAmountt_1 = Double.parseDouble(SchemeAmt_1)

            Mobile.verifyEqual(Double.parseDouble(SchemeAmt_1), Double.parseDouble(Discountvalue), FailureHandling.STOP_ON_FAILURE)

            KeywordUtil.logInfo(SchemeAmt_1 + ' : Scheme Value is displayed properly for Non Get Exclue Product !')

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

            'Scheme discount Value for Get Exclude Product'
            GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Get_exl_product', Scheme_Index)

            Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

            Scheme_Amt_2 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt_2 = Scheme_Amt_2.replaceAll('- ', '')

            SchemeAmountt_2 = Double.parseDouble(SchemeAmt_2)

            Mobile.verifyEqual(SchemeAmountt_2, '0', FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeAmt_2 + ' : Scheme Value is displayed properly for Get Exclude Product !')

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

            SchemeAmount = (Double.parseDouble(SchemeAmt_1) + Double.parseDouble(SchemeAmt_2))

            'Validation in info screen'
            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Mobile.takeScreenshot()

            OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

            Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Discountvalue), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeAmt.toString() + ' : Calculated Scheme Amount correctly displayed !')

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

            'Total amount validation in info screen'
            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :Total amount in split screen displayed correctly !')

            Mobile.delay(2)

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)
        } else if (Quantity_variations.get(i) == 'Get_Excluded_Product') {
            'Scheme discount Value for Get Exclude Product'
            GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_SS_Get(EXCL)_DV').getValue('Get_exl_product', Scheme_Index)

            Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

            Scheme_Amt_2 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt_2 = Scheme_Amt_2.replaceAll('- ', '')

            SchemeAmountt_2 = Double.parseDouble(SchemeAmt_2)

            Mobile.verifyEqual(SchemeAmountt_2, '0', FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeAmt_2 + ' : Scheme Value is displayed properly for Get Exclude Product !')

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

            'Validation in info screen'
            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Mobile.takeScreenshot()

            OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

            Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(SchemeAmt), '0', FailureHandling.OPTIONAL)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeAmt.toString() + ' : Calculated Scheme Amount correctly displayed !')

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

            'Total amount validation in info screen'
            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :Total amount in split screen displayed correctly !')

            Mobile.delay(2)

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)
        }
        
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

        Mobile.delay(2)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE) //Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
    } else if (Quantity_variations.get(i) == 'Less than Slab1') {
        'negative scenario scheme validation'

        'Scheme will not apply since it is negative scenario'
        Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        Mobile.takeScreenshot()

        Mobile.delay(2)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

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

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt), '0', FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

        Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Total amount in split screen displayed correctly !')

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 3)

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

        Mobile.delay(1)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

String sheet_name = 'B11_OTPR_MS_FP_APP(Value)'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

'Slab1, Slab2 & Double of Slab1 : Positive flow Scheme Validation'

'Buy_Product'
not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Buy_Product', Scheme_Index)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Piece_Price = Double.parseDouble(Actual_PiecePrice)

    KeywordUtil.logInfo('Price of the product' + Actual_PiecePrice)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

    Tot_value = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Sales_Value', Scheme_Index)

    Sales_Value = Double.parseDouble(Tot_value)

    'Entering Slab1 Piece qty'
    int Quantity = Sales_Value / Piece_Price

    GlobalVariable.Qty = Quantity.toString()

    KeywordUtil.logInfo('Piece quantity to given' + GlobalVariable.Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    'Verify the Total amount of Free_Product displayed in the screen'
    Mobile.takeScreenshot()

    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('Buy_Product Total Value ' + SKU_TOTAL)

    Buy_Product_Total = (Double.parseDouble(GlobalVariable.Qty) * Double.parseDouble(Actual_PiecePrice))

    Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Buy_Product_Total, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Buy_Product_Total.toString() + ' : Buy_Product Total Amount calculated and displayed correctly according the formula.')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

    if (((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab2')) || (Quantity_variations.get(i) == 'Slab achieved and Remaining Qty')) {
        'Positive flow Scheme Validation for single slab'

        'Scheme should be apply & scheme name should be display since it is positive flow'
        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' :Scheme Name correctly displayed !')

        'Scheme Slab Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Slab', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' :Scheme Description correctly displayed !')

        if (Quantity_variations.get(i) == 'Slab achieved and Remaining Qty') {
            'OTPR Formula'

            'Free Product Qty = (Total no of Order Qty / (Buy Product Min Qty / Get Product Min Qty)).'
            Buy_Max = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Buy_Max', Scheme_Index)

            Get_Product = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Get_Product_Min', Scheme_Index)

            Buyquantity = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Sales_Value', Scheme_Index)

            Calc1 = (Double.parseDouble(Buy_Max) / Double.parseDouble(Get_Product))

            FreeProduct = (Double.parseDouble(Buyquantity) / Calc1)

            FreeProduct_qty = Math.floor(FreeProduct)

            KeywordUtil.logInfo(FreeProduct_qty + ' : Free product quantity')

            FreeProduct = FreeProduct_qty.toString()

            GlobalVariable.FreeProductQty = FreeProduct.replaceAll('.0', '')

            KeywordUtil.logInfo(GlobalVariable.FreeProductQty + ' : Free product quantity')
        } else {
            GlobalVariable.FreeProductQty = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Get_Product_Qty', Scheme_Index)
        }
        
        'View screen scheme validations for Free Product1'
        Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

        Mobile.delay(2)

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Get_Product', Scheme_Index)

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
        Expecetd_FreeSKU_MinQty_Slab = ('Min:' + GlobalVariable.FreeProductQty)

        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product1 Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab = ('Max:' + GlobalVariable.FreeProductQty)

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product1 Max Piece Qty correctly displayed !')

        Mobile.takeScreenshot()

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Get_Product', Scheme_Index)

        FreeSKU_1_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        Mobile.verifyMatch(FreeSKU_1_Qty_Slab, GlobalVariable.FreeProductQty, false, FailureHandling.STOP_ON_FAILURE)

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
        Mobile.verifyMatch(Slab_FreeSku_PieceQty, GlobalVariable.FreeProductQty, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

        'Apportinate scheme Free Product amount validation'

        'Get Product'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Get_Product', Scheme_Index)

        Get_Product_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Calculated_Get_Amt = (Double.parseDouble(GlobalVariable.Scheme_GetProduct01_PiecePrice) * Double.parseDouble(GlobalVariable.FreeProductQty))

        KeywordUtil.logInfo('Get amount =' + Calculated_Get_Amt)

        Mobile.verifyEqual(Double.parseDouble(Get_Product_Amt), Calculated_Get_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'Validation in info screen'
        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

        Mobile.takeScreenshot()

        'Validate Order Value'
        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        'This is Apportionate Scheme so Buy products total added with get products total is equal to Order value '
        Buy_Products_OrderValue = (Buy_Product_Total + Calculated_Get_Amt)

        Mobile.verifyEqual(Double.parseDouble(OrderAmt), Buy_Products_OrderValue, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'Validate Scheme Amount'
        Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt = Scheme_Amt.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt)

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Get_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        'Validate Total Amount'
        TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

        Calculated_Total_Amt = (Buy_Products_OrderValue - Calculated_Get_Amt)

        Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_Total_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 3)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

        Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

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

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE //        Mobile.verifyElementNotVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 
            //            5)
            ) //
        //        Mobile.takeScreenshot()
        //Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
    } else if (Quantity_variations.get(i) == 'Less than slab1') {
        'Negative Scheme validation'
        Mobile.takeScreenshot()

        Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        'Scheme will not apply since it is negative scenario'
        Mobile.takeScreenshot()

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        'Scheme name, sku, quantity is not displayed since it is negative scenario screen'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/B11_OTPR_MS_FP_APP(Value)').getValue('Get_Product', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        Mobile.verifyEqual(Double.parseDouble(OrderAmt), Buy_Product_Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt = Scheme_Amt.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt)

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt), '0', FailureHandling.STOP_ON_FAILURE)

        CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

        TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

        Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

        Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :  Slab_1 Total amount in split screen displayed correctly !')

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

        Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

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

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


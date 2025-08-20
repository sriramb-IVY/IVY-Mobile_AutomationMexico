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

String sheet_name = '26_B11_OTPR_MS_FP(Vari)'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    if (((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab2')) || (Quantity_variations.get(i) == 'Slab achieved and Remaining Qty')) {
        not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

        'Buy_Product01'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Buy_Product1', Scheme_Index)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        KeywordUtil.logInfo('Price of the product' + Actual_PiecePrice)

        Slab = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Min_quantity', Scheme_Index)

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
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Buy_Product2', Scheme_Index)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        KeywordUtil.logInfo('Price of the product' + Actual_PiecePrice)

        Slab = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Min_quantity', Scheme_Index)

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

        'Buy_Product03'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Buy_Product3', Scheme_Index)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        KeywordUtil.logInfo('Price of the product' + Actual_PiecePrice)

        Slab = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Min_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Slab)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        'Verify the Total amount of get_product01 displayed in the screen'
        Mobile.takeScreenshot()

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('Buy_Product01 Total Value ' + SKU_TOTAL)

        Buy_Product03_Total = (Double.parseDouble(GlobalVariable.Qty) * Double.parseDouble(Actual_PiecePrice))

        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Buy_Product03_Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Buy_Product03_Total.toString() + ' : Buy_Product01 Total Amount calculated and displayed correctly according the formula.')

        'Buy_Product04'
        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Buy_Product4', Scheme_Index)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        KeywordUtil.logInfo('Price of the product' + Actual_PiecePrice)

        Slab = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Min_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Slab)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        'Verify the Total amount of get_product02 displayed in the screen'
        Mobile.takeScreenshot()

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('Buy_Product01 Total Value ' + SKU_TOTAL)

        Buy_Product04_Total = (Double.parseDouble(GlobalVariable.Qty) * Double.parseDouble(Actual_PiecePrice))

        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Buy_Product04_Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Buy_Product04_Total.toString() + ' : Buy_Product02 Total Amount calculated and displayed correctly according the formula.')

        Total = (((Buy_Product01_Total + Buy_Product02_Total) + Buy_Product03_Total) + Buy_Product04_Total)

        KeywordUtil.logInfo(Total.toString() + ' : Sum of all Buy Product Total Amount calculated.')

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        'Positive flow Scheme Validation for single slab'
        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' : Slab Scheme Name correctly applied !')

        'Slab Description Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' : Scheme Description correctly displayed !')

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

        Mobile.delay(3)

        'View screeen scheme validations'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Free_Product', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        'Free product name verification in view screen'
        Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product names correctly displayed !')

        Mobile.verifyMatch(FreeSKU_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_Qty_Slab + ' : Free Product Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Free_quantity', Scheme_Index))

        'Free product quantity verification in view screen'
        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product Max Piece Qty correctly displayed !')

        GlobalVariable.Qty = (((Double.parseDouble(Slab) + Double.parseDouble(Slab)) + Double.parseDouble(Slab)) + Double.parseDouble(Slab))

        KeywordUtil.logInfo(GlobalVariable.Qty + ' : Sum of entered qty')

        'Remaining qty calculation'
        if (Quantity_variations.get(i) == 'Slab achieved and Remaining Qty') {
            GetProductMin = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Get_Product_Min', 3)

            Buy_Max = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Buy_Max', Scheme_Index)

            double Freeget_Qty_Min = Double.parseDouble(GetProductMin)

            double BuyMax = Double.parseDouble(Buy_Max)

            double pieceqty = GlobalVariable.Qty

            double Calculation1 = Freeget_Qty_Min / BuyMax

            KeywordUtil.logInfo('cal1:' + Calculation1)

            double Calculation2 = pieceqty - BuyMax

            KeywordUtil.logInfo('cal2:' + Calculation2)

            double Remaining_qty = Calculation1 * Calculation2

            KeywordUtil.logInfo('Remaining_qty:' + Remaining_qty)

            double Remainingquantity = Math.floor(Remaining_qty)

            KeywordUtil.logInfo('Remainingquantity:' + Remainingquantity)

            double remaining_slab = Remainingquantity + Freeget_Qty_Min

            KeywordUtil.logInfo('remaining_slab:' + remaining_slab)

            WebUI.verifyEqual(remaining_slab, FreeSKU_Qty_Slab)
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        'Summary screen scheme validations'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Free_Product', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        KeywordUtil.logInfo('Scheme is applied and scheme name is displayed since it is positive flow ')

        Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

        'Free product name verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product name correctly displayed in Summary Screen !')

        'Free product quantity verification in Summary screen'
        Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/26_B11_OTPR_MS_FP(Vari)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

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

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

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

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
    } else if (Quantity_variations.get(i) == 'Less than Slab1') {
        WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_042_CT_1'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_042_CT_2'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_042_CT_3'), [:], FailureHandling.STOP_ON_FAILURE)


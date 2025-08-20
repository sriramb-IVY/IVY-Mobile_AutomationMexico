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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

'Slab1 & Double_slab1 : Positive flow Scheme Validation'

'ANY Condition'

'Buy_Product01'
String sheet_name = '10_B8_MTR_FP_SS(get_AND)'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Flag = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Flag')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    if ((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Double_Slab1')) {
        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Buy_Product01', Scheme_Index)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        Piece_Price = Double.parseDouble(Actual_PiecePrice)

        KeywordUtil.logInfo('Price of the product' + Piece_Price)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

        Slab_1_Min_Qty = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Piece_quantity', Scheme_Index)

        println(GlobalVariable.Qty = Slab_1_Min_Qty)

        //Tot_value = findTestData('Batch_Scheme_Inputs_01/1_OTP_MS_DIS_PER(Value)').getValue('Sales_Value', Scheme_Index)
        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

        Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula.')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        'Scheme should be apply & scheme name should be display since it is positive flow'
        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' :Scheme Name correctly displayed !')

        'Scheme Slab Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' :Scheme Description correctly displayed !')

        'View screen scheme validations for Free Product1'
        Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

        Mobile.delay(2)

        Mobile.takeScreenshot()

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get01_FP_01', Scheme_Index)

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
        Expecetd_FreeSKU_MinQty_Slab = ('Min:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product1 Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab = ('Max:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product1 Max Piece Qty correctly displayed !')

        'View screen scheme validations for Free Product02'
        Mobile.takeScreenshot()

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get01_FP_02', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_2_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        'Free product_2 name verification in view screen'
        Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product2 name correctly displayed !')

        'Free product2 Min and Max quantity verification in view screen'
        Expecetd_FreeSKU_MinQty_Slab = ('Min:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product2 Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab = ('Max:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product2 Max Piece Qty correctly displayed !')

        'View screen scheme validations for Free Product03'
        Mobile.takeScreenshot()

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get02_FP_01', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_3_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        'Free product_3 name verification in view screen'
        Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product3 name correctly displayed !')

        'Free product3 Min and Max quantity verification in view screen'
        Expecetd_FreeSKU_MinQty_Slab = ('Min:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product3 Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab = ('Max:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product3 Max Piece Qty correctly displayed !')

        'View screen scheme validations for Free Product04'
        Mobile.takeScreenshot()

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get02_FP_02', Scheme_Index)

        Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

        FreeSKU_4_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

        FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

        FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

        'Free product_4 name verification in view screen'
        Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeProduct_Slab + ' : Free product4 name correctly displayed !')

        'Free product4 Min and Max quantity verification in view screen'
        Expecetd_FreeSKU_MinQty_Slab = ('Min:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product4 Min Piece Qty correctly displayed !')

        Expecetd_FreeSKU_MaxQty_Slab = ('Max:' + findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index))

        Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product4 Max Piece Qty correctly displayed !')

        'If Get product is in ANY Condition means User should verify which free product get Quantity (Getting four products as get logic as ANY)'

        'Verification for which free product get the Quantity in First ANY condition'
        if (Integer.parseInt(FreeSKU_1_Qty_Slab) != 0) {
            'Get01_FP_01 have piece quantity'
            Mobile.takeScreenshot()

            GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get01_FP_01', Scheme_Index)

            GlobalVariable.FP_Get01 = GlobalVariable.Scheme_Free_SKU

            KeywordUtil.logInfo(GlobalVariable.FP_Get01 + ' : Free Product1 !')

            FreeSKU_1_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

            Mobile.verifyMatch(FreeSKU_1_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(FreeSKU_1_Qty_Slab + ' : Free Product1 Piece Qty correctly displayed !')
        } else {
            'Get01_FP_02 have piece quantity'
            Mobile.takeScreenshot()

            GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get01_FP_02', Scheme_Index)

            GlobalVariable.FP_Get01 = GlobalVariable.Scheme_Free_SKU

            FreeSKU_2_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

            Mobile.verifyMatch(FreeSKU_2_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(FreeSKU_2_Qty_Slab + ' : Free Product1 Piece Qty correctly displayed !')
        }
        
        'If Get product is in ANY Condition means User should verify which free product get Quantity(Getting four products as get logic as ANY)'

        'Verification for which free product get the Quantity in Second ANY condition'
        if (Integer.parseInt(FreeSKU_3_Qty_Slab) != 0) {
            'Get02_FP_01 have piece quantity'
            Mobile.takeScreenshot()

            GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get02_FP_01', Scheme_Index)

            GlobalVariable.FP_Get02 = GlobalVariable.Scheme_Free_SKU

            FreeSKU_1_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

            Mobile.verifyMatch(FreeSKU_1_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(FreeSKU_1_Qty_Slab + ' : Free Product2 Piece Qty correctly displayed !')

            Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
        } else {
            'Get02_FP_02 have piece quantity'
            Mobile.takeScreenshot()

            GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Get02_FP_02', Scheme_Index)

            GlobalVariable.FP_Get02 = GlobalVariable.Scheme_Free_SKU

            FreeSKU_2_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

            Mobile.verifyMatch(FreeSKU_2_Qty_Slab, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(FreeSKU_2_Qty_Slab + ' : Free Product2 Piece Qty correctly displayed !')

            Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        'Summary screen scheme validations'
        Mobile.takeScreenshot()

        'Summary screen scheme validations for Free_Product_1'
        GlobalVariable.Scheme_Free_SKU = GlobalVariable.FP_Get01

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

        'Free product_1 name verification in Summary screen'
        Mobile.verifyMatch(Slab_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab_FreeSku + ' : Free Product_1 name correctly displayed in Summary Screen !')

        'Free product_1 quantity verification in Summary screen'
        GlobalVariable.Qty = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index)

        Mobile.verifyMatch(Slab_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab_FreeSku_PieceQty + ' : Free Product_1 Piece Qty correctly displayed in Summary Screen !')

        'Summary screen scheme validations for Free_Product_2'
        GlobalVariable.Scheme_Free_SKU = GlobalVariable.FP_Get02

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        Slab_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

        'Free product_2 name verification in Summary screen'
        Mobile.verifyMatch(Slab_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab_FreeSku + ' : Free Product name correctly displayed in Summary Screen !')

        'Free product_2 quantity verification in Summary screen'
        GlobalVariable.Qty = findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index)

        Mobile.verifyMatch(Slab_FreeSku_PieceQty, findTestData('Batch_Scheme_Inputs_01/10_B8_MTR_FP_SS(get_AND)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Slab_FreeSku_PieceQty + ' : Free Product_2 Piece Qty correctly displayed in Summary Screen !')

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
    } else if (Quantity_variations.get(i) == 'Less than slab1') {
        WebUI.callTestCase(findTestCase('Product_Mobile/Regression_Schemes/Call Test Cases/RE_SCH_026_CT_1'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}


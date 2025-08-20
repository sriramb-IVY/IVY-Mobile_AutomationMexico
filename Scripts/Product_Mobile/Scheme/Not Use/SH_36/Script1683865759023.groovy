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
import com.sun.net.httpserver.Authenticator.Failure as Failure
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

Mobile.startApplication(GlobalVariable.APKFile, false)

String sheet_name = 'Fixed_Variable_Scheme'

String file_name = 'Mobile Input data'

Scheme_Index = 14

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 
    'Retailer')

for (int i = 0; i < Retailer.size(); i++) {
    Mapped_Retailer = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('MappedRetailer', Scheme_Index)

    UnMapped_Retailer = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('MappedRetailer', 13)

    if ((Retailer.get(i) != 'null') && ((Retailer.get(i) == Mapped_Retailer) || (Retailer.get(i) == UnMapped_Retailer))) {
        Retailer_Index = (i + 1)

        SchemeAppyType = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeAppyType', Scheme_Index)

        RetailerApplyType = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('RetailerApplyType', Retailer_Index)

        if (SchemeAppyType.contains(RetailerApplyType) == true) {
            KeywordUtil.logInfo('For this Retailer "SchemeAppyType" and "RetailerApplyType" was Equal, hence we can validate scheme !')
        } else {
            KeywordUtil.logInfo('SchemeAppyType and RetailerApplyType were not Equal, Hence Scheme should not be applied !')
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue(
                    'Retailer', Retailer_Index)], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 
            5)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

        //slab_1
        'Slab 1'

        'ANY_Logic'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('BuyProduct1', Scheme_Index)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
            5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 
            5)

        Mobile.takeScreenshot()

        Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 
            0)

        Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 
            0)

        Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 
            0)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', 
            Scheme_Index)

        Slab_1_Min_Qty = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

        String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

        length = Slab_Qty.size()

        if (2 == length) {
            GlobalVariable.keypadValue = Slab_Qty.charAt(0)

            Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 5)

            GlobalVariable.keypadValue = Slab_Qty.charAt(1)

            Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 5)
        } else {
            GlobalVariable.keypadValue = Slab_Qty

            Mobile.tap(findTestObject('Mobile/Common/Global_Number_keypad'), 5)
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 
            0)

        Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_1_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total.toString() + ': Slab_1 Sku Total calculated and displayed correctly according the formula.')

        GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('FreeProduct_Slab1', 
            Scheme_Index)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.Scheme_Free_SKU, 
            5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Actual_PiecePrice_FP = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 
            0)

        Actual_CaseSize_FP = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 
            0)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        if (Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 
            5, FailureHandling.OPTIONAL)) {
            CheckBox = Mobile.getAttribute(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_FreeProductQty_CheckBox'), 
                'checked', 1, FailureHandling.STOP_ON_FAILURE)

            KeywordUtil.logInfo(CheckBox + ': Scheme Applied Checkbox ')

            if (CheckBox == 'true') {
                Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 
                    15)

                SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 
                    0)

                SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 
                    0)

                Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Additional Discount Field'), 
                    5)

                Discount_Amt = Discountvalue.replaceAll('Additional Discount : ', '')

                Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeName', 
                        Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

                Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeDescSlab1', 
                        Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

                SKU_Case_Qty = Double.parseDouble(Slab_Qty)

                Web_Addition_dis_Amt = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('Additional_Dis_Amt', 
                    Scheme_Index)

                Calculated_Additional_Discount = (SKU_Case_Qty * Double.parseDouble(Web_Addition_dis_Amt))

                Mobile.verifyEqual(Double.parseDouble(Discount_Amt), Calculated_Additional_Discount, FailureHandling.STOP_ON_FAILURE)

                KeywordUtil.logInfo(Calculated_Additional_Discount.toString() + ' : Additional discount Amount Displayed correctly in Screen Screen !')

                Mobile.takeScreenshot()

                Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

                GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('FreeProduct_Slab1', 
                    Scheme_Index)

                Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 
                    5)

                FreeProduct_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 
                    5)

                FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 
                    5)

                FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 
                    5)

                FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 
                    5)

                Mobile.verifyMatch(FreeProduct_Slab1, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(FreeProduct_Slab1 + ' :Slab 1 Free product correctly displayed !')

                Mobile.verifyMatch(FreeSKU_Qty_Slab1, findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('FreeProduct_Case_Slab1', 
                        Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab 1 Free Product Case Qty correctly displayed !')

                Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue(
                    'FreeProduct_Case_Slab1', Scheme_Index))

                Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

                Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue(
                    'FreeProduct_Case_Slab1_MAX', Scheme_Index))

                Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Slab 1 Free Product Max Case Qty correctly displayed !')

                Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

                Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 
                    0)

                GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('FreeProduct_Slab1', 
                    Scheme_Index)

                Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 
                    5)

                Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 
                    5)

                Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'), 
                    5)

                Slab1_FreeSku_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedTotalAmt(Global)'), 
                    5)

                //			Calculated_Scheme_Amt = Double.parseDouble(Slab1_FreeSku_Amt) + Calculated_Additional_Discount
                //			
                //			KeywordUtil.logInfo(Calculated_Scheme_Amt.toString())
                Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

                Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue(
                        'FreeProduct_Case_Slab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

                Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 
                    15)

                Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

                OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 
                    0)

                Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 
                    0)

                SchemeAmt = Scheme_Amt.replaceAll('- ', '')

                KeywordUtil.logInfo(SchemeAmt)

                Slab1_FreeSku_Amt = ((Double.parseDouble(Actual_CaseSize_FP) * Double.parseDouble(FreeSKU_Qty_Slab1)) * 
                Double.parseDouble(Actual_PiecePrice_FP))

                Calculated_Scheme_Amt = (Slab1_FreeSku_Amt + Calculated_Additional_Discount)

                KeywordUtil.logInfo(Calculated_Scheme_Amt.toString())

                Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Scheme_Amt, FailureHandling.STOP_ON_FAILURE)

                KeywordUtil.logInfo(SchemeAmt + ' : Scheme amount displayed correctly !')

                Mobile.takeScreenshot()

                CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 
                    0)

                TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 
                    0)

                Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Calculated_Scheme_Amt)

                Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
            } else {
                Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 
                    5, FailureHandling.STOP_ON_FAILURE)

                CheckBox = Mobile.getAttribute(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_FreeProductQty_CheckBox'), 
                    'checked', 1, FailureHandling.STOP_ON_FAILURE)

                KeywordUtil.logInfo((' Scheme is present but it not applied, Because checkbox is ' + CheckBox) + ' to check !')

                Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 
                    0)

                Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 
                    15)

                Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

                Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 
                    0)

                SchemeAmt = Scheme_Amt.replaceAll('- ', '')

                KeywordUtil.logInfo(SchemeAmt)

                Mobile.verifyMatch(SchemeAmt, '0', false, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo('Scheme value is :' + SchemeAmt)

                OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 
                    0)

                CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 
                    0)

                TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 
                    0)

                Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

                Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

                Mobile.takeScreenshot()

                KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
            }
        } else {
            Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 
                3)

            KeywordUtil.logInfo('Scheme not apllied for this particular product')

            Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

            Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 
                15)

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 
                0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            Mobile.verifyMatch(SchemeAmt, '0', false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo('Scheme value is :' + SchemeAmt)

            OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 
                0)

            CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 
                0)

            TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 
                0)

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.OPTIONAL)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
        }
        
        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        //Mobile.delay(20, FailureHandling.STOP_ON_FAILURE)
        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 
            0)

        Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

        invoice_ID = Ord_ID.replaceAll('[\']', '')

        KeywordUtil.logInfo(invoice_ID)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2)

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.delay(2)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)


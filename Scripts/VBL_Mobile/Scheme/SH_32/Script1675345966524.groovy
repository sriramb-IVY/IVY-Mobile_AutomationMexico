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
import org.junit.Assert as Assert

not_run: Mobile.startApplication(GlobalVariable.APK_File, false)

not_run: Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_TradeCoverage_Menu'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Fixed_Variable_Scheme'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.VBL.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

for (int i = 0; i < Retailer.size(); i++) {
    if (Retailer.get(i) != 'null') {
        Scheme_Index = 8

        Retailer_Index = (i + 1)

        RetailerApplyType = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('RetailerApplyType', Retailer_Index)

        KeywordUtil.logInfo(RetailerApplyType)

        SchemeAppyType = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeAppyType', Scheme_Index)

        KeywordUtil.logInfo(SchemeAppyType)

        if (SchemeAppyType == GlobalVariable.VariableScheme) {
            KeywordUtil.logInfo('This scripts can validate Variable Scheme')
        } else {
            KeywordUtil.logInfo('This scripts only validation for Variable Scheme')

            Assert.fail()
        }
        
        WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Select_Retailer and Going_OrderInvoice_Screen'), [('RetailerName') : findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('Retailer', Retailer_Index)], FailureHandling.STOP_ON_FAILURE)

        //slab_1
        'Slab 1'
        GlobalVariable.ProductName = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('BuyProduct1', Scheme_Index)

        Mobile.setText(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Product Enter Search'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_BasePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

        Actual_PiecePrice = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        Actual_CaseSize = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Case_Field'), 0)

        GlobalVariable.keypadValue = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

        Slab_1_Min_Qty = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

        String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

        length = Slab_Qty.size()

        if (2 == length) {
            GlobalVariable.keypadValue = Slab_Qty.charAt(0)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)

            GlobalVariable.keypadValue = Slab_Qty.charAt(1)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
        } else {
            GlobalVariable.keypadValue = Slab_Qty

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Reusable Object/Box-Keypad/Global-number_keypad'), 5)
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 5)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        SKU_TOTAL = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_1_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total.toString() + ': Slab_1 Sku Total calculated and displayed correctly according the formula.')

        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Next Btn-OrderInvoiceScreen'), 0)

        WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        'Entering the scheme vlidations'
        if ((RetailerApplyType == GlobalVariable.Variable) || (RetailerApplyType == GlobalVariable.Fixed_DD)) {
            'Scheme Should be applied'
            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 15)

            'Scheme check box Should be enabled!'
            Mobile.takeScreenshot()

            CheckBox = Mobile.getAttribute(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Amount_CheckBox'), 'checked', 1, FailureHandling.STOP_ON_FAILURE)

            KeywordUtil.logInfo(CheckBox + ': Scheme Applied Checkbox ')

            if (CheckBox == 'true') {
                Mobile.takeScreenshot()

                println('Scheme applied check box enabled!')
            } else {
                'Scheme check box is not enabled!'
                Mobile.takeScreenshot()

                Assert.fail()
            }
            
            SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

            SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

            Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)

            Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

            Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

            Discount_value_Sheet = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('DiscounttValueSlab1', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Discountvalue + ' :Slab 1  Discount percentage correctly applied !')

            Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Mobile.takeScreenshot()

            OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Total.toString() + ' : Slab_1 Calculated order amt correctly displayed !')

            Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Discountvalue), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Discountvalue + ' : Slab_1 Calculated Scheme Amount correctly displayed !')

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(Discountvalue))

            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
        } else if (RetailerApplyType == GlobalVariable.Fixed_HT) {
            'Scheme name should be displayed but check box must be unchecked'
            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 5, FailureHandling.STOP_ON_FAILURE)

            'Scheme check box must be unchecked'
            Mobile.takeScreenshot()

            CheckBox = Mobile.getAttribute(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/Scheme_Amount_CheckBox'), 'checked', 1, FailureHandling.STOP_ON_FAILURE)

            KeywordUtil.logInfo(CheckBox + ': Scheme Applied Checkbox ')

            if (CheckBox == 'false') {
                Mobile.takeScreenshot()

                println('Scheme applied check box Not enabled!')
            } else {
                Mobile.takeScreenshot()

                println('Scheme applied check box is enabled!')

                Assert.fail()
            }
            
            SchemeName = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 0)

            SchemeDesc = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeDesc'), 0)

            Discountvalue = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/DiscountValue'), 0)

            Mobile.verifyMatch(SchemeName, findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

            Mobile.verifyMatch(SchemeDesc, findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

            Discount_value_Sheet = findTestData('VBL_Mobile Input Data/Fixed_Variable_Scheme').getValue('DiscounttValueSlab1', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Discountvalue + ' :Slab 1  Discount percentage correctly applied !')

            Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Mobile.takeScreenshot()

            Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            Mobile.verifyMatch(SchemeAmt, '0', false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo('Scheme value is :' + SchemeAmt)

            OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Total.toString() + ' : Slab_1 Calculated order amt correctly displayed !')

            CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
        } else if (RetailerApplyType == GlobalVariable.Fixed) {
            'Scheme should not be applied'
            Mobile.takeScreenshot()

            Mobile.verifyElementNotVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/OrderInvoice/Scheme/SchemeName'), 3)

            KeywordUtil.logInfo('Scheme not apllied for this particular product')

            Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Tap_Next_Btn_ApplyschemeScrn_CT'), [:], FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            Mobile.takeScreenshot()

            Scheme_Amt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            Mobile.verifyMatch(SchemeAmt, '0', false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo('Scheme value is :' + SchemeAmt)

            OrderAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-OrderAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Total.toString() + ' : Slab_1 Calculated order amt correctly displayed !')

            CGST = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt = Mobile.getText(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SliptScreen_TotalAmt'), 0)

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
        } else {
            'Scheme details mismatched or scripts need to update'
            Mobile.takeScreenshot()

            Assert.fail()
        }
        
        Mobile.tap(findTestObject('XXXXXXXXXXXX/Mobile Part/Store_Actvy/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('VBL_Mobile/Re-Usables/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)


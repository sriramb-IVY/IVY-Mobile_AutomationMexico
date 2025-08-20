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

not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Main menu visible'
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

String sheet_name = 'Fixed_Variable_Scheme'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

for (int i = 0; i < Retailer.size(); i++) {
    if (Retailer.get(i) != 'null') {
        Scheme_Index = 5

        Retailer_Index = (i + 1)

        RetailerApplyType = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('RetailerApplyType', Retailer_Index)

        KeywordUtil.logInfo(RetailerApplyType)

        SchemeAppyType = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeAppyType', Scheme_Index)

        KeywordUtil.logInfo(SchemeAppyType)

        if (SchemeAppyType == GlobalVariable.FixedScheme) {
            KeywordUtil.logInfo('This scripts can validate fixed Scheme')
        } else {
            KeywordUtil.logInfo('This scripts only validation for fixed Scheme')

            Assert.fail()
        }
        
        Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('Retailer', Retailer_Index)], FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)
		
        Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

        //slab_1
        'Slab 1'
        GlobalVariable.ProductName = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('BuyProduct1', Scheme_Index)

        Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

        Mobile.takeScreenshot()

        Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

        Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

        Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

        Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

        GlobalVariable.keypadValue = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

        Slab_1_Min_Qty = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

        String Slab_Qty = Integer.parseInt(Slab_1_Min_Qty)

        println(GlobalVariable.Qty = Slab_Qty)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

        Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

        SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

        Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_1_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

        Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Total.toString() + ': Slab_1 Sku Total calculated and displayed correctly according the formula.')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

        if (((RetailerApplyType == GlobalVariable.Fixed_DD) || (RetailerApplyType == GlobalVariable.Fixed_HT)) || (RetailerApplyType == GlobalVariable.Fixed)) {
            'Scheme Should be applied'
            Mobile.takeScreenshot()

            Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

            SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

            SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

            Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)

            Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

            Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

            Discount_value_Sheet = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('DiscounttValueSlab1', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Discountvalue + ' :Slab 1  Discount percentage correctly applied !')

            //Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
			
			WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

            Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

            Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            Mobile.takeScreenshot()

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

            Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Total.toString() + ' : Slab_1 Calculated prder amt correctly displayed !')

            Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

            Actual_MRP = findTestData('Mobile Input Data/Fixed_Variable_Scheme').getValue('BuyProduct1_MRP', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Discountvalue), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Discountvalue + ' : Slab_1 Calculated Scheme Amount correctly displayed !')

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(Discountvalue))

            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !' //	OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)
                )
        } else if (RetailerApplyType == GlobalVariable.Variable) {
            'Scheme should not be applied'
            Mobile.takeScreenshot()

            Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 5)

            KeywordUtil.logInfo('Scheme not apllied for this particular product')

            //Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
			
			WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

            Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

            Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

            Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

            OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

            CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

            TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

            Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

            SchemeAmt = Scheme_Amt.replaceAll('- ', '')

            KeywordUtil.logInfo(SchemeAmt)

            Mobile.verifyMatch(SchemeAmt, '0', false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo('Scheme value is :' + SchemeAmt)

            Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !')
        } else {
            'Scheme details mismatched or scripts need to update'
            Mobile.takeScreenshot()

            Assert.fail()
        }
        
        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

       
    }
}

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)


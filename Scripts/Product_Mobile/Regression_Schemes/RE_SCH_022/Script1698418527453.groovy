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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

String sheet_name = '4_B6_MTS_DP_S1(QTY)'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Flag = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Flag')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    Scheme_Index = (i + 1)

    'Positive flow Scheme Validation for both Slab1 & Slab2'
    not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [:], FailureHandling.STOP_ON_FAILURE)

    'Slab_1 & Slab2'
    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('SKU_Name', Scheme_Index)

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

    Slab_1_Min_Qty = findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Piece_quantity', Scheme_Index)

    println(GlobalVariable.Qty = Slab_1_Min_Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    'Total amount displayed in the screen'
    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

    'Total amount as per the calculation'
    Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

    Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Total.toString() + ' : Product Total Amount calculated and displayed correctly according the formula.')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

    if ((Quantity_variations.get(i) == 'First slab(Equal to Slab Max Limit)') || (Quantity_variations.get(i) == 'More than Slab Max Limit')) {
        'Scheme should be apply & scheme name should be display since it is positive flow'
        Mobile.takeScreenshot()

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        if (Quantity_variations.get(i) == 'More than Slab Max Limit') {
            Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

            Mobile.delay(3)

            Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

            Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 1, FailureHandling.OPTIONAL)

            Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/Resource_1/Slab_limit_Text'), 15)

            Limit_text = Mobile.getText(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/Resource_1/Slab_limit_Text'), 0)

            KeywordUtil.logInfo(Limit_text.toString() + ' : Text displaying as per the Max limit value') //			Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValueRange'), 0)
            //			Discount_value_Sheet = findTestData('Batch_Scheme_Inputs_01/3_B6_MTS_Value_S2_DV_Brand').getValue('Discount_Value_Range', Scheme_Index)
            //			
            //			Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)
            //			
        }
        
        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        DiscountPerc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' :Scheme Name correctly displayed !')

        'Scheme Slab Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' :Scheme Description correctly displayed !')

        Discount_Perc_Sheet = findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Discount_percentage', Scheme_Index)

        Max_limit = findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Max_Limit', Scheme_Index)

        'Scheme Discperc Verification'
        Mobile.verifyEqual(Double.parseDouble(DiscountPerc), Double.parseDouble(Discount_Perc_Sheet), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(DiscountPerc + ' :Discount percentage correctly applied !')

        Mobile.delay(3)

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

        Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        Mobile.takeScreenshot()

        'Summary screen'
        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

        Mobile.takeScreenshot()

        'Validation in info screen'
        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

        Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt = Scheme_Amt.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt)

        CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

        TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

        'scheme amount calculation in info screen'
        Calculated_Sch_Amt = ((Total * Double.parseDouble(Discount_Perc_Sheet)) / 100)

        KeywordUtil.logInfo(Calculated_Sch_Amt.toString())

        Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Calculated_Sch_Amt)

        if ((Quantity_variations.get(i) == 'First slab(Equal to Slab Max Limit)') || (Quantity_variations.get(i) == 'Second slab(Equal to Slab Max Limit)')) {
            Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Sch_Amt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' : Calculated Scheme Amount correctly displayed and Equal to Slab Max Limit!')

            'Total amount validation in info screen'
            Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :Total amount in split screen displayed correctly !')

            Mobile.delay(2)
        } else if (Quantity_variations.get(i) == 'More than Slab Max Limit') {
            Mobile.verifyNotEqual(Double.parseDouble(SchemeAmt), Calculated_Sch_Amt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' : Calculated Scheme Amount correctly displayed and More than Slab Max Limit !')

            Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Max_limit), FailureHandling.STOP_ON_FAILURE)

            KeywordUtil.logInfo(Max_limit.toString() + ' : Scheme Amount correctly displayed as same as the max limit !')

            'Total amount validation in info screen'
            Mobile.verifyNotEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :Total amount in split screen displayed correctly !')

            Mobile.delay(2)
        }
        
        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 3)

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

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
    } else if ((Quantity_variations.get(i) == 'Less than slab1') && (Flag.get(i) == 'Y')) {
        'negative scenario scheme validation'

        'Scheme will not apply since it is negative scenario'
        Mobile.takeScreenshot()

        Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        Mobile.takeScreenshot()

        Mobile.delay(3)
		
		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

        //Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

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

        Discount_Perc_Sheet = findTestData('Batch_Scheme_Inputs_01/4_B6_MTS_DP_S1(QTY)').getValue('Discount_percentage', Scheme_Index)

        Calculated_Sch_Amt = ((Total * Double.parseDouble(Discount_Perc_Sheet)) / 100)

        KeywordUtil.logInfo(Calculated_Sch_Amt.toString())

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Sch_Amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' :Calculated Scheme Amount correctly displayed !')

        Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Calculated_Sch_Amt)

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

        Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

        Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

        WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

Mobile.delay(2)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


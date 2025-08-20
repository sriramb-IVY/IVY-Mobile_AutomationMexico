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

Mobile.startApplication(GlobalVariable.APKFile, false)

String sheet_name = 'Empties_Scheme_OTP_EM_SL_QTY_FP'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Scheme_Name = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Scheme_Name')

ArrayList<String> Salable_SKU_Name = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Salable_SKU_Name')

ArrayList<String> Empty_SKU_Name1 = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Empty_SKU_Name1')

ArrayList<String> Buy_Quantity = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Buy_Quantity')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

ArrayList<String> Free_Product_Get_Qty = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Free_Product_Get_Qty')

ArrayList<String> Get_Logic = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Get_Logic')

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    GlobalVariable.ProductName = Salable_SKU_Name.get(i)

    GlobalVariable.Scheme_Free_SKU = Empty_SKU_Name1.get(i)

    Scheme_Index = (i + 1)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

    'Buy_SKU_1'
    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Salable_SKU_Name', Scheme_Index)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

    Mobile.delay(2)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

    Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Piece_Price = Double.parseDouble(Actual_PiecePrice)

    GlobalVariable.label = Actual_PiecePrice

    KeywordUtil.logInfo('Price of the product' + Piece_Price)

    if (Get_Logic.get(i) == 'Salable_SKU') {
        Mobile.comment('Calculation for Free Product waive off')

        Freeproduct_qty = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Free_Product_Get_Qty', Scheme_Index)

        Free_Product_Amount = (Double.parseDouble(Freeproduct_qty) * Double.parseDouble(GlobalVariable.label))

        KeywordUtil.logInfo(Free_Product_Amount + ' : Free Product Amount')

        GlobalVariable.Waiveoff_amt = Free_Product_Amount

        Mobile.takeScreenshot()
    }
    
    Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

    Slab_1_Min_Qty = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Buy_Quantity', Scheme_Index)

    println(GlobalVariable.Qty = Slab_1_Min_Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2)

    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

    'total value calculation'
    Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

    'verification of total amount'
    Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Total.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula.')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)
	
	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_Batch_Screen'), [:], FailureHandling.STOP_ON_FAILURE)
	

    if (Quantity_variations.get(i) == 'Slab1') {
        Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Stock and Order/Empty_Product_Details_Title'), 15)

        GlobalVariable.label = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Empty_SKU_Name1', Scheme_Index)

        Mobile.scrollToText(GlobalVariable.label, FailureHandling.STOP_ON_FAILURE)

        Empty_price = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_price_get'), 0)

        Empty_liable_qty = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Liable_qty_get'), 0)

        Return_Cal = (Double.parseDouble(Empty_liable_qty) * Double.parseDouble(Empty_price))

        KeywordUtil.logInfo(Return_Cal + ' : Empty  Return Amount')

        Empty_Return_Amount = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Total_Empty_Return_Value'), 0)

        Mobile.verifyEqual(Double.parseDouble(Empty_Return_Amount), Return_Cal, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 2)

        Mobile.takeScreenshot()

        'Positive flow Scheme Validation for single slab'
        Mobile.delay(2)

        Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

        SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

        DiscountAmount = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)

        'Scheme Name Verification'
        Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeName + ' : Slab Scheme Name correctly applied !')

        'Slab Description Verification'
        Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeDesc + ' : Scheme Description correctly displayed !')

        Mobile.verifyEqual(Double.parseDouble(DiscountAmount), GlobalVariable.Waiveoff_amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(DiscountAmount + ' :Slab Waive off amount correctly applied !')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
		
		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        'Summary screen scheme validations'
        Mobile.comment('Validation for Free product amount applied in salable product')

        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Salable_SKU_Name', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_OTP_EM_SL_QTY_FP').getValue('Salable_SKU_Name', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_1 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_1 = Scheme_Amt_1.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt_1 + ' : Scheme Waive off Amount is displayed properly for Salable Product !')

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt_1), GlobalVariable.Waiveoff_amt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt = Scheme_Amt.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt)

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(DiscountAmount), FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(SchemeAmt + ' : Slab Scheme amount in split screen displayed correctly !')

        CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

        TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

        Empty_Amt = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Return_value_get_info_screen'), 0)

        KeywordUtil.logInfo('Empty Return amount info screen : ' + Empty_Amt)

        Mobile.verifyEqual(Double.parseDouble(Empty_Amt), Double.parseDouble(Empty_Return_Amount), FailureHandling.STOP_ON_FAILURE)

        Calculated_TotalAmt = (((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) + Double.parseDouble(Empty_Amt)) - Double.parseDouble(SchemeAmt))

        Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab Total amount in split screen displayed correctly !')

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Generate_OrderInvoice'), [:], FailureHandling.STOP_ON_FAILURE)
		
    } else if (Quantity_variations.get(i) == 'less than slab1') {
        'Negative Scheme validation'
       

        Mobile.takeScreenshot()

        Mobile.delay(2)

        'Scheme will not apply since it is negative scenario'
        Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
		
		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

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

        Empty_Amt = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Return_value_get_info_screen'), 0)

        KeywordUtil.logInfo('Empty Return amount info screen : ' + Empty_Amt)

        Calculated_TotalAmt = (((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) + Double.parseDouble(Empty_Amt)) - Double.parseDouble(SchemeAmt))

        Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :  Slab_1 Total amount in split screen displayed correctly !')

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Generate_OrderInvoice'), [:], FailureHandling.STOP_ON_FAILURE)
		
		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)
		
		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

        //WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
    }
}

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


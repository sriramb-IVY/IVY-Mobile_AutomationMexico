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

String sheet_name = 'Empties_Scheme_MTS_DP'

String file_name = 'Batch_Scheme_Inputs_01'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Scheme_Name = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Scheme_Name')

ArrayList<String> Salable_SKU_Name = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Salable_SKU_Name1')

ArrayList<String> Empty_SKU_Name1 = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Empty_SKU_Name1')

ArrayList<String> Buy_Quantity = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Buy_Quantity')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAll_Batch_Scheme_Inputs_01_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
    GlobalVariable.ProductName = Salable_SKU_Name.get(i)

    GlobalVariable.Scheme_Free_SKU = Empty_SKU_Name1.get(i)

    Scheme_Index = (i + 1)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

    'Buy_SKU_1'
    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Salable_SKU_Name1', Scheme_Index)

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

    Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

    Slab_1_Min_Qty = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Buy_Quantity', Scheme_Index)

    println(GlobalVariable.Qty = Slab_1_Min_Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 5)

    SKU_TOTAL1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('SKU1 Total Value ' + SKU_TOTAL1)

    Mobile.delay(2)

    'Buy_SKU_2'
    Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

    GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Salable_SKU_Name2', Scheme_Index)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

    Mobile.delay(2)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_BasePrice1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

    Actual_PiecePrice1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

    Piece_Price1 = Double.parseDouble(Actual_PiecePrice1)

    KeywordUtil.logInfo('Price of the product' + Piece_Price1)

    Actual_CaseSize1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

    Slab_1_Min_Qty_1 = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Buy_Quantity1', Scheme_Index)

    println(GlobalVariable.Qty = Slab_1_Min_Qty_1)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2)

    SKU_TOTAL2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

    KeywordUtil.logInfo('SKU2 Total Value ' + SKU_TOTAL2)

    SKU_TOTAL = (Double.parseDouble(SKU_TOTAL1) + Double.parseDouble(SKU_TOTAL2))

    'total value calculation'
    Total = ((Double.parseDouble(GlobalVariable.Qty) * Piece_Price) + (Double.parseDouble(Slab_1_Min_Qty_1) * Piece_Price1))

    'verification of total amount'
    Mobile.verifyEqual(SKU_TOTAL, Total, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Total.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula.')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)
	
	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_Batch_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

    if ((((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab2')) || (Quantity_variations.get(i) == 'Slab1&2')) || (Quantity_variations.get(i) == 'Double of Slab2')) {
		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)
		
		 Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Stock and Order/Empty_Product_Details_Title'), 15)

        GlobalVariable.label = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Empty_SKU_Name1', Scheme_Index)

        Mobile.scrollToText(GlobalVariable.label, FailureHandling.STOP_ON_FAILURE)

        Empty_price_1 = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_price_get'), 0)

        Empty_liable_qty_1 = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Liable_qty_get'), 0)

        Return_Cal_1 = (Double.parseDouble(Empty_liable_qty_1) * Double.parseDouble(Empty_price_1))

        KeywordUtil.logInfo(Return_Cal_1 + ' : Empty  Return Amount for first sku')

        'Empty SKU 2'
        GlobalVariable.label = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Empty_SKU_Name2', Scheme_Index)

        Mobile.scrollToText(GlobalVariable.label, FailureHandling.STOP_ON_FAILURE)

        Empty_price_2 = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_price_get'), 0)

        Empty_liable_qty_2 = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Empty_Liable_qty_get'), 0)

        Return_Cal_2 = (Double.parseDouble(Empty_liable_qty_2) * Double.parseDouble(Empty_price_2))

        KeywordUtil.logInfo(Return_Cal_2 + ' : Empty  Return Amount for second SKU')

        Return_Cal = (Return_Cal_1 + Return_Cal_2)

        KeywordUtil.logInfo(Return_Cal + ' : Total Empty  Return Amount')

        Empty_Return_Amount = Mobile.getText(findTestObject('Object Repository/Mobile/Seller_2/Stock and Order/Total_Empty_Return_Value'), 0)

        Mobile.verifyEqual(Double.parseDouble(Empty_Return_Amount), Return_Cal, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 2)

        Mobile.takeScreenshot()
		
		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)
		
		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 2, FailureHandling.OPTIONAL)

        'Positive flow Scheme Validation for slabs'
        Mobile.delay(2)

        if (((Quantity_variations.get(i) == 'Slab1') || (Quantity_variations.get(i) == 'Slab2')) || (Quantity_variations.get(i) == 'Double of Slab2')) {
            Mobile.comment('Validation for Discount Percentage')

            Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

            SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

            SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

            Discountpercentage = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc'), 0)

            'Scheme Name Verification'
            Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' : Slab Scheme Name correctly applied !')

            'Slab Description Verification'
            Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' : Scheme Description correctly displayed !')

            Dis_Perc = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('DiscountPercentage', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(Discountpercentage), Double.parseDouble(Dis_Perc), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Discountpercentage + ' :Discount percentage amount correctly displayed !')

            GlobalVariable.label = Discountpercentage
        } else if (Quantity_variations.get(i) == 'Slab1&2') {
            Mobile.comment('Calculation for Free Product waive off')

            Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

            'Validation for Slab2'
            SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

            SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

            Discountpercentage1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc'), 0)

            'Scheme Name Verification'
            Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' : Slab2 Scheme Name correctly applied !')

            'Slab Description Verification'
            Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Slab_Description_MTS', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' : Scheme Slab2 Description correctly displayed !')

            Dis_Perc1 = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('DiscountPercentage1', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(Discountpercentage1), Double.parseDouble(Dis_Perc1), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(Dis_Perc1 + ' :Slab2 Discount percentage correctly displayed !')

            'Slab1 also applied'
            SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName_2'), 0)

            SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc_2'), 0)

            Discountpercentage2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc_2'), 0)

            Mobile.verifyMatch(SchemeName, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeName + ' :Slab_1 Scheme Name correctly applied !')

            Mobile.verifyMatch(SchemeDesc, findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            KeywordUtil.logInfo(SchemeDesc + ' :Slab_1 Scheme Description correctly displayed !')

            Dis_Perc2 = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('DiscountPercentage', Scheme_Index)

            Mobile.verifyEqual(Double.parseDouble(Discountpercentage2), Double.parseDouble(Dis_Perc2), FailureHandling.STOP_ON_FAILURE)

            Mobile.takeScreenshot()

            Discountpercentage_1 = (Double.parseDouble(Discountpercentage1) + Double.parseDouble(Discountpercentage2))

            String Discountpercentage = Discountpercentage_1

            GlobalVariable.label = Discountpercentage
        }
        
        'Prodcut 1 discount Calculation'
        Perc = (Double.parseDouble(GlobalVariable.label) / 100)

        KeywordUtil.logInfo(Perc + ' :calculated percentage')

        Discount1 = (Perc * Return_Cal_1)

        KeywordUtil.logInfo(Discount1 + ' :Discount calculated for product 1 !')

        'Prodcut 2 discount Calculation'
        Discount2 = (Perc * Return_Cal_2)

        KeywordUtil.logInfo(Discount2 + ' :Discount calculated for product 2 !')

        Discount_amt = (Discount1 + Discount2)

        KeywordUtil.logInfo(Discount_amt + ' :calculated Total Discount amount !')

        Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
		
		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

        'Summary screen scheme validations'
        Mobile.comment('Validation for Free product amount applied in salable product')

        'Product1'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Empty_SKU_Name1', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Empty_SKU_Name1', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_1 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_1 = Scheme_Amt_1.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt_1 + ' : Scheme Amount is displayed properly for Empty Product !')

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt_1), Discount1, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        'Product2'
        GlobalVariable.Scheme_Free_SKU = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Empty_SKU_Name2', Scheme_Index)

        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

        GlobalVariable.ProductName = findTestData('Batch_Scheme_Inputs_01/Empties_Scheme_MTS_DP').getValue('Empty_SKU_Name2', Scheme_Index)

        Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_BuyProductBasedTotalAmt(Global_Index_1)'), 0)

        Scheme_Amt_2 = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt_2 = Scheme_Amt_2.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt_2 + ' :  Scheme Amount is displayed properly for Empty Product 2!')

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt_2), Discount2, FailureHandling.STOP_ON_FAILURE)

        Mobile.takeScreenshot()

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

        'Validation in Summary Screen Info screen'
        Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

        Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

        OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

        Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

        SchemeAmt = Scheme_Amt.replaceAll('- ', '')

        KeywordUtil.logInfo(SchemeAmt)

        Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Discount_amt, FailureHandling.STOP_ON_FAILURE)

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
//        Mobile.verifyElementVisible(findTestObject('Mobile/Seller_2/Stock and Order/Empty_Product_Details_Title'), 15)
//
//        Mobile.delay(2)
//
//        Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 2)

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


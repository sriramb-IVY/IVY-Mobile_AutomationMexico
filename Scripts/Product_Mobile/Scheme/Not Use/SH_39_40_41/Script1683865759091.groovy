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
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint

Mobile.startApplication(GlobalVariable.APKFile, false)

Scheme_Index = 1

SchemeAppyNo = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('SchemeAppyNo', Scheme_Index)

Expected_SchemeAppyNo = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Expected_SchemeAppyNo', 
    Scheme_Index)

if (Mobile.verifyMatch(SchemeAppyNo, Expected_SchemeAppyNo, false, FailureHandling.STOP_ON_FAILURE)) {
    KeywordUtil.logInfo('For this Retailer "SchemeAppyNo" and "Expected_SchemeAppyNo" was Equal, hence we can validate scheme !')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue(
                'Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/Common/Icon_FunnelFilter'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Apply'), 5)

    Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 2, FailureHandling.OPTIONAL)

    //slab_1
    'Slab 1'
    GlobalVariable.ProductName = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('BuyProduct1', 
        Scheme_Index)

    Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 
        5)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

    Mobile.takeScreenshot()

    Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 
        0)

    Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 
        0)
	
	BuyProduct_PiecePrice = Actual_PiecePrice

    Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 
        0)

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

    GlobalVariable.keypadValue = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Slab_1_Min_Qty', 
        Scheme_Index)

    Slab_1_Min_Qty = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Slab_1_Min_Qty', Scheme_Index)

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
	
	'get free product piece price'
	
	GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Slab1',Scheme_Index)
	
	GlobalVariable.ProductName = GlobalVariable.Scheme_Free_SKU
	
	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
		5)

	Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

	Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

	Mobile.takeScreenshot()

	Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'),
		0)

	Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'),
		0)
	
	
	free_Sku_Qty = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1',Scheme_Index)
	
	Free_Sku_total_value = (Double.parseDouble(Actual_PiecePrice) * Double.parseDouble(Actual_CaseSize)) * Double.parseDouble(free_Sku_Qty)
	
	Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)
	
	Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
	

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 5, 
        FailureHandling.OPTIONAL)

    Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

    SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

    SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

    Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/Additional Discount Field'), 
        5)

    Discount_Amt = Discountvalue.replaceAll('Additional Discount : ', '')

    Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('SchemeName', 
            Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied !')

    Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('SchemeDescSlab1', 
            Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed !')

    Web_Addition_dis_Amt = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('Additional_Dis_Amt', 
        Scheme_Index)

    SKU_Case_Qty = Double.parseDouble(Slab_Qty)

    Calculated_Additional_Discount = (SKU_Case_Qty * Double.parseDouble(Web_Addition_dis_Amt))

    Mobile.verifyEqual(Double.parseDouble(Discount_Amt), Calculated_Additional_Discount, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(Calculated_Additional_Discount.toString() + ' : Additional discount Amount Displayed correctly in Screen Screen !')

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

  

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

    Mobile.verifyMatch(FreeSKU_Qty_Slab1, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', 
            Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab 1 Free Product Case Qty correctly displayed !')

    Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', 
        Scheme_Index))

    Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' :Slab 1 Free Product Min Case Qty correctly displayed !')

    Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1_MAX', 
        Scheme_Index))

    Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Slab 1 Free Product Max Case Qty correctly displayed !')

    Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

    Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

    GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Slab1', 
        Scheme_Index)

    Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 
        5)

    Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 
        5)

    Slab1_FreeSku_CaseQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'), 
        5)

    Slab1_FreeSku_Tax_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedTotalAmt(Global)'), 
        5)

    KeywordUtil.logInfo(Slab1_FreeSku_Tax_Amt)

    Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Slab1_FreeSku + ' :Slab 1 Free Product  correctly displayed in Summary Screen !')

    Mobile.verifyMatch(Slab1_FreeSku_CaseQty, findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('FreeProduct_Case_Slab1', 
            Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Slab1_FreeSku_CaseQty + ' :Slab 1 Free Product Case Qty correctly displayed in Summary Screen !')

    //			Slab1_FreeSku_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedCaseQty(Global)'),
    //				5)
    //			
    Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 
        15)

    Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

    OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)
	
	Mobile.verifyEqual(Double.parseDouble(OrderAmt), Double.parseDouble(SKU_TOTAL), FailureHandling.STOP_ON_FAILURE)
	
	Mobile.takeScreenshot()
	
	KeywordUtil.logInfo(OrderAmt + ' : Order value in split screen displayed correctly !')
	
	

    Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 
        0)

    SchemeAmt = Scheme_Amt.replaceAll('- ', '')

    KeywordUtil.logInfo(SchemeAmt)

    Mobile.verifyEqual(Double.parseDouble(SchemeAmt), 0, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo(SchemeAmt + ' : Scheme amount displayed Should be zero because it is credit not retailer !')

    Mobile.takeScreenshot()

    CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

    TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

    Calculated_TotalAmt = (Double.parseDouble(OrderAmt) + Double.parseDouble(CGST))

    Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly !') //	OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)
       

    Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

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
	
	'Slab 1'
	BuyProduct = findTestData('Mobile Input Data/RetailerTypeCreditNote_Scheme').getValue('BuyProduct1', Scheme_Index)
	
    exlpath = 'DDF\\Web Input Data\\Web Input Data.xlsx'

    Sheetname = 'Sales_Order_Invoice_View'

    workbook01 = ExcelKeywords.getWorkbook(exlpath)

    sheet1 = ExcelKeywords.getExcelSheet(workbook01, Sheetname)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 0, BuyProduct)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 1, BuyProduct_PiecePrice)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 2, Slab_Qty)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 3, SKU_TOTAL)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 4, CGST)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 5, TotalAmt)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 6, invoice_ID)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 7, GlobalVariable.RetailerName)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 8, Actual_CaseSize)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 9, SchemeName)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 10, SchemeDesc)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 12, Discount_Amt)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 13, SchemeAmt)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 14, GlobalVariable.Scheme_Free_SKU)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 15, Slab1_FreeSku_CaseQty)

    ExcelKeywords.setValueToCellByIndex(sheet1, 5, 16, Free_Sku_total_value)
	
	ExcelKeywords.setValueToCellByIndex(sheet1, 5, 17, Slab1_FreeSku_Tax_Amt)

    ExcelKeywords.saveWorkbook(exlpath, workbook01)

    Mobile.delay(1)

    Mobile.swipe(0, 100, 0, 400, FailureHandling.OPTIONAL)

    Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

   WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)


Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.callTestCase(findTestCase('Product_Mobile/Scheme/Reusable Cases/DB_Sales_Invoice_FreeProduct(CN)'), [:], FailureHandling.STOP_ON_FAILURE)
}


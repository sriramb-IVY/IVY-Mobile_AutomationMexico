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
import java.math.BigDecimal as BigDecimal
import java.math.RoundingMode as RoundingMode
import java.text.DecimalFormat as DecimalFormat
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil

 //Mobile.startApplication(GlobalVariable.APKFile, false)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Main menu visible'
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Scheme_Index = 3

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/OTPR').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)



//slab_1
'Slab_1'
GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty = Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Total.toString() + ' : Slab1 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab 1 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab 1 Scheme Description correctly displayed !')

Discount_value_Sheet = findTestData('Mobile Input Data/OTPR').getValue('DiscounttValueSlab1', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Discountvalue + ' :Slab 1  Discount percentage correctly applied !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeAmt + ' : Slab 1 Scheme amount in split screen displayed correctly !')

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab 1 Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

//2_Slab
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)



GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 5)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 5)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

Mobile.tap(findTestObject('Mobile/Common/Back Arrow-Keypad'), 5, FailureHandling.OPTIONAL)

Slab_2_Min_Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

GlobalVariable.Qty = Slab_2_Min_Qty

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(Slab_2_Min_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Total.toString() + ': Slab 2 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab 2 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :Slab 2  Scheme Description correctly displayed !')

Discount_value_Sheet = findTestData('Mobile Input Data/OTPR').getValue('DiscounttValueSlab2', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Discountvalue + ' : Slab 2 Discount percentage correctly applied !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeAmt + ' : Slab 2 Scheme amount in split screen displayed correctly !')

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab 2Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

//High_Slab
'High_Slab'
Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName, 5)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 5)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 5)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 5)

Slab_2_Min_Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_2_Min_Qty', Scheme_Index)

String HighSlab_Qty = Integer.parseInt(Slab_2_Min_Qty) * 2

println(GlobalVariable.Qty = HighSlab_Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(HighSlab_Qty)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Total.toString() + ': Slab 2 Sku Total Amount calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

Discountvalue = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountValue'), 0)

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : High Slab Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' :High Slab  Scheme Description correctly displayed !')

Discount_value = ((Double.parseDouble(SchemeAmt) / Double.parseDouble(Slab_2_Min_Qty)) * Double.parseDouble(HighSlab_Qty))

calculated_Discount_value = new BigDecimal(Discount_value).setScale(2, RoundingMode.DOWN)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), calculated_Discount_value, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

println(Discountvalue + ' : High Slab Discount percentage correctly applied !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'), 0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo(SchemeAmt)

calculated_Discount_value = new BigDecimal(Discount_value).setScale(2, RoundingMode.HALF_DOWN)

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), calculated_Discount_value, FailureHandling.STOP_ON_FAILURE)

println(calculated_Discount_value + ' : High Slab Scheme amount displayed correctly in summary screen !')

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : High Slab Total amount in split screen displayed correctly !')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

not_run: Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

not_run: id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'), 0)

not_run: Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')

not_run: invoice_ID = Ord_ID.replaceAll('[\']', '')

not_run: KeywordUtil.logInfo(invoice_ID)

not_run: Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)

not_run: Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

not_run: Mobile.delay(2)

not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

not_run: Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

not_run: Mobile.delay(2)

not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

not_run: Mobile.delay(2)

not_run: WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


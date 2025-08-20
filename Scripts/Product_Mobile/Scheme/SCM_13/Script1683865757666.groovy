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

//String sheet_name = 'Scheme_CriteriaMapping'
//
//String file_name = 'Mobile Input data'
//
//ArrayList<String> Scheme_Name = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'SchemeName')
//
//for (int a = 12; a < 13; a++) {
//    GlobalVariable.index = a
//
//    GlobalVariable.Scheme_name = Scheme_Name.get(a)
//
//    WebUI.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DivisionLogin'), [:], FailureHandling.STOP_ON_FAILURE)
//
//    WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Scheme/Retailer Master/RM_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)
//
//    WebUI.refresh()
//
//    WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Scheme/Scheme Request/SC_RQ_CT_1'), [:], FailureHandling.STOP_ON_FAILURE)
//
//    String Expected_channel = findTestData('Data Files/Mobile Input Data/Scheme_CriteriaMapping').getValue('Channel_name_mapping', a + 1)
//
//    String scheme_mapped_channel = GlobalVariable.Channel_name
//
//    WebUI.verifyMatch(scheme_mapped_channel.substring(0, 13), Expected_channel, false)
//
//    WebUI.closeBrowser()
not_run: Mobile.startApplication(GlobalVariable.APKFile, false)

if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
    'Main menu visible'
} else {
    'App relaunch'
    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Scheme_Index = 13

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Scheme_CriteriaMapping').getValue('Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)


//slab_1
'Slab_1'
GlobalVariable.ProductName = findTestData('Mobile Input Data/Scheme_CriteriaMapping').getValue('BuyProduct1', Scheme_Index)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Search_Product'), [('ProductName') :  GlobalVariable.ProductName], FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'), 0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'), 0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'), 0)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

Qty = findTestData('Mobile Input Data/Scheme_CriteriaMapping').getValue('Slab_1_Min_Qty', Scheme_Index)

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

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/Scheme_CriteriaMapping').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab 1 Scheme Name correctly applied !')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/Scheme_CriteriaMapping').getValue('SchemeDescSlab1', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab 1 Scheme Description correctly displayed !')

Discount_value_Sheet = findTestData('Mobile Input Data/Scheme_CriteriaMapping').getValue('DiscounttValueSlab1', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(Discountvalue), Double.parseDouble(Discount_value_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Discountvalue + ' :Slab 1  Discount percentage correctly applied !')

//Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)


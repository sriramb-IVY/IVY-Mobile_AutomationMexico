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

import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint

//Mobile.startApplication(GlobalVariable.APKFile, false)


if (Mobile.verifyElementExist(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)) {
	'Main menu visible'
} else {
	'App relaunch'
	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)
}

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

Scheme_Index = 1

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/OTPR').getValue(
			'Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)



Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

Mobile.comment('Validation for when we buy first product for schemes ')

'Slab 1 for first Buy Product'

GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
	5)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'),
	0)

Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'),
	0)

Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'),
	0)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2)

SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'),
	0)

Total = ((Double.parseDouble(Actual_CaseSize) * Double.parseDouble(GlobalVariable.keypadValue)) * Double.parseDouble(Actual_PiecePrice))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Total.toString() + ': Slab_1 Sku Total calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

DiscountPerc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc'),
	0)

Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName + ' : Slab_1 Scheme Name correctly applied for fist product!')

Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab1', Scheme_Index), false,
	FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc + ' : Slab_1 Scheme Description correctly displayed for first product!')

Discount_Perc_Sheet = findTestData('Mobile Input Data/OTPR').getValue('DiscountPercSlab1', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(DiscountPerc), Double.parseDouble(Discount_Perc_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(DiscountPerc + ' : Slab_1 Discount percentage correctly applied for first product !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
if (Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Empty_Product_Details_Title'),
	3, FailureHandling.OPTIONAL)) {
	
		Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 4, FailureHandling.OPTIONAL)
		Mobile.delay(2)
	
	}

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'),
	15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt), Total, FailureHandling.STOP_ON_FAILURE)

Scheme_Amt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'),
	0)

SchemeAmt = Scheme_Amt.replaceAll('- ', '')

KeywordUtil.logInfo('Scheme amt : '+ SchemeAmt)

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Sch_Amt = ((Total * Double.parseDouble(Discount_Perc_Sheet)) / 100)

KeywordUtil.logInfo(Calculated_Sch_Amt.toString())

Mobile.verifyEqual(Double.parseDouble(SchemeAmt), Calculated_Sch_Amt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_Sch_Amt.toString() + ' : Slab_1 Calculated Scheme Amount correctly displayed for first product!')

Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Calculated_Sch_Amt)

Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Slab_1 Total amount in split screen displayed correctly for first product!')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-EditIcon'), 0)

Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0,
	FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3, FailureHandling.OPTIONAL)

Mobile.comment('Validation for when we clear first product qty and add second product and check the schemes in apply screen,summary screen')
'Slab 1'
GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct1', Scheme_Index)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
	5)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5,
	FailureHandling.OPTIONAL)

Mobile.takeScreenshot()

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Vertical keypad-BackSpace'), 0,FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Object Repository/Mobile/Common/Vertical keypad-BackSpace'), 0,FailureHandling.OPTIONAL)


Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2)
Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0,
	FailureHandling.OPTIONAL)

Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 3, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('Mobile Input Data/OTPR').getValue('BuyProduct2', Scheme_Index)

Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
	5)

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'), 5)

Mobile.takeScreenshot()

Actual_BasePrice_Second_product = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'),
	0)

Actual_PiecePrice_Second_product = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'),
	0)

Actual_CaseSize_Second_product = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'),
	0)

Mobile.tap(findTestObject('Mobile/OrderInvoice/Case_Field'), 0)

GlobalVariable.Qty = findTestData('Mobile Input Data/OTPR').getValue('Slab_1_Min_Qty', Scheme_Index)

println(GlobalVariable.Qty)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_CASE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

Mobile.delay(2)

SKU_TOTAL_Second_product = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'),
	0)

Total_Second_product = ((Double.parseDouble(Actual_CaseSize_Second_product) * Double.parseDouble(GlobalVariable.keypadValue)) * Double.parseDouble(Actual_PiecePrice_Second_product))

Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL_Second_product), Total_Second_product, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Total_Second_product.toString() + ': Slab_1 Sku Total calculated and displayed correctly according the formula.')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

SchemeName_Second_product = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

SchemeDesc_Second_product = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

DiscountPerc_Second_product = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/DiscountPerc'),
	0)

Mobile.verifyMatch(SchemeName_Second_product, findTestData('Mobile Input Data/OTPR').getValue('SchemeName', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeName_Second_product + ' : Slab_1 Scheme Name correctly applied for second product!')

Mobile.verifyMatch(SchemeDesc_Second_product, findTestData('Mobile Input Data/OTPR').getValue('SchemeDescSlab1', Scheme_Index), false,
	FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(SchemeDesc_Second_product + ' : Slab_1 Scheme Description correctly displayed for second product!')

Discount_Perc_Sheet = findTestData('Mobile Input Data/OTPR').getValue('DiscountPercSlab1', Scheme_Index)

Mobile.verifyEqual(Double.parseDouble(DiscountPerc_Second_product), Double.parseDouble(Discount_Perc_Sheet), FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(DiscountPerc_Second_product + ' : Slab_1 Discount percentage correctly applied for second product !')

Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)
if (Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Empty_Product_Details_Title'),
	3, FailureHandling.OPTIONAL)) {
	
		Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 4, FailureHandling.OPTIONAL)
		Mobile.delay(2)
	
	}

Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'),
	15)

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-InfoIcon'), 0)

OrderAmt_Second_product = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-OrderAmt'), 0)

Mobile.verifyEqual(Double.parseDouble(OrderAmt_Second_product), Total_Second_product, FailureHandling.STOP_ON_FAILURE)

Scheme_Amt_Second_product = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen_SCHEME Amt'),
	0)

SchemeAmt_Second_product = Scheme_Amt_Second_product.replaceAll('- ', '')

KeywordUtil.logInfo('Scheme amt : '+ SchemeAmt_Second_product)

CGST = Mobile.getText(findTestObject('Mobile/SummaryScreen/SplitScreen-CGST Value'), 0)

TotalAmt_Second_product = Mobile.getText(findTestObject('Mobile/SummaryScreen/SliptScreen_TotalAmt'), 0)

Calculated_Sch_Amt_Second_product = ((Total_Second_product * Double.parseDouble(Discount_Perc_Sheet)) / 100)

KeywordUtil.logInfo(Calculated_Sch_Amt_Second_product.toString())

Mobile.verifyEqual(Double.parseDouble(SchemeAmt_Second_product), Calculated_Sch_Amt_Second_product, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_Sch_Amt_Second_product.toString() + ' : Slab_1 Calculated Scheme Amount correctly displayed for second product!')

Calculated_TotalAmt_Second_product = ((Double.parseDouble(OrderAmt_Second_product) + Double.parseDouble(CGST)) - Calculated_Sch_Amt_Second_product)

Mobile.verifyEqual(Double.parseDouble(TotalAmt_Second_product), Calculated_TotalAmt_Second_product, FailureHandling.STOP_ON_FAILURE)

Mobile.takeScreenshot()

KeywordUtil.logInfo(Calculated_TotalAmt_Second_product.toString() + ' : Slab_1 Total amount in split screen displayed correctly for first product!')

Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Generate_OrderInvoice and CloseCall'), [:], FailureHandling.STOP_ON_FAILURE)




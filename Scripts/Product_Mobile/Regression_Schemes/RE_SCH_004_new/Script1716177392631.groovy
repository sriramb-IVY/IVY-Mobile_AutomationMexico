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

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/Relaunch the app'), [:], FailureHandling.CONTINUE_ON_FAILURE)

String sheet_name = '4_MTS_MS_Free_Qty(Value)'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Quantity_variations = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity_variations')

not_run: Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Retailer', 1)], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/StartVisit'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Store_Menu_Selection'), [('MenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 4), ('SubMenuName') : findTestData('Mobile Input Data/Common').getValue('Menu', 1)], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Clear_Star_Filter_Default_Selection'), [:], FailureHandling.STOP_ON_FAILURE)

for (int i = 0; i < Retailer.size(); i++) {
	Scheme_Index = (i + 1)

	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

	//slab_1
	'Slab_1'
	GlobalVariable.ProductName = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('SKU_Name', Scheme_Index)

	//GlobalVariable.ProductName = SKU_Name_1.get(i)
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

	Tot_value = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Sales_Value', Scheme_Index)

	Sales_Value = Double.parseDouble(Tot_value)

	KeywordUtil.logInfo('Piece quantity to given' + Sales_Value)

	'Entering Slabs Piece qty'
	int Quantity = Sales_Value / Piece_Price

	KeywordUtil.logInfo('Piece quantity to given' + Quantity)

	GlobalVariable.Qty = Quantity.toString()

	KeywordUtil.logInfo('Piece quantity to given' + GlobalVariable.Qty)

	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

	Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

	SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 0)

	KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

	Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

	Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	KeywordUtil.logInfo(Total.toString() + ' : Scheme Total Amount calculated and displayed correctly according the formula.')

	Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

	if (((Quantity_variations.get(i) == 'First slab') || (Quantity_variations.get(i) == 'Second slab')) || (Quantity_variations.get(i) == 'Both Slabs')) {
		if (Quantity_variations.get(i) == 'Both Slabs') {
			'Applying Scheme screen validations for Both Slabs'
			Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

			SchemeName1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

			SchemeName2 = Mobile.getText(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/SchemeName_2'), 0)

			SchemeDesc1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

			SchemeDesc2 = Mobile.getText(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/SchemeDesc_2'), 0)

			'Slab1 Scheme name validation'
			Mobile.verifyMatch(SchemeName1, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(SchemeName1 + ' : Scheme Name correctly displayed !')

			'Slab2 Scheme name validation'
			Mobile.verifyMatch(SchemeName2, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(SchemeName2 + ' : Scheme Name correctly displayed !')

			'Slab1 validation'
			Mobile.verifyMatch(SchemeDesc1, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(SchemeDesc1 + ' : Slab1 Description correctly displayed !')

			'Slab2 validation'
			Mobile.verifyMatch(SchemeDesc2, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Slab_Description2', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(SchemeDesc2 + ' : Slab Description correctly displayed !')

			Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

			//slab2
			'View screeen scheme validations'
			GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_Product', Scheme_Index)

			Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

			FreeProduct_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

			FreeSKU_Qty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

			FreeSKU_MinQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

			FreeSKU_MaxQty_Slab2 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

			Mobile.verifyMatch(FreeProduct_Slab2, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeProduct_Slab2 + ' :Slab2  Free product correctly displayed !')

			Expecetd_FreeSKU_MinQty_Slab2 = ('Min:' + findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', 2))

			Mobile.verifyMatch(FreeSKU_MinQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_MinQty_Slab2 + ' : Free Product Min Case Qty correctly displayed !')

			Expecetd_FreeSKU_MaxQty_Slab2 = ('Max:' + findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', 2))

			Mobile.verifyMatch(FreeSKU_MaxQty_Slab2.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab2, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_MaxQty_Slab2 + ' : Free Product Max Piece Qty correctly displayed !')

			Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

			//slab1
			Mobile.tap(findTestObject('Object Repository/Mobile/OrderInvoice/Scheme/Scheme_View_Btn_2'), 0)

			GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_Product', Scheme_Index)

			Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

			FreeProduct_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

			FreeSKU_Qty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

			FreeSKU_MinQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

			FreeSKU_MaxQty_Slab1 = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

			Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', 1))

			Mobile.verifyMatch(FreeSKU_MinQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_MinQty_Slab1 + ' : Free Product Min Case Qty correctly displayed !')

			Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', 1))

			Mobile.verifyMatch(FreeSKU_MaxQty_Slab1.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_MaxQty_Slab1 + ' : Free Product Max Piece Qty correctly displayed !')

			Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)

			//MTS both slabs
			FreeSKU_Qty_Slab = (Double.parseDouble(FreeSKU_Qty_Slab1) + Double.parseDouble(FreeSKU_Qty_Slab2))

			//Mobile.verifyMatch(FreeSKU_Qty_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)
			Mobile.verifyEqual(FreeSKU_Qty_Slab, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', Scheme_Index), FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_Qty_Slab1 + ' :Slab  Free product correctly displayed !')
		} else if ((Quantity_variations.get(i) == 'First slab') || (Quantity_variations.get(i) == 'Second slab')) {
			'Positive flow Scheme Validation for single slab'
			Mobile.takeScreenshot()

			Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

			SchemeName = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 0)

			SchemeDesc = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeDesc'), 0)

			Mobile.verifyMatch(SchemeName, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Scheme_Name', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(SchemeName + ' : Slab Scheme Name correctly applied !')

			Mobile.verifyMatch(SchemeDesc, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Slab_Description', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(SchemeDesc + ' : Scheme Description correctly displayed !')

			Mobile.tap(findTestObject('Mobile/OrderInvoice/Scheme/Scheme_View_Btn'), 0)

			Mobile.delay(3)

			'View screeen scheme validations'
			GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_Product', Scheme_Index)

			Mobile.waitForElementPresent(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

			FreeProduct_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU'), 5)

			FreeSKU_Qty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_Qty(Global)'), 5)

			FreeSKU_MinQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MinQty(Global)'), 5)

			FreeSKU_MaxQty_Slab = Mobile.getText(findTestObject('Mobile/OrderInvoice/Scheme/SchemeViewScreen_FreeSKU_MaxQty(Global)'), 5)

			Mobile.verifyMatch(FreeProduct_Slab, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeProduct_Slab + ' :Slab  Free product correctly displayed !')

			Mobile.verifyMatch(FreeSKU_Qty_Slab, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_Qty_Slab + ' : Free Product Case Qty correctly displayed !')

			Expecetd_FreeSKU_MinQty_Slab1 = ('Min:' + findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', Scheme_Index))

			Mobile.verifyMatch(FreeSKU_MinQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MinQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_MinQty_Slab + ' : Free Product Min Case Qty correctly displayed !')

			Expecetd_FreeSKU_MaxQty_Slab1 = ('Max:' + findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', Scheme_Index))

			Mobile.verifyMatch(FreeSKU_MaxQty_Slab.replaceAll('\\s', ''), Expecetd_FreeSKU_MaxQty_Slab1, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(FreeSKU_MaxQty_Slab + ' : Free Product Max Piece Qty correctly displayed !')

			Mobile.tap(findTestObject('Mobile/Common/Btn_Done'), 0)
		}

		Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

		'Summary screen scheme validations'
		if ((Quantity_variations.get(i) == 'First slab') || (Quantity_variations.get(i) == 'Second slab')) {
			GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_Product', Scheme_Index)

			Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

			Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

			Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global)'), 5)

			Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product correctly displayed in Summary Screen !')

			Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')
		} else if (Quantity_variations.get(i) == 'Both Slabss') {
			GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_Product', Scheme_Index)

			Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

			Slab1_FreeSku = Mobile.getText(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

			Slab1_FreeSku_PieceQty = Mobile.getText(findTestObject('Object Repository/Mobile/SummaryScreen/Summary_GetProductBasedPieceQty(Global_Index_1)'), 5)

			Mobile.verifyMatch(Slab1_FreeSku, GlobalVariable.Scheme_Free_SKU, false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(Slab1_FreeSku + ' : Free Product correctly displayed in Summary Screen !')

			Mobile.verifyMatch(Slab1_FreeSku_PieceQty, findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_quantity', Scheme_Index), false, FailureHandling.STOP_ON_FAILURE)

			Mobile.takeScreenshot()

			KeywordUtil.logInfo(Slab1_FreeSku_PieceQty + ' : Free Product Piece Qty correctly displayed in Summary Screen !')
		}

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

		Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

		Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' : Total amount in split screen displayed correctly !')
	} else if (Quantity_variations.get(i) == 'Less than slab1') {
		'Negative Scheme validation'

		'Scheme will not apply since it is negative scenario'
		Mobile.verifyElementNotVisible(findTestObject('Mobile/OrderInvoice/Scheme/SchemeName'), 15)

		Mobile.takeScreenshot()

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_ApplyingScheme_Screen'), [:], FailureHandling.STOP_ON_FAILURE)

		//Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Handle_Empties_Screen'), [:], FailureHandling.OPTIONAL)

		'Scheme name, sku, quantity is not displayed since it is negative scenario screen'
		GlobalVariable.Scheme_Free_SKU = findTestData('Mobile Input Data/4_MTS_MS_Free_Qty(Value)').getValue('Free_Product', Scheme_Index)

		Mobile.verifyElementNotVisible(findTestObject('Mobile/SummaryScreen/Summary_FreeSkuTitle(Global)'), 5)

		Mobile.takeScreenshot()

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

		Calculated_TotalAmt = ((Double.parseDouble(OrderAmt) + Double.parseDouble(CGST)) - Double.parseDouble(SchemeAmt))

		Mobile.verifyEqual(Double.parseDouble(TotalAmt), Calculated_TotalAmt, FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo(Calculated_TotalAmt.toString() + ' :  Slab_1 Total amount in split screen displayed correctly !')
	}

	Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-info_closeBtn'), 0)

	// Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Generate_OrderInvoice'), [:], FailureHandling.STOP_ON_FAILURE )//    Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Order btn'), 0)
	//
	//    Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)
	//
	//    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
	//
	//    id = Mobile.getText(findTestObject('Mobile/SummaryScreen/Click Order-OrderSavedID-Title'),
	//        0)
	//
	//    Ord_ID = id.replaceAll('[Order Saved. Order ID is:\']', '')
	//
	//    invoice_ID = Ord_ID.replaceAll('[\']', '')
	//
	//    KeywordUtil.logInfo(invoice_ID)
	//
	//    Mobile.tap(findTestObject('Mobile/SummaryScreen/Click Order-PRINT ORDER btn'), 0)
	//
	//    Mobile.delay(2)
	//
	//    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)
	//
	//    Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
	//
	//    Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)
	//
	//    WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Generate_Order'), [:], FailureHandling.STOP_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

	Mobile.swipe(50, 50, 50, 600, FailureHandling.OPTIONAL)

	Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
}

Mobile.delay(2)

Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

//
//Mobile.delay(2)
WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)


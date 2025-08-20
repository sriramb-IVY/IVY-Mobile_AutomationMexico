import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import java.awt.Robot as Robot
import java.awt.event.KeyEvent as KeyEvent
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import org.apache.commons.lang.RandomStringUtils as RandomStringUtils
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import io.appium.java_client.AppiumDriver as AppiumDriver
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords

Mobile.startApplication(GlobalVariable.APKFile, false)

String sheet_name = 'Credit_Collection'

String file_name = 'Mobile Input data'

ArrayList<String> Credit_Collection_retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Credit_Collection_retailer')

ArrayList<String> Credit_Validation = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Credit_Validation')

for (int i = 0; i < Credit_Collection_retailer.size(); i++) {

	Scheme_Index = (i + 1)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Credit_Limit_Collection').getValue(
		'Credit_Collection_retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

	//slab_1
	'Slab_1'
	GlobalVariable.ProductName = findTestData('Mobile Input Data/Credit_Limit_Collection').getValue('Product_Name', Scheme_Index)

	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
			5)

	Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.OPTIONAL)

	Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'),
			5)

	Mobile.takeScreenshot()

	Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'),
			0)

	Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'),
			0)

	Piece_Price = Double.parseDouble(Actual_PiecePrice)

	KeywordUtil.logInfo('Price of the product' + Piece_Price)

	Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'),
			0)

	 Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

	Slab_1_Min_Qty = findTestData('Mobile Input Data/Credit_Limit_Collection').getValue('Piece_quantity', Scheme_Index)

	println(GlobalVariable.Qty = Slab_1_Min_Qty)

    Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

    Mobile.takeScreenshot()

    Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

    SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'), 
        0)

	KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

	Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

	Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	KeywordUtil.logInfo(Total.toString() + ' : Slab Total Amount calculated and displayed correctly according the formula.')

	Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	Mobile.delay(2)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

	Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

	Mobile.takeScreenshot()

	if (i == 0) {

		//if ((Credit_Validation.get(i) == 'Infinite Limit & Warn') || (Credit_Validation.get(i) == 'Less than Credit & Warn limit')) {

		Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('Invoice should be generated, when credit & warn limit is Infinite AND Credit&Warn limit is less than the Order Value')

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
	}
	else if (i == 1) {
		Mobile.verifyElementVisible(findTestObject('Mobile/Mobile Part/Credit_Collection/Credit_Balance_Notavailable_Popup'),
				5)

		limit_exceed_alert_popup = Mobile.getText(findTestObject('Mobile/Mobile Part/Credit_Collection/Credit_Balance_Notavailable_Popup'),
				2, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo(limit_exceed_alert_popup)

		Mobile.verifyMatch(limit_exceed_alert_popup, findTestData('Mobile Input Data/Credit_Limit_Collection').getValue('Alert_Popup',
				Scheme_Index), false)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('Credit Balance not available hard alert should be displayed when credit bal exceeds credit limit ')

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		WebUI.callTestCase(findTestCase('Test Cases/Product_Mobile/Credit Management/Call Test Cases/CDM_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

		Mobile.delay(6)

		Mobile.swipe(0, 150, 0, 400)

		Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.OPTIONAL)

		not_run: if (Mobile.verifyElementVisible(findTestObject('Mobile/SummaryScreen/Summary-Title'),
		5, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

			Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

			Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

			Mobile.delay(2)

			Mobile.swipe(0, 100, 0, 400)

			Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)

			WebUI.callTestCase(findTestCase('Product_Mobile/Common/OrderPopUp(EditOrder)'), [:], FailureHandling.STOP_ON_FAILURE)
		}

		Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'), 0,
				FailureHandling.OPTIONAL)

		Product(Product_Name.get(i),Sales_Value.get(i))
		
	}}	
			
		
		
		def Product(def Product_Name, def Qty)
		{	
		GlobalVariable.ProductName = Product_Name
		
		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
				5)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.OPTIONAL)

		Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'),
				5)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

		Tot_value = Sales_Value

		Sales_Value = Double.parseDouble(Tot_value)

		'Entering Slab1 Piece qty'
		int Quantity = Sales_Value / Piece_Price

		qty = Quantity.toString()

		Buy_Qty = Double.parseDouble(qty)

		GlobalVariable.Qty = Quantity.toString()

		KeywordUtil.logInfo('Piece quantity to given' + GlobalVariable.Qty)

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'),
				0)

		KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

		Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

		Mobile.verifyEqual(Double.parseDouble(SKU_TOTAL), Total, FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo(Total.toString() + ' : Slab Total Amount calculated and displayed correctly according the formula.')

		Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

		Mobile.tap(findTestObject('Mobile/SummaryScreen/SummaryScreen-Invoice btn'), 0)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('Invoice should be generated, when credit & warn limit is Infinite AND Credit&Warn limit is less than the Order Value')

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
		}
	




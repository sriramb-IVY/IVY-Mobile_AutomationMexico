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

//WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)
String sheet_name = 'Credit_Retailer_Edit'

String file_name = 'Mobile Input data'

ArrayList<String> Credit_retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name,
		'Credit_retailer')

for (int i = 0; i < Credit_retailer.size(); i++) {
	Scheme_Index = (i + 1)

	//GlobalVariable.RetailerName = findTestData('Mobile Input Data/Credit_Retailer_Edit').getValue('Credit_retailer', 1)
	//Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

	if (i == 1) {
		WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Credit_Retailer_Edit').getValue(
			'Credit_retailer', 1)], FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

		'Enter Product'
		GlobalVariable.ProductName = findTestData('Mobile Input Data/Credit_Retailer_Edit').getValue('Product_Name', 1)

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

		Slab_1_Min_Qty = findTestData('Mobile Input Data/Credit_Retailer_Edit').getValue('Piece_quantity', Scheme_Index)

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

		Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

		Mobile.delay(2)

		Invoice_submitted_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'),
				2, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo(Invoice_submitted_popup)

		Mobile.verifyMatch(Invoice_submitted_popup, findTestData('Mobile Input Data/Bill_Validations').getValue('Alert_Popup',
				Scheme_Index), false)

		KeywordUtil.logInfo('Invoice is generated for the retailer ')

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.takeScreenshot()

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
	} else if (i == 0) {
		Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

		Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

		KeywordUtil.logInfo('The Retailer Name is :' + GlobalVariable.RetailerName)

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName,
				5)

		Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/RetailerEdit_Icon'), 5)

		Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)

		//Mobile.scrollToText(null, FailureHandling.STOP_ON_FAILURE)
		
		Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/Credit_Type_Field'), 5)

		Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/Select_Retailertype'),
				5)

		Ret_Edit_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/RetailerEdit_Popup'),
				2, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo(Ret_Edit_popup)

		Mobile.verifyMatch(Ret_Edit_popup, findTestData('Mobile Input Data/Credit_Retailer_Edit').getValue('Alert_Popup',
				Scheme_Index), false)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('Warning popup is displaying since the retailer is having due')

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.tap(findTestObject('Mobile/Mobile Part/Reusable Object/Save_btn'), 0)

		WebUI.callTestCase(findTestCase('Product_Mobile/Credit Management/Call Test Cases/CDM_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)

		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

		Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)

		KeywordUtil.logInfo('The Retailer Name is :' + GlobalVariable.RetailerName)

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.RetailerName,
				5)

		Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/RetailerEdit_Icon'), 5)

		Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/Credit_Type_Field'), 5)

		Mobile.tap(findTestObject('Object Repository/Mobile/Credit Management/Select_Retailertype'),
				5)

		Ret_Edit_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/RetailerEdit_Popup'),
				2, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo(Ret_Edit_popup)

		Mobile.verifyMatch(Ret_Edit_popup, findTestData('Mobile Input Data/Credit_Retailer_Edit').getValue('Alert_Popup',
				Scheme_Index), false)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('Warning popup is displaying since the retailer is having due')

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.tap(findTestObject('Mobile/Mobile Part/Reusable Object/Save_btn'), 0)

	}
}



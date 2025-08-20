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

//Mobile.startApplication(GlobalVariable.APKFile, false)

CustomKeywords.'credit_management.fetch_query.delete_invoice'(findTestData('Mobile Input Data/update_credit_warn_1').getValue('Retailer_Name',5))

CustomKeywords.'credit_management.fetch_query.delete_invoice'(findTestData('Mobile Input Data/update_credit_warn_1').getValue('Retailer_Name',6))

CustomKeywords.'credit_management.fetch_query.delete_invoice'(findTestData('Mobile Input Data/update_credit_warn_1').getValue('Retailer_Name',7))

CustomKeywords.'credit_management.fetch_query.query'(CustomKeywords.'credit_management.fetch_query.LIMITS_overdue'(findTestData('Mobile Input Data/update_credit_warn_1').getValue('Retailer_Name', 5), findTestData('Mobile Input Data/update_credit_warn_1').getValue('Balance', 1), findTestData('Mobile Input Data/update_credit_warn_1').getValue('overdue', 1)))

CustomKeywords.'credit_management.fetch_query.query'(CustomKeywords.'credit_management.fetch_query.LIMITS_overdue'(findTestData('Mobile Input Data/update_credit_warn_1').getValue('Retailer_Name', 6), findTestData('Mobile Input Data/update_credit_warn_1').getValue('Balance', 2), findTestData('Mobile Input Data/update_credit_warn_1').getValue('overdue', 2)))

CustomKeywords.'credit_management.fetch_query.query'(CustomKeywords.'credit_management.fetch_query.LIMITS_overdue'(findTestData('Mobile Input Data/update_credit_warn_1').getValue('Retailer_Name', 7), findTestData('Mobile Input Data/update_credit_warn_1').getValue('Balance', 3), findTestData('Mobile Input Data/update_credit_warn_1').getValue('overdue', 3)))

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_Login-With Reset'), [:], FailureHandling.STOP_ON_FAILURE)

String sheet_name = 'Limit_Validations'

String file_name = 'Mobile Input data'

ArrayList<String> Credit_Validation = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name,
	'Credit_Validation')

Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

//Product_Name = findTestData('Mobile Input Data/Limit_Validations').getValue('Product_Name', 1)

//Case_qty = findTestData('Mobile Input Data/Limit_Validations').getValue('CaseQty', 1)

//KeywordUtil.logInfo(CustomKeywords.'credit_management.stock_manual_load.Add_Stock'(Product_Name, Case_qty))

for (int i = 0; i < Credit_Validation.size(); i++) {
	
	Scheme_Index = (i + 1)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 10)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/TradeCoverage(SelectRetailer)'), [('RetailerName') : findTestData('Mobile Input Data/Limit_Validations').getValue(
				'Channelwise_Retailer', Scheme_Index)], FailureHandling.STOP_ON_FAILURE)

	CustomKeywords.'credit_management.collection_invoice.Single_Product_QuantityEnter_Piece'(findTestData('Mobile Input Data/Limit_Validations').getValue('Product_Name', Scheme_Index), findTestData('Mobile Input Data/Limit_Validations').getValue('Piece_quantity', Scheme_Index))
	
	if ((Credit_Validation.get(i) == 'Infinite') || (Credit_Validation.get(i) == 'Less_than_Warn') || (Credit_Validation.get(i) == 'Less_than_Credit')) {
		
		Submit_Popup = findTestData('Mobile Input Data/Limit_Validations').getValue('Alert_Popup', Scheme_Index)

		CustomKeywords.'credit_management.collection_invoice.Submit_Invoice'(Submit_Popup)
		
		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
		
	} else if (Credit_Validation.get(i) == 'Warn_limit') {
		
		GlobalVariable.Alert_message = findTestData('Mobile Input Data/Limit_Validations').getValue('Alert_Popup', Scheme_Index)

		CustomKeywords.'credit_management.collection_invoice.Getting_Warn_Alert'(GlobalVariable.Alert_message)
		
		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
		
	} else if (Credit_Validation.get(i) == 'Credit_limit_Exceed') {
		
		GlobalVariable.Alert_message = findTestData('Mobile Input Data/Limit_Validations').getValue('Alert_Popup', Scheme_Index)

		CustomKeywords.'credit_management.collection_invoice.Getting_Credit_Alert'(GlobalVariable.Alert_message)
		
		WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)
		
	}
}


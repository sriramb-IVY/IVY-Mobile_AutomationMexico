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

'Period Rfield - 1 Soft Alert'
CustomKeywords.'credit_management.Admin_Configs_1.Set_Configs'('CREDITDAY01', '1')

'Order Exceed RField -2 soft Alert'
CustomKeywords.'credit_management.Admin_Configs_1.Set_Configs'('CREDIT01', '2')

'Invoice delect Query And Limit_Overdue Query'

CustomKeywords.'credit_management.Fetch_query_1.LIMITS_overdue'(findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue(
	'SID_Retailer', 6), findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue('Balance', 6), findTestData(
	'Data Files/Mobile Input Data/update_credit_warn').getValue('overdue', 6))

CustomKeywords.'credit_management.Fetch_query_1.delete_invoice'(findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue('SID_Retailer',6))

CustomKeywords.'credit_management.Fetch_query_1.LIMITS_overdue'(findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue(
	'SID_Retailer', 2), findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue('Balance', 2), findTestData(
	'Data Files/Mobile Input Data/update_credit_warn').getValue('overdue', 2))

CustomKeywords.'credit_management.Fetch_query_1.delete_invoice'(findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue('SID_Retailer',2))

CustomKeywords.'credit_management.Fetch_query_1.LIMITS_overdue'(findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue(
	'SID_Retailer', 4), findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue('Balance', 4), findTestData(
	'Data Files/Mobile Input Data/update_credit_warn').getValue('overdue', 4))

CustomKeywords.'credit_management.Fetch_query_1.delete_invoice'(findTestData('Data Files/Mobile Input Data/update_credit_warn_1').getValue('SID_Retailer',4))


'infinity'

String sheet_name = 'Credit Period'

String file_name = 'Mobile Input data'

ArrayList<String> Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Retailer')

ArrayList<String> Product = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Product')

ArrayList<String> Quantity1 = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Quantity1')

ArrayList<String> Invoice_popup = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Invoice_popup')

ArrayList<String> Channel_Retailer = CustomKeywords.'poi.Automation.GetAllDataRow_Flag.getAllDataRow'(file_name, sheet_name, 'Channel_Retailer')

//Mobile.startApplication(GlobalVariable.APKFile, false)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Mobile_Login/VanSeller_withoutAttendance'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/Start_Attendance'), [:], FailureHandling.STOP_ON_FAILURE)

//Product1 = findTestData('Mobile Input Data/Credit Period').getValue('Product', 1)
//Qty = findTestData('Mobile Input Data/Credit Period').getValue('CaseQty', 1)
//
//CustomKeywords.'credit_management.Stock_manual_load_1.Add_Stock'(Product1, Qty)



for (int i = 0; i< Channel_Retailer.size(); i++) {

	index =i+1
	
	Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 30)

	Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Trade Coverage'), 0)
	
		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 5)
	
		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Channel_Retailer.get(i), 5)
	
		Mobile.tap(findTestObject('Mobile/TradeCoverage/First_Retailer_Selection_From_List'), 5)
	
		Mobile.delay(10)
		
		Mobile.tap(findTestObject('Mobile/Common/Btn_StartVisit'), 5)
	
		if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Popup_Title_LocationValidation'), 5, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Mobile/Common/Btn_YES'), 5)
		}
	
		if (Mobile.verifyElementVisible(findTestObject('Mobile/Common/Global_Radio_Btn'), 1, FailureHandling.OPTIONAL)) {
			Mobile.tap(findTestObject('Mobile/Common/Global_Radio_Btn'), 5)
		}
	
		Mobile.delay(15)
	
		Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)
	
		Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
	
		Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-OrderInvoice'), 0, FailureHandling.STOP_ON_FAILURE)
	
		Mobile.tap(findTestObject('Mobile/Common/Icon_Star'), 0, FailureHandling.OPTIONAL)
	
		Mobile.tap(findTestObject('Mobile/Common/Btn_Clear'),
				0, FailureHandling.OPTIONAL)
	
	

	Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

	Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Product.get(i), 5)

	Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

	Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.STOP_ON_FAILURE)

	Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'),
			5)

	Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'),
			0)

	Piece_Price = Double.parseDouble(Actual_PiecePrice)

	KeywordUtil.logInfo('Price of the product' + Piece_Price)

	Mobile.takeScreenshot()

	Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

	Tot_value = Quantity1.get(i)

	Sales_Value = Double.parseDouble(Tot_value)

	'Entering Slab1 Piece qty'

	int Quantity = Sales_Value / Piece_Price

	GlobalVariable.Qty = Quantity.toString()

	KeywordUtil.logInfo('Piece quantity to given' + GlobalVariable.Qty)

	Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

	Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

	String SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'),
			0)

	KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

	GlobalVariable.Total_Amt = SKU_TOTAL

	Mobile.takeScreenshot()

	Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/MustSell_Alert'), [:], FailureHandling.STOP_ON_FAILURE)

	Mobile.takeScreenshot()

	Mobile.delay(3)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Next'), 0)

	Mobile.tap(findTestObject('Object Repository/Mobile/SummaryScreen/SummaryScreen-Invoice btn'),
			0)

	Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

	Mobile.delay(2)

	Invoice_Save_Popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'),
			0)

	Mobile.takeScreenshot()

	KeywordUtil.logInfo(Invoice_Save_Popup)

	Mobile.verifyMatch(Invoice_Save_Popup, Invoice_popup.get(i), false)


	Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

	KeywordUtil.logInfo('Price of the product1' + GlobalVariable.Total_Amt)

	Mobile.delay(3)

	WebUI.callTestCase(findTestCase('Product_Mobile/Common/Call_Analysis'), [:], FailureHandling.STOP_ON_FAILURE)



}


WebUI.callTestCase(findTestCase('Product_Mobile/Common/Sync'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Product_Mobile/Common/HHT_Transaction_DB'), [:], FailureHandling.STOP_ON_FAILURE)

Code = findTestData('Mobile Input Data/Credit Period').getValue('Channel_Retailer', 2)

CustomKeywords.'credit_management.Period_Date_Change_1.CP_date_change'(Code)

Code = findTestData('Mobile Input Data/Credit Period').getValue('Channel_Retailer', 3)

CustomKeywords.'credit_management.Period_Date_Change_1.CP_date_change'(Code)








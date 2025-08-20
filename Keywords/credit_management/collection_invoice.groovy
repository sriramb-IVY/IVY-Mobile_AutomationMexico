package credit_management

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import internal.GlobalVariable as GlobalVariable
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.text.SimpleDateFormat as SimpleDateFormat
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil


public class collection_invoice {

	@Keyword
	public String Collection_Single_Invoice(){

		Mobile.delay(6)

		Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

		not_run: Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0, FailureHandling.OPTIONAL)

		not_run: Mobile.delay(3)

		not_run: Mobile.swipe(0, 150, 0, 400, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/Store_Actvy/Menu-Collection'), 0)

		String pattern = 'dd-MM-yyyy'

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

		String currentdate = simpleDateFormat.format(new Date())

		String ScrollDate = (currentdate + '(')

		GlobalVariable.label = currentdate

		Mobile.scrollToText(ScrollDate, FailureHandling.OPTIONAL)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice No'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Date'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Get_InvoiceNo_Date'),
				0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/OS Amt'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/OS_Amt'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Due Date'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Due Date_Value'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Invoice Amt'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Transaction_Amt'),
				0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Title/Received'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Values/Received_Amt'), 0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('The user can view the list of Pending Invoices details')

		int TransactionNo = Mobile.getText(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'),
				0)

		GlobalVariable.Transaction_No = TransactionNo

		String Transaction_Amt = Mobile.getText(findTestObject('Mobile/Collection/Values/Transaction_Amt'),
				0)

		GlobalVariable.C_TransactionAmt = Transaction_Amt

		String pay = (Double.parseDouble(Transaction_Amt))

		BigDecimal single_pay = new BigDecimal(pay).setScale(0, RoundingMode.HALF_DOWN)

		KeywordUtil.logInfo(single_pay.toString())

		Mobile.tap(findTestObject('Mobile/Collection/Values/Get_InvoiceNo'), 0)

		Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Collection/Blue_Tick'),
				0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('The user can able to select any invoice')

		Mobile.tap(findTestObject('Mobile/Collection/Pay_Btn'), 0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('The user can able to navigate Bill payment screen while click the pay button')

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Payment Amount_Value'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/Collection/Balance Amount_Value'), 0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('The user can able to view the Payment Amount_Value and Balance Amount_Value')

		'Validation for Cash'
		String mode_1 = Mobile.getText(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

		Mobile.tap(findTestObject('Mobile/Collection/Cash/Menu-Cash'), 0)

		Mobile.clearText(findTestObject('Mobile/Collection/Cash/Amount'), 0, FailureHandling.OPTIONAL)

		Mobile.setText(findTestObject('Mobile/Collection/Cash/Amount'), single_pay.toString(), 0,
				FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Collection/APPLY_Btn'), 0)

		Mobile.tap(findTestObject('Mobile/Collection/SAVE_Btn'), 0)

		Mobile.setText(findTestObject('Mobile/Collection/Enter Receipt Number'), findTestData('Mobile Input Data/CallAnalysis').getValue(
				'InvoiceNo', 1), 0)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)
	}

	@Keyword
	public String Manualload_Stock_Add(def Product, def Qty) {

		Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 0)

		Mobile.tap(findTestObject('Mobile/MainMenu/Menu_End of the Day'), 0)

		Mobile.tap(findTestObject('Mobile/End of the Day/SubMenu_Manual Van Load'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Select Distributor'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		GlobalVariable.label = GlobalVariable.DistributorName

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Ditributor_Dropdown_option'), 0)

		Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

		Mobile.verifyElementExist(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/SIH'), 0)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), Product, 10)

		Mobile.verifyElementVisible(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), 0)

		Mobile.verifyElementText(findTestObject('Mobile/End of the Day/Manual Van Load/ProductName'), Product)

		Mobile.takeScreenshot()

		println('User verified the functionality of the product hierarchy filter icon in the manual van load screen')

		Mobile.delay(1)

		Mobile.hideKeyboard(FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), 2, FailureHandling.OPTIONAL)

		Mobile.setText(findTestObject('Mobile/End of the Day/Manual Van Load/Case'), Qty , 10)

		println('user can able to enter quantity to the product in the Manual van load screen')

		Mobile.tap(findTestObject('Mobile/End of the Day/Manual Van Load/Save-btn'), 0)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		AppiumDriver driver = MobileDriverFactory.getDriver()

		String Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

		Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/ManualVanLoad').getValue('Verify', 1), false, FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		println('Van Unload should be saved successfully')

		Mobile.tap(findTestObject('Mobile/Common/Btn_Menu'), 2, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/MainMenu/Menu_Load Management'), 0)

		Mobile.tap(findTestObject('Mobile/LoadManagement/SubMenu-Van Load'), 0)

		String pattern = 'MM-dd-yyyy'

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern)

		String currentdate = simpleDateFormat.format(new Date())

		println(currentdate)

		String current_date = currentdate.replaceAll('\\D+', '')

		KeywordUtil.logInfo(current_date)

		String UserID = findTestData('Mobile Input Data/ManualVanLoad').getValue('User_ID', 1)

		String Vanload_no = UserID + current_date

		KeywordUtil.logInfo(Vanload_no)

		GlobalVariable.VanLoad_No = Vanload_no

		Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

		GlobalVariable.VanLoad_No = Mobile.getText(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'),
				0, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Vanload_No(contains)'), 0)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 0)

		GlobalVariable.ProductName = findTestData('Mobile Input Data/Limit_Validations').getValue('Product_Name', 1)

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
				0)

		Mobile.verifyElementVisible(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), 5, FailureHandling.STOP_ON_FAILURE)

		Mobile.verifyElementText(findTestObject('Mobile/LoadManagement/Vanload/ProductName'), GlobalVariable.ProductName,
				FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.scrollToText(GlobalVariable.VanLoad_No, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/Global_RadioIcon'), 3, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/VanLoadScreen-Accept Btn'), 3, FailureHandling.OPTIONAL)

		Mobile.tap(findTestObject('Mobile/LoadManagement/Vanload/OK Btn'), 3, FailureHandling.OPTIONAL)

		Actualtoastmessage = driver.findElementByXPath('//android.widget.Toast[1]').getText()

		println(Actualtoastmessage)

		Mobile.verifyMatch(Actualtoastmessage, findTestData('Mobile Input Data/VanLoad').getValue('Alerts', 1), false)

		Mobile.takeScreenshot()

		println('Verified : that User is able to accept the van load')
	}

	@Keyword
	public String Submit_Invoice(def Invoice_Saved_Popup, Negative_Validation) {
		
		
		GlobalVariable.label = Negative_Validation
		
		Mobile.verifyElementNotExist(findTestObject('Mobile/Common/Global_Toast_Validation'), 2, FailureHandling.CONTINUE_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)

		Mobile.delay(2)

		String Invoice_submitted_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'),
				2, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo(Invoice_submitted_popup)

		Mobile.verifyMatch(Invoice_submitted_popup, Invoice_Saved_Popup, false)

		KeywordUtil.logInfo('Invoice should be generated, until the Credit & Warn limit, Credit & Warn Bill achieved for the retailer ')

		KeywordUtil.logInfo('Invoice should be generated, if the retailer has infinite Credit & Warn limit, Credit & Warn Bill ')

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('Invoice should be generated, when credit & warn limit is Infinite AND Credit&Warn limit is less than the Order Value')
	}

	@Keyword
	public String Getting_Warn_Alert(def Warn_Alert_Popup, Invoice_Saved_Popup) {

//		AppiumDriver driver = MobileDriverFactory.getDriver()
//		
//		 String Warning_message_popup = driver.findElementByXPath('//android.widget.Toast[1]').getText()
//		 
//		 KeywordUtil.logInfo(Warning_message_popup)
//		 
//		 Mobile.verifyMatch(Warning_message_popup, Warn_Alert_Popup, false)
//		 
		Mobile.takeScreenshot()
		
		GlobalVariable.label = Warn_Alert_Popup
		
		Mobile.verifyElementExist(findTestObject('Mobile/Common/Global_Toast_Validation'), 2, FailureHandling.CONTINUE_ON_FAILURE)

		KeywordUtil.logInfo('Amount exceeds warning Credit Balance soft alert should be displayed when credit bal exceeds warn credit limit ')

		Mobile.tap(findTestObject('Mobile/Common/Btn_Confirm'), 0)
		
		String Invoice_submitted_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/InvoiceSubmittedSuccessfully_Popup'),
			2, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo(Invoice_submitted_popup)

		Mobile.verifyMatch(Invoice_submitted_popup, Invoice_Saved_Popup, false)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.takeScreenshot()

		//WebUI.callTestCase(findTestCase('Product_Mobile/Credit Management/Call Test Cases/CDM_CT_01'), [:], FailureHandling.STOP_ON_FAILURE)
	}

	@Keyword
	public String Getting_Credit_Alert(def Credit_Alert_Popup) {

		GlobalVariable.Alert_message = Credit_Alert_Popup

		Mobile.verifyElementVisible(findTestObject('Object Repository/Mobile/Credit Management/Alert_Popup_global'),
				5)

		String Credit_exceed_alert_popup = Mobile.getText(findTestObject('Object Repository/Mobile/Credit Management/Alert_Popup_global'),
				2, FailureHandling.STOP_ON_FAILURE)

		KeywordUtil.logInfo(Credit_exceed_alert_popup)

		Mobile.verifyMatch(Credit_exceed_alert_popup, Credit_Alert_Popup, false)

		Mobile.takeScreenshot()

		KeywordUtil.logInfo('Credit Balance not available hard alert should be displayed when credit bal exceeds credit limit ')

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 1, FailureHandling.OPTIONAL)

		Mobile.delay(2)

		Mobile.tap(findTestObject('Mobile/Common/Btn_OK'), 0, FailureHandling.OPTIONAL)
	}

	@Keyword
	public String Single_Product_QuantityEnter_Sales_Value(def Product_Name, def SalesValue) {

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

		//slab_1
		'Slab_1'
		GlobalVariable.ProductName = Product_Name

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
				5)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.OPTIONAL)

		Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'),
				5)

		Mobile.takeScreenshot()

		String Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'),
				0)

		String Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'),
				0)

		double Piece_Price = Double.parseDouble(Actual_PiecePrice)

		KeywordUtil.logInfo('Price of the product' + Piece_Price)

		String Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'),
				0)

		Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

		String Tot_value = SalesValue

		double Sales_Value = Double.parseDouble(Tot_value)

		'Entering Slab1 Piece qty'
		double Quantity = Sales_Value / Piece_Price

		String qty = Quantity.toString()

		double Buy_Qty = Double.parseDouble(qty)

		double Qty = Math.round(Quantity)

		GlobalVariable.Qty = Qty.toString()

		KeywordUtil.logInfo('Piece quantity to given' + GlobalVariable.Qty)

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		String SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'),
				0)

		KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

		String Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

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
	}



	@Keyword
	public String Single_Product_QuantityEnter_Piece(def Product_Name, def Piece_qty) {

		Mobile.tap(findTestObject('Mobile/Common/Icon_Search'), 4, FailureHandling.OPTIONAL)

		//slab_1
		'Slab_1'
		GlobalVariable.ProductName = Product_Name

		Mobile.setText(findTestObject('Mobile/Common/Field_Search_EnterText'), GlobalVariable.ProductName,
				5)

		Mobile.delay(5, FailureHandling.STOP_ON_FAILURE)

		Mobile.tap(findTestObject('Mobile/OrderInvoice/ProductName-(Global)'), 5, FailureHandling.OPTIONAL)

		Mobile.verifyElementVisible(findTestObject('Mobile/OrderInvoice/Tab-Product Details'),
				5)

		Mobile.takeScreenshot()

		String Actual_BasePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/BasePrice_Value_Field'),
				0)

		String Actual_PiecePrice = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/PiecePrice_Value_Field'),
				0)

		double Piece_Price = Double.parseDouble(Actual_PiecePrice)

		KeywordUtil.logInfo('Price of the product' + Piece_Price)

		String Actual_CaseSize = Mobile.getText(findTestObject('Mobile/OrderInvoice/ProductDetails/CaseSize_Value_Field'),
				0)

		Mobile.tap(findTestObject('Mobile/OrderInvoice/Qty_Field'), 0)

		String Slab_1_Min_Qty = Piece_qty

		println(GlobalVariable.Qty = Slab_1_Min_Qty)

		Mobile.callTestCase(findTestCase('Product_Mobile/Common/Enter_PIECE_Qty'), [:], FailureHandling.STOP_ON_FAILURE)

		Mobile.takeScreenshot()

		Mobile.tap(findTestObject('Mobile/Common/Icon_Back'), 0)

		Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

		String SKU_TOTAL = Mobile.getText(findTestObject('Mobile/OrderInvoice/OrderInvoiceScrn-SkuTotal'),
				0)

		KeywordUtil.logInfo('SKU Total Value ' + SKU_TOTAL)

		double Total = (Double.parseDouble(GlobalVariable.Qty) * Piece_Price)

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
	}
	
	
	
}

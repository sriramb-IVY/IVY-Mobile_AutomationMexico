import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import java.sql.Connection as Connection
import java.sql.DriverManager as DriverManager
import java.sql.ResultSet as ResultSet
import java.sql.Statement as Statement
import java.text.SimpleDateFormat as SimpleDateFormat
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import org.openqa.selenium.WebElement as WebElement
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.keyword.excel.ExcelKeywords as ExcelKeywords
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil



GlobalVariable.SR_No = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SR_No + '\'', ('columnNames') : ['sih_no','sih_id']], FailureHandling.STOP_ON_FAILURE)

String sih_no=GlobalVariable.data[0]

KeywordUtil.logInfo('Header id : '+sih_no)


GlobalVariable.InvoiceNo = sih_no

println('InvoiceNo : ' + GlobalVariable.InvoiceNo)

String sih_id=GlobalVariable.data[1]

GlobalVariable.label = sih_id

KeywordUtil.logInfo('sih_id : ' + GlobalVariable.label)

'Invoice Details table validation'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_sales_invoice_detail where sid_sih_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['SID_Piece_Qty','SID_Line_Value','SID_Piece_Price','SID_Net_Amount']], FailureHandling.STOP_ON_FAILURE)

String Qty=GlobalVariable.data[0]

KeywordUtil.logInfo('QUANTITY : '+Qty)

PieceQty = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1)

Mobile.verifyEqual(Qty, Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Enter piece Qty in the mobile Order & Invoice screen should be properly inserted into database')

//line_value

String SID_Line_Value=GlobalVariable.data[1]

Total_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1)

Mobile.verifyEqual(SID_Line_Value, Double.parseDouble(Total_Amt), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Enter Total Amount in the mobile Stock Proposal screen should be properly inserted into database')

//Price

String SID_Piece_Price=GlobalVariable.data[2]

Price = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1)

Mobile.verifyEqual(SID_Piece_Price, Double.parseDouble(Price), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Enter sku Amount in the mobile Stock Proposal screen should be properly inserted into database')

//Gross_Amt

String SIH_Net_Amount=GlobalVariable.data[2]

Gross_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

Mobile.verifyEqual(SIH_Net_Amount, Double.parseDouble(Gross_Amt), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Enter Total Amount in the mobile Stock Proposal screen should be properly inserted into database')

//3nd DB
'Tax value DB Validation'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_sales_invoice_tax_detail where sitd_sih_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['SITD_Value']], FailureHandling.STOP_ON_FAILURE)

String SITD_Value=GlobalVariable.data[0]

KeywordUtil.logInfo('SITD_Value : '+SITD_Value)

Tax = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1)

Mobile.verifyEqual(SITD_Value, Double.parseDouble(Tax), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Tax in the mobile screen should be properly inserted into database')

'Discount value DB validation'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_sales_invoice_discount_detail where sidd_sih_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['SIDD_Amount']], FailureHandling.STOP_ON_FAILURE)

String SIDD_Amount=GlobalVariable.data[0]

KeywordUtil.logInfo('Dicount value : '+SIDD_Amount)



Disc = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Disc', 1)

Mobile.verifyEqual(SIDD_Amount, Double.parseDouble(Disc), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Tax in the mobile screen should be properly inserted into database')


Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Sales Invoice View'), [:],
FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesInvoiceView/SOnumber'), 50)

WebUI.click(findTestObject('Web Part/SalesInvoiceView/SOnumber'))

GlobalVariable.SR_No = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

WebUI.setText(findTestObject('Web Part/SalesInvoiceView/SOnumber'), GlobalVariable.SR_No)

WebUI.click(findTestObject('Web Part/SalesInvoiceView/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.label = GlobalVariable.InvoiceNo

WebUI.scrollToPosition(0, 500)

WebUI.verifyElementVisible(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('Invoice Number : ' + GlobalVariable.label)

GlobalVariable.label = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

WebUI.verifyElementText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('Sales Order Number : ' + GlobalVariable.label)

GlobalVariable.label = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

not_run: WebUI.verifyElementPresent(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'), 0)

not_run: Amt = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.takeScreenshot()

not_run: KeywordUtil.logInfo(Amt)

GlobalVariable.label = GlobalVariable.InvoiceNo

WebUI.click(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 10, FailureHandling.OPTIONAL)

Invoice_Number = WebUI.getAttribute(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 'value')

KeywordUtil.logInfo('Invoice_Number : ' + Invoice_Number)

WebUI.verifyMatch(Invoice_Number, GlobalVariable.InvoiceNo, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

SO_Number = WebUI.getAttribute(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SO no'), 'value')

KeywordUtil.logInfo('SO_Number : ' + SO_Number)

WebUI.verifyMatch(SO_Number, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1), false,
		FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesInvoiceView/Random Click'), 5, FailureHandling.OPTIONAL)

ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 1)

GlobalVariable.ProductName = ProductName

SKU = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

WebUI.verifyMatch(SKU.toUpperCase(), ProductName.toUpperCase(), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Pric = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Price'))

Price = Pric.replace('$ ', '')

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Piece'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

TotalAmt = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalValue'))

Total_Amt = TotalAmt.replace('$ ', '')

KeywordUtil.logInfo('Total_Amt : ' + Total_Amt)

WebUI.verifyMatch(Total_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Tax = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Tax'))

Tax = Tax.replace('$ ', '')

KeywordUtil.logInfo('Tax : ' + Tax)

WebUI.verifyMatch(Tax, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Disc = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Disc'))

Disc = Disc.replace('$ ', '')

KeywordUtil.logInfo('Discount amt : ' + Disc)

Expected_Disc = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Disc', 1)

Mobile.verifyEqual(Double.parseDouble(Disc), Double.parseDouble(Expected_Disc), FailureHandling.STOP_ON_FAILURE)

//WebUI.verifyMatch(Double.parseDouble(Disc), Double.parseDouble(Expected_Disc), false, FailureHandling.STOP_ON_FAILURE)
WebUI.takeScreenshot()

GrossAmt = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalAmt'))

Gross_Amt = GrossAmt.replace('$ ', '')

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

Expected_Gross_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

Mobile.verifyEqual(Double.parseDouble(Gross_Amt), Double.parseDouble(Expected_Gross_Amt), FailureHandling.STOP_ON_FAILURE)

//WebUI.verifyMatch(Double.parseDouble(Gross_Amt), , false, FailureHandling.STOP_ON_FAILURE)
WebUI.takeScreenshot()

WebUI.closeBrowser()


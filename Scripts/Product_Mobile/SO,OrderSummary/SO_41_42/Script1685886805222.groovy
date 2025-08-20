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


GlobalVariable.InvoiceNo = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_order_header  where oh_order_id=\'' + GlobalVariable.InvoiceNo + '\'', ('columnNames') : ['OH_id']], FailureHandling.STOP_ON_FAILURE)

String OH_Id=GlobalVariable.data[0]

KeywordUtil.logInfo('Header data id'+OH_Id)

GlobalVariable.label = OH_Id

KeywordUtil.logInfo('oh_id : ' + GlobalVariable.label)



Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_order_detail where od_oh_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['OD_Qty','od_line_value','od_price','OD_Net_Amount']], FailureHandling.STOP_ON_FAILURE)

String Qty=GlobalVariable.data[0]

KeywordUtil.logInfo('Header data id : '+ Qty)



PieceQty = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1)

Mobile.verifyEqual(Qty, Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Entere piece Qty in the mobile Order & Invoice screen should be properly inserted into database')

//line_value


String od_line_value=GlobalVariable.data[1]

Total_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1)

Mobile.verifyEqual(od_line_value, Double.parseDouble(Total_Amt), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Enter Total Amount in the mobile Stock Proposal screen should be properly inserted into database')

//Price
String od_price=GlobalVariable.data[2]

Price = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1)

Mobile.verifyEqual(od_price, Double.parseDouble(Price), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Enter sku Amount in the mobile Stock Proposal screen should be properly inserted into database')

//Gross_Amt

String OD_Net_Amount=GlobalVariable.data[3]

Gross_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

Mobile.verifyEqual(OD_Net_Amount, Double.parseDouble(Gross_Amt), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Entere Total Amount in the mobile Stock Proposal screen should be properly inserted into database')

'Tax Validation'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_order_tax_detail where otd_oh_id=\'' + GlobalVariable.label + '\'', ('columnNames') : ['OTD_Value']], FailureHandling.STOP_ON_FAILURE)

String tax_Value=GlobalVariable.data[0]

KeywordUtil.logInfo('Tax amount : '+ tax_Value)


Tax = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1)

Mobile.verifyEqual(tax_Value, Double.parseDouble(Tax), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Tax in the mobile screen should be properly inserted into database')


'Discount value DB validation'

Mobile.callTestCase(findTestCase('Product_Mobile/Common/DB'), [('Query') : 'select * from appdata_order_discount_detail where odd_oh_id= \'' + GlobalVariable.label + '\'', ('columnNames') : ['ODD_Amount']], FailureHandling.STOP_ON_FAILURE)

String ODD_Amount=GlobalVariable.data[0]

KeywordUtil.logInfo('Discount amount : '+ ODD_Amount)


Disc = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Disc', 1)

Mobile.verifyEqual(ODD_Amount, Double.parseDouble(Disc), FailureHandling.STOP_ON_FAILURE)

KeywordUtil.logInfo('Discount in the mobile screen should be properly inserted into database')




Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Sales Order View'), [:], FailureHandling.STOP_ON_FAILURE)

//WebUI.click(findTestObject('Web Part/Re-usable/Navigation Hide Icon'))
WebUI.waitForElementVisible(findTestObject('Web Part/SalesOrderView/SalesPerson'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Web Part/SalesOrderView/SalesPerson'))

GlobalVariable.label = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SalesPerson', 1)

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/Global_li_Tag(Dropdown)'), 50)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesOrderView/Global_li_Tag(Dropdown)'), 50)

WebUI.click(findTestObject('Web Part/SalesOrderView/Global_li_Tag(Dropdown)'))

WebUI.click(findTestObject('Web Part/SalesOrderView/Random Click'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('Object Repository/Web Part/SalesOrderView/Source_Field'))

GlobalVariable.label = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Source', 1)

WebUI.click(findTestObject('Object Repository/Web Part/Re-usable/Global/li_Tag(Dropdown)'))

WebUI.click(findTestObject('Web Part/SalesOrderView/Search_Btn'))

WebUI.delay(10)

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.InvoiceNo = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

GlobalVariable.SR_No = GlobalVariable.InvoiceNo

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/Filter_Icon'), 0)

WebUI.click(findTestObject('Web Part/SalesOrderView/Filter_Icon'))

WebUI.click(findTestObject('Web Part/SalesOrderView/SO_Number_In_Filter'))

WebUI.setText(findTestObject('Web Part/SalesOrderView/SO_Number_In_Filter'), GlobalVariable.InvoiceNo)

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/Apply_Btn_In_Filter'), 0)

WebUI.click(findTestObject('Web Part/SalesOrderView/Apply_Btn_In_Filter'))

WebUI.verifyElementVisible(findTestObject('Web Part/SalesOrderView/Global_Td_Tag(SO.no)'))

WebUI.verifyElementText(findTestObject('Web Part/SalesOrderView/Global_Td_Tag(SO.no)'), GlobalVariable.InvoiceNo)

WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Number : ' + GlobalVariable.InvoiceNo)

GlobalVariable.Total_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

WebUI.verifyElementVisible(findTestObject('Web Part/SalesOrderView/Global_Td_Tag(Price)'))

String SO_Value = WebUI.getText(findTestObject('Web Part/SalesOrderView/Global_Td_Tag(Price)'))

SO_Val = SO_Value.replace('$ ', '')

KeywordUtil.logInfo(SO_Val)

WebUI.verifyEqual(Double.parseDouble(SO_Val), Double.parseDouble(GlobalVariable.Total_Amt), FailureHandling.STOP_ON_FAILURE)

//WebUI.verifyElementText(findTestObject('Web Part/SalesOrderView/Global_Td_Tag(Price)'), GlobalVariable.Total_Amt)
WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Value : ' + GlobalVariable.Total_Amt)

WebUI.click(findTestObject('Web Part/SalesOrderView/View_Icon'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesOrderView/ViewScrn_SOnumber'), 10, FailureHandling.OPTIONAL)

SO_Number = WebUI.getAttribute(findTestObject('Web Part/SalesOrderView/ViewScrn_SOnumber'), 'value')

KeywordUtil.logInfo('SO_Number : ' + SO_Number)

WebUI.verifyMatch(SO_Number, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/ViewScrn_TotalValue'), 10, FailureHandling.STOP_ON_FAILURE)

GrossAmt = WebUI.getAttribute(findTestObject('Web Part/SalesOrderView/ViewScrn_TotalValue'), 'value')

Gross_Amt = GrossAmt.replace('$ ', '')

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

WebUI.verifyMatch(Gross_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/ViewScrn_NetAmt'), 10, FailureHandling.STOP_ON_FAILURE)

NetAmt = WebUI.getAttribute(findTestObject('Web Part/SalesOrderView/ViewScrn_NetAmt'), 'value')

Net_Amt = NetAmt.replace('$ ', '')

KeywordUtil.logInfo('Net_Amt : ' + Net_Amt)

WebUI.verifyMatch(Net_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 1)

SKU = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

Expected_Text = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 1)

WebUI.verifyMatch(SKU.toUpperCase(), Expected_Text.toUpperCase(), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Pric = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Price'))

Price = Pric.replace('$ ', '')

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Piece'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/ViewScrn_GrossLabel'), 10, FailureHandling.OPTIONAL)

TotalAmt = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_TotalAmt'))

Total_Amt = TotalAmt.replace('$ ', '')

KeywordUtil.logInfo('Total_Amt : ' + Total_Amt)

WebUI.verifyMatch(Total_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Tax_1 = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Tax'))

Tax = Tax_1.replace('$ ', '')

KeywordUtil.logInfo('Tax : ' + Tax)

WebUI.verifyMatch(Tax, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Disc = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Disc'))

Disc = Disc.replace('$ ', '')

KeywordUtil.logInfo('Discount amt : ' + Disc)

Expected_Disc = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Disc', 1)

WebUI.verifyEqual(Double.parseDouble(Disc), Double.parseDouble(Expected_Disc), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

GrossAmt = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Gross'))

Gross_Amt = GrossAmt.replace('$ ', '')

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

WebUI.verifyMatch(Gross_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()




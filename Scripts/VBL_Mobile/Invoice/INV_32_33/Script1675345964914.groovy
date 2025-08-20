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

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

GlobalVariable.SR_No = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

queryString = (('select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SR_No) + '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object sih_no = recordSet.getObject('sih_no')

    GlobalVariable.VersionCodenvoiceNo = sih_no

    println('InvoiceNo : ' + GlobalVariable.VersionCodenvoiceNo)

    Object sih_id = recordSet.getObject('sih_id')

    GlobalVariable.label = sih_id

    println('sih_id : ' + GlobalVariable.label)
}

//2nd DB
queryString1 = (('select * from appdata_sales_invoice_detail where sid_sih_id=\'' + GlobalVariable.label) + '\'')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object Qty = recordSet1.getObject('SID_Piece_Qty')

    PieceQty = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1)

    Mobile.verifyEqual(Qty, Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

    println('Entere piece Qty in the mobile Order & Invoice screen should be properly inserted into database')

    //line_value
    Object SID_Line_Value = recordSet1.getObject('SID_Line_Value')

    Total_Amt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1)

    Mobile.verifyEqual(SID_Line_Value, Double.parseDouble(Total_Amt), FailureHandling.STOP_ON_FAILURE)

    println('Entere Total Amount in the mobile Stock Proposal screen should be properly inserted into database')

    //Price
    Object SID_Piece_Price = recordSet1.getObject('SID_Piece_Price')

    Price = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1)

    Mobile.verifyEqual(SID_Piece_Price, Double.parseDouble(Price), FailureHandling.STOP_ON_FAILURE)

    println('Entere sku Amount in the mobile Stock Proposal screen should be properly inserted into database')

    //Gross_Amt
    Object SIH_Net_Amount = recordSet1.getObject('SID_Net_Amount')

    Gross_Amt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

    Mobile.verifyEqual(SIH_Net_Amount, Double.parseDouble(Gross_Amt), FailureHandling.STOP_ON_FAILURE)

    println('Entere Total Amount in the mobile Stock Proposal screen should be properly inserted into database')
}

//3nd DB
queryString2 = (('select * from appdata_sales_invoice_tax_detail where sitd_sih_id=\'' + GlobalVariable.label) + '\' ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object SITD_Value = recordSet2.getObject('SITD_Value')

    Tax = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1)

    Mobile.verifyEqual(SITD_Value, Double.parseDouble(Tax), FailureHandling.STOP_ON_FAILURE)

    println('Tax in the mobile screen should be properly inserted into database')
}

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Sales Invoice View'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/SOnumber'), 50)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/SOnumber'))

GlobalVariable.SR_No = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/SOnumber'), GlobalVariable.SR_No)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.label = GlobalVariable.VersionCodenvoiceNo

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('Invoice Number : ' + GlobalVariable.label)

GlobalVariable.label = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('Sales Order Number : ' + GlobalVariable.label)

GlobalVariable.label = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.takeScreenshot()

KeywordUtil.logInfo(Amt)

GlobalVariable.label = GlobalVariable.VersionCodenvoiceNo

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 10, FailureHandling.OPTIONAL)

Invoice_Number = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 'value')

KeywordUtil.logInfo('Invoice_Number : ' + Invoice_Number)

WebUI.verifyMatch(Invoice_Number, GlobalVariable.VersionCodenvoiceNo, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

SO_Number = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SO no'), 'value')

KeywordUtil.logInfo('SO_Number : ' + SO_Number)

WebUI.verifyMatch(SO_Number, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1), false, 
    FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Random Click'), 5, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 1)

SKU = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

WebUI.verifyMatch(SKU, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Price = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Price'))

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Piece'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Total_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalValue'))

KeywordUtil.logInfo('Total_Amt : ' + Total_Amt)

WebUI.verifyMatch(Total_Amt, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Tax = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Tax'))

KeywordUtil.logInfo('Tax : ' + Tax)

WebUI.verifyMatch(Tax, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Gross_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalAmt'))

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

WebUI.verifyMatch(Gross_Amt, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.closeBrowser()


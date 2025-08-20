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

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Sales Order View'), [:], FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/SalesPerson'), 50, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/SalesPerson'))

GlobalVariable.label = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('SalesPerson', 1)

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Global_li_Tag(Dropdown)'), 50)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Global_li_Tag(Dropdown)'), 50)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Global_li_Tag(Dropdown)'))

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Random Click'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/SOnumber'))

GlobalVariable.VersionCodenvoiceNo = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/SOnumber'), GlobalVariable.VersionCodenvoiceNo)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.SR_No = GlobalVariable.VersionCodenvoiceNo

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Global_Td_Tag(SO.no)'))

WebUI.verifyElementText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Global_Td_Tag(SO.no)'), GlobalVariable.VersionCodenvoiceNo)

WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Number : ' + GlobalVariable.VersionCodenvoiceNo)

GlobalVariable.Total_Amt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Global_Td_Tag(Price)'))

WebUI.verifyElementText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/Global_Td_Tag(Price)'), GlobalVariable.Total_Amt)

WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Value : ' + GlobalVariable.Total_Amt)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/View_Icon'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_SOnumber'), 10, FailureHandling.OPTIONAL)

SO_Number = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_SOnumber'), 'value')

KeywordUtil.logInfo('SO_Number : ' + SO_Number)

WebUI.verifyMatch(SO_Number, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1), false, 
    FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_TotalValue'), 10, FailureHandling.STOP_ON_FAILURE)

Gross_Amt = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_TotalValue'), 'value')

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

WebUI.verifyMatch(Gross_Amt, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_NetAmt'), 10, FailureHandling.STOP_ON_FAILURE)

Net_Amt = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_NetAmt'), 'value')

KeywordUtil.logInfo('Net_Amt : ' + Net_Amt)

WebUI.verifyMatch(Net_Amt, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

SKU = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

WebUI.verifyMatch(SKU, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Price = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_Grid_Price'))

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_Grid_Piece'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_GrossLabel'), 10, FailureHandling.OPTIONAL)

Total_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_Grid_TotalAmt'))

KeywordUtil.logInfo('Total_Amt : ' + Total_Amt)

WebUI.verifyMatch(Total_Amt, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Tax = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_Grid_Tax'))

KeywordUtil.logInfo('Tax : ' + Tax)

WebUI.verifyMatch(Tax, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Gross_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesOrderView/ViewScrn_Grid_Gross'))

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

WebUI.verifyMatch(Gross_Amt, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Connection connection = null

url = findTestData('VBL_DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('VBL_DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('VBL_DB Credentials/Config1').getValue('username', 1)

password = findTestData('VBL_DB Credentials/Config1').getValue('password', 1)

GlobalVariable.VersionCodenvoiceNo = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1)

queryString = (('select * from appdata_order_header  where oh_order_id=\'' + GlobalVariable.VersionCodenvoiceNo) + '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object oh_id = recordSet.getObject('oh_id')

    GlobalVariable.label = oh_id

    println('oh_id : ' + GlobalVariable.label)
}

//2nd DB
queryString1 = (('select * from appdata_order_detail where od_oh_id=\'' + GlobalVariable.label) + '\'')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object Qty = recordSet1.getObject('OD_Qty')

    PieceQty = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 1)

    Mobile.verifyEqual(Qty, Integer.parseInt(PieceQty), FailureHandling.STOP_ON_FAILURE)

    println('Entere piece Qty in the mobile Order & Invoice screen should be properly inserted into database')

    //line_value
    Object od_line_value = recordSet1.getObject('od_line_value')

    Total_Amt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 1)

    Mobile.verifyEqual(od_line_value, Double.parseDouble(Total_Amt), FailureHandling.STOP_ON_FAILURE)

    println('Entere Total Amount in the mobile Stock Proposal screen should be properly inserted into database')

    //Price
    Object od_price = recordSet1.getObject('od_price')

    Price = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Price', 1)

    Mobile.verifyEqual(od_price, Double.parseDouble(Price), FailureHandling.STOP_ON_FAILURE)

    println('Entere sku Amount in the mobile Stock Proposal screen should be properly inserted into database')

    //Gross_Amt
    Object OD_Net_Amount = recordSet1.getObject('OD_Net_Amount')

    Gross_Amt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 1)

    Mobile.verifyEqual(OD_Net_Amount, Double.parseDouble(Gross_Amt), FailureHandling.STOP_ON_FAILURE)

    println('Entere Total Amount in the mobile Stock Proposal screen should be properly inserted into database')
}

//3nd DB
queryString2 = (('select * from appdata_order_tax_detail where otd_oh_id=\'' + GlobalVariable.label) + '\' ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object tax_Value = recordSet2.getObject('OTD_Value')

    Tax = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 1)

    Mobile.verifyEqual(tax_Value, Double.parseDouble(Tax), FailureHandling.STOP_ON_FAILURE)

    println('Tax in the mobile screen should be properly inserted into database')
}


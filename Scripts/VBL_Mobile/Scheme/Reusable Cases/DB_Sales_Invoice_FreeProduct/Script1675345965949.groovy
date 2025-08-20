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

GlobalVariable.SO_Number = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 4)

queryString = (('select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SO_Number) + '\'')

KeywordUtil.logInfo(('select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SO_Number) +  '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
    Object sih_no = recordSet.getObject('sih_no')

    GlobalVariable.VersionCodenvoiceNo = sih_no

    KeywordUtil.logInfo('InvoiceNo : ' + GlobalVariable.VersionCodenvoiceNo)

    Object sih_id = recordSet.getObject('sih_id')

    GlobalVariable.label = sih_id

    KeywordUtil.logInfo('sih_id : ' + GlobalVariable.label)
}

//2nd DB
queryString1 = (('select * from appdata_sales_invoice_scheme_detail where sisd_sih_id=\'' + GlobalVariable.label) + '\'')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

if (recordSet1.next()) {



    Object SISD_AS_Id = recordSet1.getObject('SISD_AS_Id')

    GlobalVariable.label = SISD_AS_Id

    Object SISD_APH_Id = recordSet1.getObject('SISD_APH_Id')

    GlobalVariable.label1 = SISD_APH_Id
}

//3nd DB
queryString2 = (('select * from adm_scheme where as_id=\'' + GlobalVariable.label) + '\' ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

if (recordSet2.next()) {
    Object AS_Code = recordSet2.getObject('AS_Code')

    SchemeName = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('SchemeName', 4)

    Mobile.verifyMatch(AS_Code, SchemeName, false, FailureHandling.STOP_ON_FAILURE)

    println('SchemeName from the mobile should be properly inserted into database')
}

//4nd DB
queryString3 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label1) + '\' ')

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

if (recordSet3.next()) {
    Object APH_Name = recordSet3.getObject('APH_Name')

    ProductName = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('FreeProduct', 4)

    Mobile.verifyMatch(ProductName, APH_Name, false, FailureHandling.STOP_ON_FAILURE)

    println('Scheme Product Name from the mobile should be properly inserted into database')
}

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/ManjuLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('VBL_Mobile/Re-Usables/Web Login and Navigation/Navigate to Sales Invoice View'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/SOnumber'), 50)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/SOnumber'))

//GlobalVariable.VersionCodenvoiceNo = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 2)
GlobalVariable.SO_Number = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 4)

WebUI.setText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/SOnumber'), GlobalVariable.SO_Number)

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.label = GlobalVariable.SO_Number

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Number : ' + GlobalVariable.label)

'Invoice number get from Data Base'
KeywordUtil.logInfo(GlobalVariable.VersionCodenvoiceNo)

GlobalVariable.label = GlobalVariable.VersionCodenvoiceNo

WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('Invoice Number : ' + GlobalVariable.label)

//GlobalVariable.label = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 2)
//
//WebUI.verifyElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))
//
//Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))
//
//WebUI.takeScreenshot()
//
//KeywordUtil.logInfo(Amt)
GlobalVariable.label = GlobalVariable.VersionCodenvoiceNo

WebUI.click(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.waitForElementNotVisible(findTestObject('XXXXXXXXXXXX/Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 10, FailureHandling.OPTIONAL)

//
//Invoice_Number = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 'value')
//
//KeywordUtil.logInfo('Invoice_Number : ' + Invoice_Number)
//
//WebUI.verifyMatch(Invoice_Number, GlobalVariable.VersionCodenvoiceNo, false, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.takeScreenshot()
//
//SO_Number = WebUI.getAttribute(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SO no'), 'value')
//
//KeywordUtil.logInfo('SO_Number : ' + SO_Number)
//
//WebUI.verifyMatch(SO_Number, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1), false, 
//    FailureHandling.STOP_ON_FAILURE)
//
//WebUI.takeScreenshot()
WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/Random Click'), 5, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 4)

SKU = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

WebUI.verifyMatch(SKU, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Price = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Price'))

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Price', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Case'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Total_Amt1 = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalValue'))
Total_Amt = Total_Amt1.replaceAll(",", "")
KeywordUtil.logInfo('Total_Amt : ' + Total_Amt)

//WebUI.verifyMatch(Total_Amt, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 2), false, FailureHandling.STOP_ON_FAILURE)
TotalAmt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 4)

WebUI.verifyEqual(Double.parseDouble(Total_Amt), Double.parseDouble(TotalAmt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Scheme_Amt = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SchemeAmt'))

KeywordUtil.logInfo('Tax : ' + Scheme_Amt)

SchemeAmt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('SchemeAmt', 4)

WebUI.verifyEqual(Double.parseDouble(Scheme_Amt), Double.parseDouble(SchemeAmt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()


Gross_Amt1 = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalAmt'))

Gross_Amt = Gross_Amt1.replaceAll(",", "")

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

GrossAmt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 4)

FreeProductAmt = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('FreeProductAmt', 4)

GrossAmount = Double.parseDouble(GrossAmt) - Double.parseDouble(FreeProductAmt)

WebUI.verifyEqual(Double.parseDouble(Gross_Amt), GrossAmount, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()


'free product grid details'

GlobalVariable.ProductName = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('FreeProduct', 4)

freeSKU = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('Free SKU : ' + freeSKU)

WebUI.verifyMatch(freeSKU, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

freeSkuQty = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Case'))

KeywordUtil.logInfo('free sku Qty : ' + freeSkuQty)

WebUI.verifyMatch(freeSkuQty, findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('FreeProductQTY', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SchemeNameField'), 0)

SchemeName = WebUI.getText(findTestObject('XXXXXXXXXXXX/Web Part/SalesInvoiceView/InvoiceDetails/SchemeNameField'))

Expected_SchemeName = findTestData('VBL_Web Input Data/Sales_Order_Invoice_View').getValue('SchemeName', 4)

Schemeverify = SchemeName.contains(Expected_SchemeName)

String Scheme_verify = Schemeverify

WebUI.verifyMatch(Scheme_verify, 'true', false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()
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

CreditNote = 5

Connection connection = null

url = findTestData('DB Credentials/Config1').getValue('URL', 1)

dbname = findTestData('DB Credentials/Config1').getValue('dbname', 1)

username = findTestData('DB Credentials/Config1').getValue('username', 1)

password = findTestData('DB Credentials/Config1').getValue('password', 1)

GlobalVariable.SO_Number = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', CreditNote)

queryString = (('select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SO_Number) + 
'\'')

KeywordUtil.logInfo(('select * from appdata_sales_invoice_header where SIH_Sales_Order_Id =\'' + GlobalVariable.SO_Number) + 
    '\'')

String conn = (((((('jdbc:sqlserver://' + url) + ';databaseName=') + dbname) + ';user=') + username) + ';password=') + password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

while (recordSet.next()) {
    Object sih_no = recordSet.getObject('sih_no')

    GlobalVariable.Pending_bill_id  = sih_no

    KeywordUtil.logInfo('InvoiceNo : ' + GlobalVariable.Pending_bill_id)

    Object sih_id = recordSet.getObject('sih_id')

    GlobalVariable.label = sih_id

    KeywordUtil.logInfo('sih_id : ' + GlobalVariable.label)
}

//2nd DB
queryString1 = (('select * from appdata_sales_invoice_scheme_detail where sisd_sih_id=\'' + GlobalVariable.label) + '\'')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

while (recordSet1.next()) {
    Object Discount_Amount = recordSet1.getObject('SISD_Discount_Amount')

    //    //percentage
    //    DiscountAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('DiscountAmt', CreditNote)
    //
    //    Mobile.verifyEqual(Discount_Amount, Double.parseDouble(DiscountAmt), FailureHandling.STOP_ON_FAILURE)
    //
    //    println('Scheme Discount Amount from the mobile should be properly inserted into database')
    //
    //    Scheme Amt
    //    Object SIH_Scheme_Amount = recordSet1.getObject('SIH_Scheme_Amount')
    //
    //    SchemeAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeAmt', CreditNote)
    //
    //    Mobile.verifyEqual(SIH_Scheme_Amount, Double.parseDouble(SchemeAmt), FailureHandling.STOP_ON_FAILURE)
    //
    //    println('Scheme Amount from the mobile summary screen should be properly inserted into database')
    'FreeProductPieceQty'
    Object SISD_Qty = recordSet1.getObject('SISD_Qty')

    CaseQty = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProductQTY', CreditNote)

    CaseSize = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('CaseSize', CreditNote)

    FreeProductPieceQty = (Integer.parseInt(CaseQty) * Integer.parseInt(CaseSize))

    Mobile.verifyEqual(SISD_Qty, FreeProductPieceQty, FailureHandling.STOP_ON_FAILURE)

    println('Case & Piece Qty from the mobile summary screen should be properly inserted into database')

    Object SISD_AS_Id = recordSet1.getObject('SISD_AS_Id')

    GlobalVariable.label2 = SISD_AS_Id

    Object SISD_APH_Id = recordSet1.getObject('SISD_APH_Id')

    GlobalVariable.label1 = SISD_APH_Id
}

//3rd 
queryString5 = (('Select top 1 * from AppData_Sales_Invoice_Scheme_Detail_Credit where SISDC_SIH_Id =\'' + GlobalVariable.label) + 
'\' ')

ResultSet rs5 = stm.executeQuery(queryString5)

def recordSet5 = rs5

while (recordSet5.next()) {
    Object SISDC_Value = recordSet5.getObject('SISDC_Value')

    FreeProductAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProductAmt', CreditNote)

    Mobile.verifyEqual(FreeProductAmt, SISDC_Value, FailureHandling.STOP_ON_FAILURE)

    println('credit note free amount  from the mobile should be properly inserted into database')
}

queryString6 = (('Select top 1 * from AppData_Sales_Invoice_Scheme_Detail_Credit where SISDC_SIH_Id =\'' + GlobalVariable.label) + 
'\' order by 1 desc ')

ResultSet rs6 = stm.executeQuery(queryString6)

def recordSet6 = rs6

while (recordSet6.next()) {
    Object SISDC_Value1 = recordSet6.getObject('SISDC_Value')

    DiscountAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('DiscountAmt', CreditNote)

    Mobile.verifyEqual(DiscountAmt, SISDC_Value1, FailureHandling.STOP_ON_FAILURE)

    println('credit note free amount  from the mobile should be properly inserted into database')
}

//4th DB
queryString2 = (('select * from adm_scheme where as_id=\'' + GlobalVariable.label2) + '\' ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

while (recordSet2.next()) {
    Object AS_Code = recordSet2.getObject('AS_Code')

    SchemeName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeName', CreditNote)

    Mobile.verifyMatch(AS_Code, SchemeName, false, FailureHandling.STOP_ON_FAILURE)

    println('SchemeName from the mobile should be properly inserted into database')
}

//5th DB
queryString3 = (('select * from adm_product_hierarchy where aph_id=\'' + GlobalVariable.label1) + '\' ')

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

while (recordSet3.next()) {
    Object APH_Name = recordSet3.getObject('APH_Name')

    ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProduct', CreditNote)

    Mobile.verifyMatch(ProductName, APH_Name, false, FailureHandling.STOP_ON_FAILURE)

    println('Scheme Product Name from the mobile should be properly inserted into database')
}

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Sales Invoice View'), [:], 
    FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesInvoiceView/SOnumber'), 50)

WebUI.click(findTestObject('Web Part/SalesInvoiceView/SOnumber'))

//GlobalVariable.VersionCodenvoiceNo = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 2)
GlobalVariable.SO_Number = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', CreditNote)

WebUI.setText(findTestObject('Web Part/SalesInvoiceView/SOnumber'), GlobalVariable.SO_Number)

WebUI.click(findTestObject('Web Part/SalesInvoiceView/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.label = GlobalVariable.SO_Number

WebUI.verifyElementVisible(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Number : ' + GlobalVariable.label)

'Invoice number get from Data Base'
KeywordUtil.logInfo(GlobalVariable.Pending_bill_id)

GlobalVariable.label = GlobalVariable.Pending_bill_id

WebUI.verifyElementVisible(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('Invoice Number : ' + GlobalVariable.label)

//GlobalVariable.label = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 2)
//
//WebUI.verifyElementVisible(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))
//
//Amt = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))
//
//WebUI.takeScreenshot()
//
//KeywordUtil.logInfo(Amt)
GlobalVariable.label = GlobalVariable.Pending_bill_id

WebUI.click(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 10, FailureHandling.OPTIONAL)

//
//Invoice_Number = WebUI.getAttribute(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 'value')
//
//KeywordUtil.logInfo('Invoice_Number : ' + Invoice_Number)
//
//WebUI.verifyMatch(Invoice_Number, GlobalVariable.VersionCodenvoiceNo, false, FailureHandling.STOP_ON_FAILURE)
//
//WebUI.takeScreenshot()
//
//SO_Number = WebUI.getAttribute(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SO no'), 'value')
//
//KeywordUtil.logInfo('SO_Number : ' + SO_Number)
//
//WebUI.verifyMatch(SO_Number, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 1), false, 
//    FailureHandling.STOP_ON_FAILURE)
//
//WebUI.takeScreenshot()
WebUI.scrollToElement(findTestObject('Web Part/SalesInvoiceView/Random Click'), 5, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', CreditNote)

SKU = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

WebUI.verifyMatch(SKU, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Pric = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Price'))

Price = Pric.replace('$ ', '')

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Price', CreditNote), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Case'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Qty', CreditNote), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', CreditNote)

Total_Amt1 = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalValue'))

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProduct', CreditNote)

Free_Total_Amt1 = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalValue'))

Total_Amt2 = Total_Amt1.replaceAll(',', '')

BUY_Total_Amt = Total_Amt2.replace('$ ', '')

KeywordUtil.logInfo('Buy SKU Total_Amt : ' + BUY_Total_Amt)

Free_Total_Amt1 = Free_Total_Amt1.replaceAll(',', '')

Free_Total_Amt = Free_Total_Amt1.replace('$ ', '')

KeywordUtil.logInfo('Free SKU Total_Amt : ' + Free_Total_Amt)

Invoice_Total = Double.parseDouble(BUY_Total_Amt) + Double.parseDouble(Free_Total_Amt)

'*************'
TotalAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', CreditNote)


WebUI.verifyEqual(Invoice_Total, Double.parseDouble(TotalAmt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

SchemeAmt = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SchemeAmt'))

Scheme_Amt = SchemeAmt.replace('$ ', '')

KeywordUtil.logInfo('Tax : ' + Scheme_Amt)

SchemeAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeAmt', CreditNote)

WebUI.verifyEqual(Double.parseDouble(Scheme_Amt), Double.parseDouble(SchemeAmt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', CreditNote)

Gross_Amt1 = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalAmt'))

Gross_Amt2 = Gross_Amt1.replaceAll(',', '')

Buy_SKU_Gross_Amt = Gross_Amt2.replace('$ ', '')

KeywordUtil.logInfo('Buy sku Gross_Amt : ' + Buy_SKU_Gross_Amt)

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProduct', CreditNote)

Gross_Amt1 = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalAmt'))

Gross_Amt2 = Gross_Amt1.replaceAll(',', '')

Free_SKU_Gross_Amt = Gross_Amt2.replace('$ ', '')

KeywordUtil.logInfo('Free sku Gross_Amt : ' + Free_SKU_Gross_Amt)

Gross_Total =Double.parseDouble(Buy_SKU_Gross_Amt) + Double.parseDouble( Free_SKU_Gross_Amt)

Gross_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', CreditNote)

'ssssssssssssssssssss'
Tax = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Tax', CreditNote)

FreeProductTax = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProductTax', CreditNote)

GrossAmt = Double.parseDouble(Gross_Amt) + Double.parseDouble(Tax)   
	//- Double.parseDouble(FreeProductTax))

WebUI.verifyEqual(Gross_Total, GrossAmt, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

'free product grid details'
GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProduct', CreditNote)

freeSKU = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('Free SKU : ' + freeSKU)

WebUI.verifyMatch(freeSKU, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

freeSkuQty = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Case'))

KeywordUtil.logInfo('free sku Qty : ' + freeSkuQty)

WebUI.verifyMatch(freeSkuQty, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProductQTY', CreditNote), 
    false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SchemeNameField'), 0)

SchemeName = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SchemeNameField'))

Expected_SchemeName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeName', CreditNote)

Schemeverify = SchemeName.contains(Expected_SchemeName)

String Scheme_verify = Schemeverify

WebUI.verifyMatch(Scheme_verify, 'true', false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()


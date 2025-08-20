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
import org.testng.Assert as Assert

Mobile.comment('Validation for Order table scheme order detail inserting or not')

Connection connection = null

GlobalVariable.SO_Number = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 4)

queryString = (('select * from Appdata_Order_Header  where oh_order_id=\'' + GlobalVariable.SO_Number) + '\'')

String conn = (((((('jdbc:sqlserver://' + GlobalVariable.DB_URL) + ';databaseName=') + GlobalVariable.DB_Name) + ';user=') + GlobalVariable.DB_Username) + ';password=') + GlobalVariable.DB_Password

connection = DriverManager.getConnection(conn)

Statement stm = connection.createStatement()

ResultSet rs = stm.executeQuery(queryString)

def recordSet = rs

if (recordSet.next()) {
    Object oh_id = recordSet.getObject('oh_id')

    GlobalVariable.label = oh_id

    KeywordUtil.logInfo('oh_id : ' + GlobalVariable.label)
} else {
    KeywordUtil.logInfo('Data not inserting in DB')

    Assert.fail()
}

Mobile.comment('Validation for Order scheme detail table')

queryString1 = (('select * from AppData_Order_Scheme_Detail where OSD_OH_Id=\'' + GlobalVariable.label) + '\'')

ResultSet rs1 = stm.executeQuery(queryString1)

def recordSet1 = rs1

if (recordSet1.next()) {
    Object OSD_AS_Id = recordSet1.getObject('OSD_AS_Id')

    GlobalVariable.label = OSD_AS_Id

    Object OSD_APH_Id = recordSet1.getObject('OSD_APH_Id')

    GlobalVariable.label1 = OSD_APH_Id
} else {
    KeywordUtil.logInfo('Data not inserting in DB')

    Assert.fail()
}

Mobile.comment('Validation for Scheme table')

queryString2 = (('select * from ADM_Scheme where AS_ID=\'' + GlobalVariable.label) + '\' ')

ResultSet rs2 = stm.executeQuery(queryString2)

def recordSet2 = rs2

if (recordSet2.next()) {
    Object AS_Code = recordSet2.getObject('AS_Code')

    SchemeName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeName', 4)

    Mobile.verifyMatch(AS_Code, SchemeName, false, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo('SchemeName from the mobile should be properly inserted into database')
} else {
    KeywordUtil.logInfo('Data not inserting in DB')

    Assert.fail()
}

Mobile.comment('Validation for Product table')

queryString3 = (('select * from ADM_Product_Hierarchy where APH_Id=\'' + GlobalVariable.label1) + '\' ')

ResultSet rs3 = stm.executeQuery(queryString3)

def recordSet3 = rs3

if (recordSet3.next()) {
    Object APH_Name = recordSet3.getObject('APH_Name')

    ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('FreeProduct', 4)

    Mobile.verifyMatch(ProductName, APH_Name, false, FailureHandling.STOP_ON_FAILURE)

    KeywordUtil.logInfo('Scheme Product Name from the mobile should be properly inserted into database')
} else {
    KeywordUtil.logInfo('Data not inserting in DB')

    Assert.fail()
}

Mobile.comment('Validation for Web Order view screen')

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Sales Order View'), [:], FailureHandling.STOP_ON_FAILURE)

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

GlobalVariable.InvoiceNo = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 4)

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

GlobalVariable.Total_Amt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 4)

WebUI.verifyElementVisible(findTestObject('Object Repository/Mobile/Web Part/SalesOrderView/SO_Value_Grid'))

String SO_Value = WebUI.getText(findTestObject('Object Repository/Mobile/Web Part/SalesOrderView/SO_Value_Grid'))

SO_Value_1 = SO_Value.replaceAll(',', '')

KeywordUtil.logInfo(SO_Value_1)

SO_Val = SO_Value_1.replace('$ ', '')

KeywordUtil.logInfo(SO_Val)

WebUI.verifyEqual(Double.parseDouble(SO_Val), Double.parseDouble(GlobalVariable.Total_Amt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Value : ' + GlobalVariable.Total_Amt)

WebUI.click(findTestObject('Web Part/SalesOrderView/View_Icon'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesOrderView/ViewScrn_SOnumber'), 10, FailureHandling.OPTIONAL)

SO_Number = WebUI.getAttribute(findTestObject('Web Part/SalesOrderView/ViewScrn_SOnumber'), 'value')

KeywordUtil.logInfo('SO_Number : ' + SO_Number)

WebUI.verifyMatch(SO_Number, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/ViewScrn_TotalValue'), 10, FailureHandling.STOP_ON_FAILURE)

GrossAmt = WebUI.getAttribute(findTestObject('Web Part/SalesOrderView/ViewScrn_TotalValue'), 'value')

Grossamount = GrossAmt.replaceAll(',', '')

Gross_Amt = Grossamount.replace('$ ', '')

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

WebUI.verifyMatch(Gross_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/ViewScrn_NetAmt'), 10, FailureHandling.STOP_ON_FAILURE)

NetAmt = WebUI.getAttribute(findTestObject('Web Part/SalesOrderView/ViewScrn_NetAmt'), 'value')

Netamount = NetAmt.replaceAll(',', '')

Net_Amt = Netamount.replace('$ ', '')

KeywordUtil.logInfo('Net_Amt : ' + Net_Amt)

WebUI.verifyMatch(Net_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Mobile.comment('Validation for Order product and details in Grid table')

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 4)

SKU = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

Expected_Text = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 4)

WebUI.verifyMatch(SKU.toUpperCase(), Expected_Text.toUpperCase(), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Pric = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Price'))

Price = Pric.replace('$ ', '')

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Price', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('Mobile/Web Part/SalesOrderView/ViewScrn_Grid_Case'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesOrderView/ViewScrn_GrossLabel'), 10, FailureHandling.OPTIONAL)

TotalAmt = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_TotalAmt'))

totalamount = TotalAmt.replaceAll(',', '')

Total_Amt = totalamount.replace('$ ', '')

KeywordUtil.logInfo('Total_Amt : ' + Total_Amt)

WebUI.verifyMatch(Total_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Tax_1 = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Tax'))

Tax = Tax_1.replace('$ ', '')

KeywordUtil.logInfo('Tax : ' + Tax)

Tax_amt_sheet = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 4)

//WebUI.verifyEqual(Double.parseDouble(Tax), Double.parseDouble(Tax_amt_sheet))

WebUI.takeScreenshot()

GrossAmt = WebUI.getText(findTestObject('Web Part/SalesOrderView/ViewScrn_Grid_Gross'))

Grossamount = GrossAmt.replaceAll(',', '')

Gross_Amt = Grossamount.replace('$ ', '')

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

WebUI.verifyMatch(Gross_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 4), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Mobile.comment('Validation for scheme name in order view screen')

WebUI.scrollToElement(findTestObject('Mobile/Web Part/SalesOrderView/SchemeNameField'), 0)

SchemeName = WebUI.getText(findTestObject('Mobile/Web Part/SalesOrderView/SchemeNameField'))

Expected_SchemeName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeName', 4)

Schemeverify = SchemeName.contains(Expected_SchemeName)

String Scheme_verify = Schemeverify

WebUI.verifyMatch(Scheme_verify, 'true', false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()




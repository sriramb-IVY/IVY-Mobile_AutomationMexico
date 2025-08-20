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



Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/DistributorLogin'), [:], FailureHandling.STOP_ON_FAILURE)

Mobile.callTestCase(findTestCase('Product_Mobile/Common/Web Login and Navigation/Navigate to Sales Invoice View'), [:],
	FailureHandling.STOP_ON_FAILURE)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesInvoiceView/SOnumber'), 50)

WebUI.click(findTestObject('Web Part/SalesInvoiceView/SOnumber'))

GlobalVariable.SO_Number = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('OrderNumber', 3)

WebUI.setText(findTestObject('Web Part/SalesInvoiceView/SOnumber'), GlobalVariable.SO_Number)

WebUI.click(findTestObject('Web Part/SalesInvoiceView/Search_Btn'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

GlobalVariable.label = GlobalVariable.SO_Number

WebUI.verifyElementVisible(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('SO Number : ' + GlobalVariable.label)

'Invoice number get from Data Base'
KeywordUtil.logInfo(GlobalVariable.InvoiceNo)

GlobalVariable.label = GlobalVariable.InvoiceNo

WebUI.verifyElementVisible(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.verifyElementText(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'), GlobalVariable.label)

WebUI.takeScreenshot()

KeywordUtil.logInfo('Invoice Number : ' + GlobalVariable.label)


GlobalVariable.label = GlobalVariable.InvoiceNo

WebUI.click(findTestObject('Web Part/SalesInvoiceView/Global_(GridValue)'))

WebUI.waitForElementNotVisible(findTestObject('Web Part/Re-usable/Page Load'), 100, FailureHandling.OPTIONAL)

WebUI.waitForElementVisible(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SI no'), 10, FailureHandling.OPTIONAL)


WebUI.scrollToElement(findTestObject('Web Part/SalesInvoiceView/Random Click'), 5, FailureHandling.OPTIONAL)

GlobalVariable.ProductName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('ProductName', 3)

SKU = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SKU'))

KeywordUtil.logInfo('SKU : ' + SKU)

WebUI.verifyMatch(SKU, GlobalVariable.ProductName, false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Pric = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Price'))

Price = Pric.replace('$ ', '')

KeywordUtil.logInfo('Price : ' + Price)

WebUI.verifyMatch(Price, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Price', 3), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Qty = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Case'))

KeywordUtil.logInfo('Qty : ' + Qty)

WebUI.verifyMatch(Qty, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Qty', 3), false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Total_Amt1 = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalValue'))

Total_Amt2 = Total_Amt1.replaceAll(",", "")

Total_Amt = Total_Amt2.replace('$ ', '')

KeywordUtil.logInfo('Total_Amt : ' + Total_Amt)

//WebUI.verifyMatch(Total_Amt, findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 2), false, FailureHandling.STOP_ON_FAILURE)


TotalAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Total_Amt', 3)

WebUI.verifyEqual(Double.parseDouble(Total_Amt), Double.parseDouble(TotalAmt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

SchemeAmt = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_SchemeAmt'))

Scheme_Amt = SchemeAmt.replace('$ ', '')

KeywordUtil.logInfo('Tax : ' + Scheme_Amt)

SchemeAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeAmt', 3)

WebUI.verifyEqual(Double.parseDouble(Scheme_Amt), Double.parseDouble(SchemeAmt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Taxs = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_Tax'))

Tax = Taxs.replace('$ ', '')

KeywordUtil.logInfo('Tax : ' + Tax)

TaxValue = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Tax', 3)

WebUI.verifyEqual(Double.parseDouble(Tax), Double.parseDouble(TaxValue), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

Gross_Amt1 = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/ViewScrn_Grid_TotalAmt'))

Gross_Amt2 = Gross_Amt1.replaceAll(",", "")

Gross_Amt = Gross_Amt2.replace('$ ', '')

KeywordUtil.logInfo('Gross_Amt : ' + Gross_Amt)

GrossAmt = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('Gross_Amt', 3)

WebUI.verifyEqual(Double.parseDouble(Gross_Amt), Double.parseDouble(GrossAmt), FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()

WebUI.scrollToElement(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SchemeNameField'), 0)

SchemeName = WebUI.getText(findTestObject('Web Part/SalesInvoiceView/InvoiceDetails/SchemeNameField'))

Expected_SchemeName = findTestData('Web Input Data/Sales_Order_Invoice_View').getValue('SchemeName', 3)

Schemeverify = SchemeName.contains(Expected_SchemeName)

String Scheme_verify = Schemeverify

WebUI.verifyMatch(Scheme_verify, 'true', false, FailureHandling.STOP_ON_FAILURE)

WebUI.takeScreenshot()